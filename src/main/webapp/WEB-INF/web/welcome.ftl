<#include "/includeEnum.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>登录</title>
</head>
<body>
<div class="flex-center bg-lightBlue">
    <div class="page-region">
        <div class="page">
            <div class="container">
                <div class="grid">
                    <div class="row">
                        <img src="/static/image/loginLogo.jpg" class="polaroid span2 offset5">
                    </div>
                    <div class="row">
                        <div class="login span8 offset2">
                            <form action="/login" method="post">
                                <fieldset>
                                    <div class="padding10 fg-color-darken text-center">
                                        <legend><h1 class="text-info">教学资源管理系统</h1></legend>
                                    </div>
                                    <div class="span4">
                                        <div class="input-control text size3 block info-state"
                                             data-role="input-control">
                                            <input type="text" name="number" placeholder="学号/教室号">
                                            <button class="btn-clear" tabindex="-1"></button>
                                        </div>
                                        <div class="input-control password size3 block info-state"
                                             data-role="input-control">
                                            <input type="password" name="password" placeholder="密码"
                                                   autofocus="">
                                            <button class="btn-reveal" tabindex="-1"></button>
                                        </div>
                                    </div>

                                    <div>
                                        <div class="clearfix">
                                            <div class="input-control radio inline-block fg-lightBlue"
                                                 data-role="input-control">
                                                <label>
                                                    <input type="radio" name="role" checked="" value="STUDENT">
                                                    <span class="check"></span>
                                                    学生
                                                </label>
                                                <label class="inline-block">
                                                    <input type="radio" name="role" value="TEACHER">
                                                    <span class="check"></span>
                                                    教师
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="offset2">
                                        <input class="info" type="submit" value="登录">
                                        <input type="reset" value="重设">
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>