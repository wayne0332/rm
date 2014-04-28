<#import "macroUpload.ftl" as macroUpload>
<#import "macroShare.ftl" as macroShare>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>添加资源</title>
</head>
<body>
<div class="container">
    <div class="grid">
        <div class="main-content">
            <div class="row">
                <h2 class="span3 offset1">
                    <a href="/back"><i class="icon-arrow-left-3 fg-darker smaller"></i></a>
                    添加资源
                </h2>

                <div class="row span11 offset3">
                    <div class="tab-control" data-role="tab-control" data-effect="fade">
                        <ul class="tabs">
                            <li class="active"><a href="#_page_1">添加文章</a></li>
                            <li><a href="#_page_2">添加文件</a></li>
                        </ul>

                        <div class="frames">
                            <div class="frame" id="_page_1">
                                <form action="/share/add" method="post">
                                    <fieldset>
                                    <@macroShare.addShareInput/>
                                        <input type="hidden" name="type" value="ARTICLES"/>
                                        <div class="span11">
                                            <div class="toolbar transparent offset6">
                                                <input id="submit" class="info" type="submit" value="添加">
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                            <div class="frame" id="_page_2">...</div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>