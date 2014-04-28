$(document).ready(function () {
    setTimeout(function () {
        $.Notify({
            caption: '登陆错误',
            shadow: false,
            width: 200,
            height: 60,
            content: '账号或密码错误!',
            style: {background: 'white', color: '#fa6800'},
            timeout: 30 * 1000
        });
    }, 300);
    $(".info-state").addClass("warning-state").removeClass("info-state")
    $(".info").addClass("warning").removeClass("info");
    $(".fg-lightBlue").addClass("fg-orange").removeClass("fg-lightBlue");
    $(".text-info").addClass("text-warning").removeClass("text-info");
    $(".bg-lightBlue").addClass("bg-orange").removeClass("bg-lightBlue");
});