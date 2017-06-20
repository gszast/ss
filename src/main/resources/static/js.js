$(document).ready(function () {
    console.log("ASDASDASD");
})
$("form#data").submit(function(event){

    event.preventDefault();

    var url  = 'http://server.com/upload';
    var image_file = $('#image_file').get(0).files[0];

    var formData = new FormData();
    formData.append("image_file", image_file);

    $.ajax({
        url: url,
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (status) {
            // do something on success
        }
    });

    return false;

});
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

    $.get( view.trim(), function(result) {
        loader.css("display","none");
        if ( view === "admin") {
        } else {
            $('.container').html(result);
        }
    });
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
                    $('.j-img-list').html(result);
                }
            });
        } else {
            $.post(url, data, function (result) {
                loader.css("display", "none");
                $('.container').html(result);
                return false;
            })
        }
    }

    if ( method === "get") {
        $.get( url, function(result) {
            loader.css("display","none");
            container.html(result);
            return false;
        });
    }
    if ( method === "delete") {
        $.ajax( {
            url: url,
            method: "delete",
            success: function(result) {
                loader.css("display","none");
                container.html(result);
                return false;
                }
        });
    }
    return false;
}