<!--  学生毕业选题界面 -->

<!-- 按钮 -->
<#include "common/student/stu_byxt_page_btn.ftl">

<div id="panel-group">

    <div class="row">
        <!-- 可选题目 -->
        <div id="stu_kxtm" class="col-xs-10 col-md-10" style="display: none">
            <#include "common/student/stu_byxt_page_kxtm.ftl">
        </div>
        <!-- 已选题目 -->
        <div id="stu_yxtm" class="col-xs-8 col-md-8" style="display: none">
            <#include "common/student/stu_byxt_page_yxtm.ftl">
        </div>
        <!-- 查看公告 -->
        <div id="stu_ckgg" class="col-xs-10 col-md-10" style="display: none">
            <#include "common/student/stu_byxt_page_ckgg.ftl">
        </div>
        <!-- 下载资源 -->
        <div id="stu_xzzy" class="col-xs-8 col-md-8" style="display: none">
            <#include "common/student/stu_byxt_page_xzzy.ftl">
        </div>
        <!-- 评价及意见 -->
        <div id="stu_pjyj" class="col-xs-8 col-md-8" style="display: none">
            <#include "common/student/stu_byxt_page_pjyj.ftl">
        </div>

    </div>


</div>
