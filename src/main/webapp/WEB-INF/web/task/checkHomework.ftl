<#import "/share/macroShare.ftl" as macroShare>
<#include "/includeEnum.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>${homework.student.name} 的作业</title>
</head>
<body>
<div class="container">
    <div class="grid">
        <div class="main-content">
            <div class="row">
                <h2 class="span8 offset1">
                    <a href="/task/checkInput/${homework.task.id}"><i
                            class="icon-arrow-left-3 fg-darker smaller"></i></a>
                ${homework.student.name} 的作业
                </h2>

                <div class="accordion span12 offset1" data-role="accordion" data-closeany="false">
                    <div class="accordion-frame">
                        <a href="#" class="heading active fg-lightBlue">评分</a>

                        <div class="content no-padding" style="display: block; margin-left: -20px;">
                            <div class="grid no-margin">
                                <div class="row no-margin">
                                    <form method="post" action="/task/check">
                                        <fieldset>
                                            <div class="span12" style="margin-top: 20px;"><span
                                                    class="span2 text-right">分数:</span>

                                                <div class="slider permanent-hint hint-top span8" data-role="slider"
                                                     data-position="${(homework.score??)?string(homework.score?if_exists, "80")}" data-max="100" data-show-hint="true"
                                                     data-colors="indigo, violet, orange, yellow" data-name="score"
                                                     style="margin-top: 5px;" data-can-move="${(homework.task.status != TaskStatus.END)?string("true", "false")}"></div>
                                                <#if homework.task.status != TaskStatus.END>
                                                    <input class="span1 info" type="submit" value="确定"
                                                           style="margin-top: -2px;"/>
                                                </#if>
                                            </div>
                                            <input type="hidden" name="id" value="${homework.id}"/>
                                            <input type="hidden" name="status" value="${HomeworkStatus.CHECKED}"/>
                                            <input type="hidden" name="taskId" value="${homework.task.id}"/>
                                        </fieldset>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="accordion-frame">
                        <a href="#" class="heading active fg-lightBlue">内容</a>

                        <div class="content no-padding" style="display: block; margin-left: -20px;">
                            <div class="grid no-margin">
                                <div class="row no-margin">
                                    <h2 class="text-center" style="margin-top: 20px;">
                                        <span>${homework.student.clazz.subject.name} ${homework.student.clazz.name} ${homework.student.name}</span>
                                    </h2>
                                    <h4 class="text-center">
                                        <a href="/upload/download/homework/${homework.id}">上传附件</a>
                                    </h4>

                                    <div class="wordBreak span10 offset1"
                                         style="margin-left: -15px; margin-bottom: 20px;">${homework.remark}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>