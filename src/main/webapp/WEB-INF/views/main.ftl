<span style="font-size:18px;"><!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta content="width=device-width,initial-scale=1,user-scalable=0" name="viewport" />
<meta content="telephone=no" name="format-detection" />

<title>华软毕业设计管理系统</title>
<script src="https://cdn.staticfile.org/jquery/2.2.4/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/echarts/4.2.0-rc.2/echarts.js"></script>




<script src="statics/localjs/navigation.js"></script>

<style>
	body{
		margin-top: 65px;
	}
	input{
		background:transparent;
		border: 1px solid #ccc;
		padding: 4px 0px;
		border-radius: 3px; /*css3属性IE不支持*/
		padding-left:5px;
	}
	td{
		text-align: center;
	}
	.col-md-2{
		background-color: #ccc;
		/*?*/
		height: 600px;
	}
	#left-content{
		margin-top: 20px;
	}
	.list-group{
		padding-left: 0;
	}
	#panel-group{
		margin-top: 20px;
	}
	#onebtn{
		margin: 10px 0;
	}
	#btngroup button{
		width: 100%;
	}
	.progress{
		margin-top: 15px;
	}
	/*#list-function li{
		padding: 3px 20px;
		clear: both;
		font-weight: 400;
		line-height: 1.42857143;
		color: #333;
		white-space: nowrap;
	}*/



</style>

</head>

<body>

<input type="hidden" id="identity" name="identity" value="${userMessage.identity}">
<#--<input type="hidden" id="userId" name="userId" value="${userMessage.userId}">-->

<#include "common/navigation.ftl">

<div class="container-fluid">
	<div class="row">

		<div id="user_message_page" class="col-xs-9 col-md-9" style="display: block">
			<#include "common/user_message_page.ftl">
		</div>

		<div id="stu_byxt_page" class="col-xs-12 col-md-12" style="display: none">
			<#include "common/stu_byxt_page.ftl">
		</div>

		<div id="tea_byxtsz_page" class="col-xs-12 col-md-12" style="display: none">
			<#include "common/tea_byxtsz_page.ftl">
		</div>

		<div id="student_manger_page" class="col-xs-12 col-md-12" style="display: none">
			<#include "common/dmanger/student_manger_page.ftl">
		</div>

		<div id="teacher_manger_page" class="col-xs-12 col-md-12" style="display: none">
			<#include "common/dmanger/teacher_manger_page.ftl">
		</div>

		<div id="add_user_page" class="col-xs-12 col-md-12" style="display: none">
			<#include "common/smanger/add_user_page.ftl">
		</div>

        <div id="statistical_page" class="col-xs-12 col-md-12" style="display: none">
			<#include "common/dmanger/statistical_page.ftl">
		</div>

	</div>



</div>





</body>
</html>
