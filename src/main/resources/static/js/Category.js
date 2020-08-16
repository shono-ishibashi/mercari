$(function(){

    const token = $("meta[name='_csrf']").attr('content');
    const header = $("meta[name='_csrf_header']").attr('content');

    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    $('#grandParentCategory').on('change', function(){
        var grandParentCategoryId = $(this).val();
        $('#parentCategory').empty();
        $('#childCategory').empty().append('<option>--中カテゴリーを選択してください--</option>');
        var data = {'grandParentCategoryId' : Number.parseInt(grandParentCategoryId)};

        var json = JSON.stringify(data);
        $.ajax({
            url: 'http://localhost:8080/mercari/category/getParentCategory',
            type: 'POST',
            dataType: 'json',
            contentType: "application/json; charset=UTF-8",
            data: json,
        }).done(function(data) {
            console.log(data);
            for(var category of data){
                $('#parentCategory').append('<option value='+category.id+'>'+category.name+'</option>');
            }
        }).fail(function(XMLHttpRequest, textStatus, errorThrown) {
            alert("error");
        })
    })

    $('#parentCategory').on('change', function(){
        var grandParentCategoryId = $('#grandParentCategory').val();
        var parentCategoryId = $('#parentCategory').val();
        $('#childCategory').empty().append('<option>--中カテゴリーを選択してください--</option>');
        var data = {
            'grandParentCategoryId' : Number.parseInt(grandParentCategoryId),
            'parentCategoryId' : Number.parseInt(parentCategoryId)
        };

        var json = JSON.stringify(data);
        $.ajax({
            url: 'http://localhost:8080/mercari/category/getChildCategory',
            type: 'POST',
            dataType: 'json',
            contentType: "application/json; charset=UTF-8",
            data: json,
        }).done(function(data) {
            console.log(data);
            for(var category of data.descendantCategories[0].descendantCategories){
                $('#childCategory').append('<option value='+category.id+'>'+category.name+'</option>');
            }
        }).fail(function(XMLHttpRequest, textStatus, errorThrown) {
            alert("error");
        })
    })





});