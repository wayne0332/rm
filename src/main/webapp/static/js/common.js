function addCSS(path) {
    var link = document.createElement('link');
    link.type = 'text/css';
    link.rel = 'stylesheet';
    link.href = path;
    document.getElementsByTagName("head")[0].appendChild(link);
}
var uploadCount = 0;
$(document).ready(function () {
    $("#post_body").qeditor({});

    var manualuploader = $('#manual-fine-uploader').fineUploader({
        request: {
            endpoint: '/upload/upload'
        },
        button: $("#selectFile"),
        template: "qq-template-manual-noedit",
        autoUpload: true
    }).on('submit', function (event, id, name) {
        ++uploadCount;
        if (uploadCount == 1) {
            $("#submit").attr("disabled", true);
            $("#submit").attr("value", "上传中");
        }
    }).on('complete', function (event, id, name, responseJSON) {
        --uploadCount;
        if (uploadCount == 0) {
            $("#submit").attr("disabled", false);
            $("#submit").attr("value", "添加");
        }
    }).on("deleteComplete", function (event, id, xhr, isError) {
        //...
    }).on("delete", function (event, id) {
        // ...
    }).on("error", function (event, id, name, errorReason) {
        // ...
    });

    $('#upload').click(function () {
        manualuploader.fineUploader('uploadStoredFiles');
    });

    $("input[name='qqfile']").css("height", "40px");
});
$(document).on('click', '.uploadFile', function(){
    $(this).toggleClass("selected");
});