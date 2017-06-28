function deleteImg(jId, imgId){
    var url = '/img/' + jId + "/" + imgId;
    $.ajax({
        url: url,
        type: 'DELETE',
        success: function(result) {
            $('#'+imgId).remove();
        }
    });
}
function navTo(view){
    var loader = $('.loader');
    loader.css("display","block");

    if ( view === "user") {
      $('.nav-a').remove();
      $('.nav').css("display","block");
      navTo('jewelrys');
    } else {
        $.get( view.trim(), function(result) {
            loader.css("display","none");
            if ( view === "admin") {
                $('.nav').css("display","none");
                $('.container-fluid').append(result);
            } else {
                $('.content').html(result);
            }
        });
    }

    return false;
}
function submitForm(url, formClass){
    var loader = $('.loader');
    formClass = "." + formClass;
    loader.css("display","block");
    var form = $(formClass+" form");
    var method = form.attr('method');

    var data = form.serialize();

    if ( method === "post") {

        // Jewelery Image Upload
        if ( formClass === ".jewelry-img-u" ) {
            var input = $(formClass + " input[type=file]");
            input
            var files = input.get(0).files;
            data = new FormData();
            data.append("image", files[0]);
            $.ajax({
                url: url,
                type: 'POST',
                data: data,
                async: true,
                cache: false,
                contentType: false,
                processData: false,
                success: function (result) {
                loader.css("display","none");
                    $('.j-img-list').replaceWith(result);
                    $('.jewelry-img-u input')[0].value = "";
                },
                error: function (){
                    loader.css("display","none");
                }
            });
        } else {
            $.post(url, data, function (result) {
                loader.css("display", "none");
                $('.content').html(result);
                return false;
            })
        }
    }

    if ( method === "get") {
        $.get( url, function(result) {
            loader.css("display","none");
             $('.content').html(result);
            return false;
        });
    }
    if ( method === "delete") {
        $.ajax( {
            url: url,
            method: "delete",
            success: function(result) {
                loader.css("display","none");
                $('.content').html(result);
                return false;
                }
        });
    }
    return false;
}