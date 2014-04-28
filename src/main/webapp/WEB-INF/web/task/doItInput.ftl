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
                <form action="/task/doIt" method="post" enctype="multipart/form-data">
                    <h2 class="span8 offset1">
                        <a href="/task/show/${task.id}"><i class="icon-arrow-left-3 fg-darker smaller"></i></a>
                        作业
                        <button class="button warning" style="margin-left: 10px;" id="taskDetail" type="button">作业要求</button>
                    </h2>

                    <h4 class="span8 offset3">${task.share.teacher.name} <a class="fg-orange"
                                                                            href="#">${task.starTime?string("yyyy-MM-dd")}</a>
                        ~ <a class="fg-orange"
                             href="#">${task.endTime?string("yyyy-MM-dd")}</a></h4>
                    <h4 class="span8 offset3"></h4>

                    <div class="input-control textarea span8 offset3"
                         style="margin-bottom: -10px;">
                        <textarea id="post_body" name="remark" class="textarea"
                                  placeholder="${(homework??)?string("", "内容")}">
                        <#if homework??>
                                ${homework.remark}
                            </#if>
                        </textarea>
                    </div>
                <#if homework??>
                    <div class="span2 offset1"><a href="/upload/download/homework/${homework.id}" class="tile bg-orange"
                       style="margin-left: -15px;">
                        <div class="tile-content icon">
                            <i class="icon-file"></i>
                        </div>
                        <div class="brand opacity row">
                            <span class="text">附件
                                </span>
                        </div>
                    </a></div>
                </#if>
                    <div class="input-control file warning-state span8 offset3">
                        <input name="file" type="file">
                        <button class="btn-file" type="button"></button>
                    </div>
                    <div class="span4 offset9">
                        <div class="toolbar transparent">
                            <input id="submit" class="warning" type="submit" value="提交">
                        </div>
                    </div>
                    <#if homework??>
                        <input type="hidden" name="id" value="${homework.id}"/>
                    </#if>
                    <input type="hidden" name="status" value="NEW"/>
                    <input type="hidden" name="taskId" value="${task.id}"/>
                </form>
            </div>
        </div>
    </div>
</div>
<div style="display: none">
    <div id="taskTitle"><a href="/task/show/${task.id}">${task.share.title}</a></div>
    <div id="taskContent"><div class="wordBreak">${task.share.content}</div></div>
</div>
</body>
</html>