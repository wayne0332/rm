<#import "/share/macroShare.ftl" as macroShare>
<#import "/task/macroTask.ftl" as macroTask>
<#include "/includeEnum.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>主页</title>
</head>
<body>
<div class="page-region-content">
    <div class="page">
        <div class="container">
            <div class="grid">
                <div class="row">
                    <div class="span6">
                        <h2>作业
                            <button id="addHomework" class="button link linkBottom"
                                    value="/task/addInput"><span
                                    class="icon-plus"></span> 添加
                            </button>
                        </h2>
                        <div class="balloon bottom">
                            <div class="tab-control" data-role="tab-control">
                                <ul class="tabs">
                                    <li><a class="fg-grayLight infoTab" href="#newTasks">未开始的</a></li>
                                    <li class="active"><a class="fg-grayLight infoTab" href="#beginTasks">进行中的</a></li>
                                    <li><a class="fg-grayLight infoTab" href="#waitCheckTasks">待批改的</a></li>
                                    <li><a class="fg-grayLight infoTab" href="#finishTasks">完成的</a></li>
                                </ul>

                                <div class="frames">
                                    <div id="newTasks" class="frame listview-outlook" style="display: block;">
                                    <@macroTask.taskList tasks=Request.newTasks/>
                                    </div>
                                    <div id="beginTasks" class="frame listview-outlook" style="display: block;">
                                    <@macroTask.taskList tasks=Request.beginTasks/>
                                    </div>
                                    <div id="waitCheckTasks" class="frame listview-outlook" style="display: block;">
                                    <@macroTask.taskList tasks=Request.waitCheckTasks linkUrl="/task/checkInput/"/>
                                    </div>
                                    <div id="finishTasks" class="frame listview-outlook" style="display: block;">
                                    <@macroTask.taskList tasks=Request.finishTasks linkUrl="/task/checkInput/"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="span6">
                        <h2>资源
                            <button id="addSource" class="button link linkBottom" value="/share/addInput"><span
                                    class="icon-plus"></span> 添加
                            </button>
                        </h2>
                        <div class="balloon bottom">
                            <div class="listview-outlook">
                            <@macroShare.shareList shares=Request.shares/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>