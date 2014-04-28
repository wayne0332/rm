<#import "/share/macroShare.ftl" as macroShare>
<#import "/task/macroTask.ftl" as macroTask>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>作业评分</title>
</head>
<body>
<div class="container">
    <div class="grid">
        <div class="main-content">
            <div class="row">
                <h2 class="span8 offset1">
                    <a href="/back"><i class="icon-arrow-left-3 fg-darker smaller"></i></a>
                    作业评分
                    <button class="button warning" style="margin-left: 10px;" id="taskDetail">作业要求</button>
                </h2>

                <div class="span12 offset1 margin20"><span class="span2 text-right">提交人数:</span>
                    <div class="slider permanent-hint hint-top span8" data-role="slider"
                         data-position="${task.homeworks?size}" data-max="${totalStudentNumber}" data-show-hint="true"
                         data-can-move="false" data-colors="indigo, violet, orange, yellow" style="margin-top: 5px;"></div>
                    <span class="span1 text-center button info" style="margin-top: -2px;">${(task.homeworks?size / totalStudentNumber)?string.percent}</span>
                </div>
                <div class="span10 offset2 margin20"><@macroTask.listHomework homeworkMap=Request.homeworks/></div>
                <#--TODO 弹框显示-->
                <div class="span10 offset2 margin20"><@macroTask.listNotSubmit studentMap=Request.notSubmits/></div>
            </div>
        </div>
    </div>
</div>
<div style="display: none">
    <div id="taskTitle"><a href="/task/show/${task.id}">${task.share.title}</a></div>
    <div id="taskContent">
        <div class="wordBreak">${task.share.content}</div>
    </div>
</div>
</body>
</html>