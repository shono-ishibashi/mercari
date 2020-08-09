package com.example.demo.controller;

import com.example.demo.domain.Category;
import com.example.demo.domain.Search;
import com.example.demo.myBatisParam.CategoryParam;
import com.example.demo.domain.Item;
import com.example.demo.form.ItemForm;
import com.example.demo.form.SearchForm;
import com.example.demo.myBatisParam.ItemUpdateParam;
import com.example.demo.repository.ItemMybatisRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ItemService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

import static java.util.Objects.equals;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Controller
@MapperScan("com.example.demo.mapper")
@RequestMapping("/")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private HttpSession session;

    @ModelAttribute
    public ItemForm itemFormSetUp() {
        return new ItemForm();
    }

    ;

    @ModelAttribute
    public SearchForm searchFormSetUp() {
        return new SearchForm();
    }

    @RequestMapping("")
    public String index(Model model, Integer page) {

        if (isNull(page) || page < 0) {
            page = 0;
        }

        final String URL = "/";

        List<Item> itemList = itemService.pagedFindAll(page);
        List<Category> grandParentCategories = categoryService.grandParentCategories();

        model.addAttribute("page", page);
        model.addAttribute("url", URL);
        model.addAttribute("itemList", itemList);
        model.addAttribute("grandParentCategory", grandParentCategories);

        return "list";
    }


    @RequestMapping("/search")
    public String search(Model model, Integer page, SearchForm searchForm) {
        final String URL = "/search";


        SearchForm sessionSearchForm = (SearchForm) session.getAttribute("searchForm");

        //ページ移行などで、searchForm のフィールド変数がすべてnullの場合、sessionスコープのsearchFormを代入
        if (
                isNull(searchForm.getItemName())
                && isNull(searchForm.getGrandParentCategory())
                && isNull(searchForm.getParentCategory())
                && isNull(searchForm.getChildCategory())
                && isNull(searchForm.getBrand())
                && isNull(searchForm.getUrl())
        ) {
            searchForm = sessionSearchForm;
            //新しい検索条件だったら新しい検索条件をsessionスコープに格納して、pageを初期化
        } else if (!searchForm.equals(sessionSearchForm)) {
            session.setAttribute("searchForm", searchForm);
            page = 0;
        }

        //不正なページ番号なら、page=0にする。
        if (isNull(page) || page < 0) {
            page = 0;
        }

        //form -> domain コピー
        Search search = new Search(
                searchForm.getItemName(),
                searchForm.getGrandParentCategory(),
                searchForm.getParentCategory(),
                searchForm.getChildCategory(),
                searchForm.getBrand(),
                page
        );

        //検索結果のItemのリスト
        List<Item> itemList = itemService.search(search);

        //階層が一番上のcategoryのリスト
        List<Category> grandParentCategories = categoryService.grandParentCategories();

        model.addAttribute("page", page);
        model.addAttribute("url", URL);
        model.addAttribute("itemList", itemList);
        model.addAttribute("grandParentCategory", grandParentCategories);
        model.addAttribute("searchFrom",searchForm);

        return "list";
    }

    @RequestMapping("/category")
    public String searchByCategory(Integer id){

        final String URL = "/category";

        SearchForm sessionSearchForm = (SearchForm) session.getAttribute("searchFrom");


        return "list";
    }

    @RequestMapping("/detail")
    public String showDetail(Integer id, Model model) {

        Item item = itemService.load(id);

        model.addAttribute("item", item);

        return "detail";
    }

    @RequestMapping("/edit")
    public String edit(Integer id, Model model) {
        Item item = itemService.load(id);

        ItemForm itemForm = new ItemForm();

        CategoryParam param = new CategoryParam();

        itemForm.setId(item.getId());
        itemForm.setName(item.getName());
        itemForm.setPrice(String.valueOf(item.getPrice()));

        if (!item.getAncestorCategories().isEmpty()) {
            itemForm.setGrandParentCategory(item.getAncestorCategories().get(0).getId());
            itemForm.setParentCategory(item.getAncestorCategories().get(1).getId());

            param.setGrandParentId(item.getAncestorCategories().get(0).getId());
            param.setParentId(item.getAncestorCategories().get(1).getId());
        }

        if (nonNull(item.getChildCategoryId())) {
            itemForm.setChildCategory(item.getChildCategoryId());
        }

        if (nonNull(item.getBrand())) {
            itemForm.setBrand(item.getBrand());
        }

        itemForm.setBrand(itemForm.getBrand());
        itemForm.setCondition(item.getCondition());
        itemForm.setDescription(item.getDescription());


        //itemのカテゴリー
        Category category = categoryService.searchCategory(param);

        //depthが１のcategoryのlist
        List<Category> grandParentCategories = categoryService.grandParentCategories();
        List<Category> parentCategories = null;
        //depthが2のcategoryのlist
        if (nonNull(item.getAncestorCategories().get(0).getId())) {
            parentCategories = categoryService.parentCategories(item.getAncestorCategories().get(0).getId());
        }

        model.addAttribute("grandParentCategories", grandParentCategories);
        model.addAttribute("parentCategories", parentCategories);
        model.addAttribute("category", category);
        model.addAttribute("itemForm", itemForm);

        return "edit";
    }

    @RequestMapping("/update")
    public String update(
            @Validated ItemForm itemForm,
            BindingResult result) {

        if (result.hasErrors() && nonNull(itemForm.getId())) {
            return "forward:/detail" + itemForm.getId();
        } else if (result.hasErrors() && isNull(itemForm.getId())) {
            return "forward:/add";
        }


        ItemUpdateParam param = new ItemUpdateParam();

        param.setId(itemForm.getId());
        param.setName(itemForm.getName());
        param.setCategory(itemForm.getChildCategory());
        param.setBrand(itemForm.getBrand());
        param.setPrice(Double.valueOf(itemForm.getPrice()));
        param.setCondition(itemForm.getCondition());
        param.setDescription(itemForm.getDescription());


        itemService.update(param);

        return "redirect:/";
    }

    @RequestMapping("/add")
    public String insert(Model model) {

        List<Category> grandParentCategories = categoryService.grandParentCategories();
        model.addAttribute("grandParentCategories", grandParentCategories);

        return "add";
    }
}
