<#import "macroCommon.ftl" as macroCommon>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<@macroCommon.importhead title='${title}' head='${head}'/>
</head>
<body class="metro">
<#if Session.student?? && Session.student.name??>
    <@macroCommon.studentHead student=Session.student/>
<#elseif Session.teacher?? && Session.teacher.name??>
    <@macroCommon.teacherHead teacher=Session.teacher/>
</#if>
<@macroCommon.importBody body='${body}'/>
</body>
</html>