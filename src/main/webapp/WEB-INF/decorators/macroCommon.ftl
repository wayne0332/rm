<#assign appName="校园资源管理系统" />
<#macro importStyles>
<link href="/static/css/metro-bootstrap.css" rel="stylesheet">
<link href="/static/css/metro-bootstrap-responsive.css" rel="stylesheet">
<link href="/static/css/docs.css" rel="stylesheet">
<link href="/static/css/myCss.css" rel="stylesheet">
<link href="/static/js/common/prettify/prettify.css" rel="stylesheet">

<link href="/static/css/font-awesome.css" rel="stylesheet">
<link href="/static/css/jquery.qeditor.css" rel="stylesheet">
<link href="/static/css/common.css" rel="stylesheet">
</#macro>

<#macro importhead title head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>
    <#if title?? && title!="">
    ${appName}-${title}
    <#else>
    ${appName}
    </#if>
</title>
    <@importStyles/>
${head}
</#macro>

<#macro importScripts>
<!-- Load JavaScript Libraries -->
<script src="/static/js/common/jquery/jquery.min.js"></script>
<script src="/static/js/common/jquery/jquery.widget.min.js"></script>
<script src="/static/js/common/jquery/jquery.mousewheel.js"></script>
<script src="/static/js/common/prettify/prettify.js"></script>
<script src="/static/js/common/holder/holder.js"></script>
<!-- Metro UI CSS JavaScript plugins -->
<script>
    METRO_WEEK_START = 1;
</script>
<script src="/static/js/common/load-metro.js"></script>
<script src="/static/js/common/metro-notify.js"></script>

<#--other-->
<script src="/static/js/common/jquery.qeditor.js"></script>
<script src="/static/js/common/upload/jquery.fineuploader-4.1.0-13.min.js"></script>

<script src="/static/js/common.js"></script>
<script src="/static/js/loader.js"></script>
</#macro>

<#macro importBody body>
${body}
    <@importScripts/>
</#macro>

<#macro home role>
<a href="/back" class="element"><span class="icon-home fg-lightBlue"></span><span class="fg-lightBlue"> 主页</span></a>
<span class="element-divider"></span>
</#macro>

<#macro profile roleName>
<div class="element place-right fg-orange">
    <a class="dropdown-toggle" href="#">
        <span class="icon-user"></span> ${roleName}
    </a>
    <ul class="dropdown-menu place-right" data-role="dropdown">
        <li class="fg-lightBlue"><a href="#">编辑</a></li>
        <div class="divider"></div>
        <li class="fg-orange"><a href="/logout">注销</a></li>
    </ul>
</div>
</#macro>
<#macro studentHead student>
<header class="bg-dark" data-load="header.html">
    <div class="navigation-bar light fixed-top">
        <div class="navigation-bar-content container">
            <@home role='student'/>
            <a class="element brand" href="#"><span class="icon-newspaper"></span> 作业</a>
            <a class="element brand" href="#"><span class="icon-file"></span> 资源</a>
            <div class="no-tablet-portrait no-phone">
                <@profile roleName='${student.name}'/>
                <span class="element-divider place-right"></span>
            </div>
        </div>
    </div>
</header>
</#macro>
<#macro teacherHead teacher>
<header class="bg-dark" data-load="header.html">
    <div class="navigation-bar dark fixed-top">
        <div class="navigation-bar-content container">
            <@home role='teacher'/>
            <a class="element brand" href="#"><span class="icon-newspaper"></span> 作业</a>
            <a class="element brand" href="#"><span class="icon-file"></span> 资源</a>
            <div class="no-tablet-portrait no-phone">
                <@profile roleName='${teacher.name}'/>
                <span class="element-divider place-right"></span>
            </div>
        </div>
    </div>
</header>
</#macro>