$(function(){
    if ((document.location.host.indexOf('.dev') > -1) || (document.location.host.indexOf('modernui') > -1) ) {
        $("<script/>").attr('src', '/static/js/common/metro/metro-loader.js').appendTo($('head'));
    } else {
//        $("<script/>").attr('src', '/static/js/common/metro.min.js').appendTo($('head'));
        $("<script/>").attr('src', '/static/js/common/metro/metro-loader.js').appendTo($('head'));
    }
})