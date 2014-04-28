<#include "/includeEnum.ftl">
<#macro selectClass classMap>
<div class="tab-control span8 no-margin" data-role="tab-control" data-effect="fade">
    <ul class="tabs">
        <#if classMap?? && classMap?size gt 0>
            <#list classMap?keys as year>
                <li ${(year_index==0)?string("class='active'","")}><a class="fg-grayLight infoTab"
                                                                      href="#_page_${year}">${year}</a>
                </li>
            </#list>
        <#else>
            <li class="active">
                <a class="fg-grayLight infoTab"
                   href="#_no_data">无班级</a>
            </li>
        </#if>
    </ul>

    <div class="frames" style="border-color: #4390df;">
        <#if classMap?? && classMap?size gt 0>
            <#list classMap?keys as year>
                <div class="frame" id="_page_${year}" style="display: block;">
                    <div class="input-control checkbox" data-role="input-control">
                        <#assign students=classMap[year]>
                        <#list students as clazz>
                            <label class="inline-block fg-gray">
                                <input type="checkbox" name="clazzs" value="${clazz.id}">
                                <span class="check"></span>
                            ${clazz.name}
                            </label>
                        </#list>
                    </div>
                </div>
            </#list>
        <#else>
            <div class="frame" id="_no_data" style="display: block;">
                <div class="input-control checkbox" data-role="input-control">
                    <label class="inline-block fg-gray">
                        <input type="checkbox" disabled>
                        <span class="check"></span>
                        无班级
                    </label>
                </div>
            </div>
        </#if>
    </div>
</div>
</#macro>
<#macro datePick>
<div class="span11 row" style="margin-left: 0px;">
    <div class="span3">
        <div class="input-control text info-state" data-role="datepicker" data-other-days="1" data-week-start="1"
             data-locale="zhCN" data-effect="slide" data-format="yyyy-mm-dd">
            <input type="text" name="starTime" readonly="readonly" placeholder="开始日期">
            <button class="btn-date" type="button"></button>
        </div>
    </div>
    <h6 class="span2 text-center"><i class="icon-arrow-right"></i></h6>

    <div class="span3">
        <div class="input-control text info-state" data-role="datepicker" data-week-start="1" data-locale="zhCN"
             data-effect="slide" data-format="yyyy-mm-dd">
            <input type="text" name="endTime" readonly="readonly" placeholder="结束日期">
            <button class="btn-date" type="button"></button>
        </div>
    </div>
</div>
</#macro>
<#macro taskList tasks linkUrl="/task/show/">
    <#if tasks?? && tasks?size gt 0>
        <#list tasks as task>
        <a class="list${(Session.teacher.name??)?string(" marked", "")}" href="${linkUrl + task.id}">
            <div class="list-content">
                                        <span class="list-title">${task.share.title}<span
                                                class="place-right">${(task.status==TaskStatus.NEW)?string(task.share.type.getName(), "提交人数:" + task.homeworks?size)}</span></span>
                <span class="list-subtitle">${task.starTime?string('yyyy-MM-dd')}
                    ~ ${task.endTime?string('yyyy-MM-dd')}</span>
                                        <span class="list-remark">${task.teacher.subject.college.name}
                                            - ${task.teacher.subject.name}<span
                                                    class="place-right">发布老师:${task.teacher.name}</span></span>
            </div>
        </a>
        </#list>
    <button class="button link">更多<i class="icon-forward"></i></button>
    <#else>
    <span>无数据</span>
    </#if>
</#macro>
<#macro listHomework homeworkMap>
<div class="tab-control no-margin" data-role="tab-control" data-effect="fade">
    <ul class="tabs">
        <#if homeworkMap?? && homeworkMap?size gt 0>
            <#list homeworkMap?keys as clazzName>
                <li ${(clazzName_index==0)?string("class='active'","")}><a class="fg-grayLight infoTab"
                                                                           href="#_page_${clazzName}">${clazzName}</a>
                </li>
            </#list>
        <#else>
            <li class="active">
                <a class="fg-grayLight infoTab"
                   href="#_no_data">无班级</a>
            </li>
        </#if>
    </ul>

    <div class="frames" style="border-color: #4390df;">
        <#if homeworkMap?? && homeworkMap?size gt 0>
            <#list homeworkMap?keys as clazzName>
                <div class="frame" id="_page_${clazzName}" style="display: block;">
                    <div class="input-control checkbox" data-role="input-control">
                        <#assign students=homeworkMap[clazzName]>
                        <#list students as homework>
                            <a class="shortcut link" href="/task/checkHomework/${homework.id}">
                                <i class="icon-book"></i>
                            ${homework.student.name}
                                <small class="bg-lightBlue fg-white">${(homework.score??)?string(homework.score?if_exists, "无")}</small>
                            </a>
                        </#list>
                    </div>
                </div>
            </#list>
        <#else>
            <div class="frame" id="_no_data" style="display: block;">
                <div class="input-control checkbox" data-role="input-control">
                    <label class="inline-block fg-gray">
                        <input type="checkbox" disabled>
                        <span class="check"></span>
                        无班级
                    </label>
                </div>
            </div>
        </#if>
    </div>
</div>
</#macro>
<#macro listNotSubmit studentMap>
<div class="tab-control no-margin" data-role="tab-control" data-effect="fade">
    <ul class="tabs">
        <#if studentMap?? && studentMap?size gt 0>
            <#list studentMap?keys as clazzName>
                <li ${(clazzName_index==0)?string("class='active'","")}><a class="fg-grayLight infoTab"
                                                                           href="#_page_${clazzName}">${clazzName}</a>
                </li>
            </#list>
        <#else>
            <li class="active">
                <a class="fg-grayLight infoTab"
                   href="#_no_data">无班级</a>
            </li>
        </#if>
    </ul>

    <div class="frames" style="border-color: #4390df;">
        <#if studentMap?? && studentMap?size gt 0>
            <#list studentMap?keys as clazzName>
                <div class="frame" id="_page_${clazzName}" style="display: block;">
                    <div class="input-control checkbox" data-role="input-control">
                        <span class="margin10 padding10"></span>
                        <#assign students=studentMap[clazzName]>
                        <#list students as student>
                            <span class="text-center padding10 margin10 bg-lightBlue fg-white span2">${student.name}</span>
                        </#list>
                    </div>
                </div>
            </#list>
        <#else>
            <div class="frame" id="_no_data" style="display: block;">
                <div class="input-control checkbox" data-role="input-control">
                    <label class="inline-block fg-gray">
                        <input type="checkbox" disabled>
                        <span class="check"></span>
                        无班级
                    </label>
                </div>
            </div>
        </#if>
    </div>
</div>
</#macro>