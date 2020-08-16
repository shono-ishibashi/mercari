package com.example.demo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * 商品の編集、更新をする際に使用するform
 *
 * 
 */

@Data
public class ItemForm {
    private Integer id;

    @NotBlank(message = "商品名を入力してください")
    private String name;

    @Pattern(regexp = "^\\d{1,10}(\\.\\d{0,2})?$",message = "正しく入力してください ex) 12345.67" )
    private String price;

    @NotNull(message = "ジャンルを選択してください")
    private Integer grandParentCategory;

    @NotNull(message = "ジャンルを選択してください")
    private Integer parentCategory;

    @NotNull(message = "ジャンルを選択してください")
    private Integer childCategory;

    private String brand;

    @NotNull(message = "コンディションを選択してください")
    private Integer condition;

    @NotBlank(message = "説明を入力してください")
    private String description;
}
