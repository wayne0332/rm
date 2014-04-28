<#import "macroShare.ftl" as macroShare>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>共享资源</title>
</head>
<body>
<div class="container">
    <div class="grid">
        <div class="main-content">
            <div class="row">
                <h2 class="span8 offset1">
                    <a href="/back"><i class="icon-arrow-left-3 fg-darker smaller"></i></a>
                    共享资源
                </h2>

                <h1 class="text-center span12 offset1">${share.title}</h1>
                <h4 class="text-center span12 offset1">${share.teacher.name} ${share.editTime?string("yyyy-MM-dd hh:mm:ss")}</h4>

                <div class="span10 offset2 wordBreak">${share.content}</div>
                <div class="span2">
                <#list share.resources as resource>
                    <a href="/upload/download/share/${resource.id}" class="tile bg-lightBlue" style="margin-left: -15px;">
                        <div class="tile-content icon">
                            <i class="icon-file"></i>
                        </div>
                        <div class="brand opacity row">
                            <span class="text">${resource.name}&nbsp;&nbsp;<@macroShare.size size="${resource.size}"/></span>
                        </div>
                    </a>
                </#list>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>