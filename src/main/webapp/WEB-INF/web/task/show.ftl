<#import "/share/macroShare.ftl" as macroShare>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>作业</title>
</head>
<body>
<div class="container">
    <div class="grid">
        <div class="main-content">
            <div class="row">
                <h2 class="span8 offset1">
                    <a href="/back"><i class="icon-arrow-left-3 fg-darker smaller"></i></a>
                    作业
                </h2>

                <h1 class="text-center span12 offset1">
                <#if Session.student.name??>
                <a href="/task/doItInput/${task.id}" data-hint="作业|去完成作业" class="fg-red">${task.share.title}</a>
                    <#else>
                ${task.share.title}
                </#if>
                </h1>
                <h4 class="text-center span12 offset1">${task.share.teacher.name}</h4>
                <h4 class="text-center span12 offset1"><a class="selectTime"
                                                          href="#">${task.starTime?string("yyyy-MM-dd")}</a> ~ <a
                        href="#">${task.endTime?string("yyyy-MM-dd")}</a></h4>

                <div class="span4" style="margin-left: -90px;">
                    <div id="calendar" class="calendar"></div>
                </div>
                <div class="span10 wordBreak" style="margin-left: -15px;">${task.share.content}</div>
                <div class="span2">
                <#list task.share.resources as resource>
                    <a href="/upload/download/share/${resource.id}" class="tile bg-lightBlue"
                       style="margin-left: -15px;">
                        <div class="tile-content icon">
                            <i class="icon-file"></i>
                        </div>
                        <div class="brand opacity row">
                            <span class="text">${resource.name}
                                &nbsp;&nbsp;<@macroShare.size size="${resource.size}"/></span>
                        </div>
                    </a>
                </#list>
                </div>
                <div class="span12" style="height: 80px;"></div>
            </div>
        </div>
    </div>
    <div class="page-footer">
        <div class="navigation-bar light fixed-bottom bg-white" style="margin-left: 20px;">
            <div class="navigation-bar-content container">
                <div class="stepper rounded span12 offset1" data-steps="5" data-start="1">
                    <ul>
                        <li ${(task.status.ordinal() + 1 == 1)?string("class='current'", "")} style="left: 0px;"
                                                                                              data-hint="作业状态|新建"
                                                                                              data-hint-position="top"></li>
                        <li ${(task.status.ordinal() + 1 == 2)?string("class='current'", "")} style="left: 227.75px;"
                                                                                              data-hint="Title|This is a hint for Me"
                                                                                              data-hint-position="top"></li>
                        <li ${(task.status.ordinal() + 1 == 3)?string("class='current'", "")} style="left: 455.5px;"
                                                                                              data-hint="Title|This is a hint for Me"
                                                                                              data-hint-position="top"></li>
                        <li ${(task.status.ordinal() + 1 == 4)?string("class='current'", "")} style="left: 683.25px;"
                                                                                              data-hint="Title|This is a hint for Me"
                                                                                              data-hint-position="top"></li>
                        <li ${(task.status.ordinal() + 1 == 5)?string("class='current'", "")} style="left: 911px;"
                                                                                              data-hint="Title|This is a hint for Me"
                                                                                              data-hint-position="top"></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>