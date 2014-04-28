<#import "/share/macroShare.ftl" as macroShare>
<#import "/task/macroTask.ftl" as macroTask>
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
                        </h2>

                        <div class="balloon bottom">
                            <div class="tab-control" data-role="tab-control">
                                <ul class="tabs">
                                    <li class="active"><a class="fg-grayLight infoTab" href="#notFinish">未完成</a></li>
                                    <li><a class="fg-grayLight infoTab" href="#finish">已完成</a></li>
                                </ul>

                                <div class="frames">
                                    <div id="notFinish" class="frame listview-outlook" style="display: block;">
                                    <@macroTask.taskList tasks=Request.notFinishTasks/>
                                    </div>
                                    <div id="finish" class="frame listview-outlook" style="display: none;">
                                    <@macroTask.taskList tasks=Request.finishTasks/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="span6">
                        <h2>资源
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