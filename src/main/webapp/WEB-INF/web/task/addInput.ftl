<#import "/share/macroShare.ftl" as macroShare>
<#import "macroTask.ftl" as macroTask>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>添加作业</title>
</head>
<body>
<div class="container">
    <div class="grid">
        <div class="main-content">
            <div class="row">
                <h2 class="span3 offset1">
                    <a href="/back"><i class="icon-arrow-left-3 fg-darker smaller"></i></a>
                    添加作业
                </h2>

                <div class="row span11 offset3">
                    <form action="/task/add" method="post">
                        <fieldset>
                        <@macroTask.datePick/>
                        <@macroShare.addShareInput/>
                        <@macroTask.selectClass classMap=Request.classMap/>
                            <input type="hidden" name="type" value="TASK"/>
                            <input type="hidden" name="status" value="NEW"/>

                            <div class="span11">
                                <div class="toolbar transparent offset6">
                                    <input id="submit" class="info" type="submit" value="添加">
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>