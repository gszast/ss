function deleteImg(jewelryId, imgId){
    $.ajax({
        url: '/img/'+jewelryId+'/'+imgId,
        type: 'DELETE',
        success: function(result) {
            $('#'+imgId).css("display","none");
        }
    });
}