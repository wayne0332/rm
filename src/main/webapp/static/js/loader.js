var currentPath = window.location.pathname.split("/",3).toString().replace(new RegExp(",", "g"), "/");
addCSS('/static/css' + currentPath + '.css');
$.getScript('/static/js' + currentPath + '.js');
$(document).ready(function () {
    $(".linkBottom").click(function () {
        location.href = $(this).val();
    });
});
function getRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        var strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = decodeURI(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}
function getParam(name) {
    return getRequest()[name];
}