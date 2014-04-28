<#import "macroUpload.ftl" as macroUpload>
<#assign mbSize = 1024 * 1024>
<#assign gbSize = 1024 * 1024 * 1024>
<#macro size size>
    <#local theSize = size?number>
    <#if theSize <= 1024>
    ${size}B
    <#elseif theSize <= mbSize>
    ${theSize / 1024}KB
    <#elseif theSize <= gbSize>
    ${theSize / mbSize}MB
    <#else>
    ${theSize / gbSize}GB
    </#if>
</#macro>
<#macro shareList shares>
    <#if shares?? && shares?size gt 0>
        <#list shares as share>
        <a class="list${(Session.teacher.name??)?string(" marked", "")}" href="/share/show/${share.id}">
            <div class="list-content">
                                        <span class="list-title">${share.title}<span
                                                class="place-right">${share.type.getName()}</span></span>
                <span class="list-subtitle">${share.editTime?string('yyyy-MM-dd hh:mm:ss')}</span>
                                        <span class="list-remark">${share.resources?size}个附件<span
                                                class="place-right">上传老师:${share.teacher.name}</span></span>
            </div>
        </a>
        </#list>
    <button class="button link">更多<i class="icon-forward"></i></button>
    <#else>
    <span>无数据</span>
    </#if>
</#macro>
<#macro addShareInput>
<div class="span8 no-margin">
    <div class="input-control text size3 block info-state span8"
         data-role="input-control">
        <input class="text-center" type="text" name="title" placeholder="标题">
        <button class="btn-clear" tabindex="-1"></button>
    </div>
</div>
<div class="input-control textarea span8"
     style="margin-top: 10px; margin-bottom: -10px;">
    <textarea id="post_body" name="content" class="textarea"
              placeholder="内容"></textarea>
</div>
<#--TODO 删除,打包,显示session里已经上传的-->
<div class="offset8">
    <div id="selectFile" class="button success large no-margin"><i
            class="icon-upload-3"></i></div>
    <div class="button warning large no-margin"><i
            class="icon-remove"></i></div>
    <div class="input-control checkbox span2 no-margin">
        <label>
            <input type="checkbox"/>
            <span class="check"></span>
            是否打包压缩
        </label>
    </div>
    <div id="manual-fine-uploader"></div>
    <@macroUpload.template/>
</div>
</#macro>