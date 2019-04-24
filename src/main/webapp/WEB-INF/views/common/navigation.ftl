<script type="text/javascript">

	function user_message_click() {
		$("#user_message_page").attr("style", "display: block;");
		$("#stu_byxt_page").attr("style", "display:none;");
		$("#tea_byxtsz_page").attr("style", "display:none;");
        $("#student_manger_page").attr("style", "display:none;");
        $("#teacher_manger_page").attr("style", "display:none;");
        $("#add_user_page").attr("style", "display:none;");
        $("#statistical_page").attr("style", "display:none;");
	};

	function stu_byxt_click() {
		$("#user_message_page").attr("style", "display: none;");
		$("#stu_byxt_page").attr("style", "display:block;");
		$("#tea_byxtsz_page").attr("style", "display:none;");
        $("#student_manger_page").attr("style", "display:none;");
        $("#teacher_manger_page").attr("style", "display:none;");
        $("#add_user_page").attr("style", "display:none;");
        $("#statistical_page").attr("style", "display:none;");
	};

	function tea_byxtsz_click() {
		$("#user_message_page").attr("style", "display: none;");
		$("#stu_byxt_page").attr("style", "display:none;");
		$("#tea_byxtsz_page").attr("style", "display:block;");
        $("#student_manger_page").attr("style", "display:none;");
        $("#teacher_manger_page").attr("style", "display:none;");
        $("#add_user_page").attr("style", "display:none;");
        $("#statistical_page").attr("style", "display:none;");
	};


    /*
     *数据统计
     */
    function statistical_click() {
        $("#user_message_page").attr("style", "display: none;");
        $("#stu_byxt_page").attr("style", "display:none;");
        $("#tea_byxtsz_page").attr("style", "display:none;");
        $("#student_manger_page").attr("style", "display:none;");
        $("#teacher_manger_page").attr("style", "display:none;");
        $("#add_user_page").attr("style", "display:none;");
        $("#statistical_page").attr("style", "display:block;");
    }


	/*管理员查看该系学生的情况*/
	function student_manger_click(page_num,student,hasBsTea) {
		var identity = document.getElementById("identity").value;
		if (student == null){
			student = "";
		}
		if (hasBsTea == null){
			hasBsTea = "";
		}
		if (identity == "DM") {
			var department_id = $("#dm_department_Id").text();
			$.ajax({
				type: "GET",
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				url: "bschoice/getStuMessageByManger.do?page_num=" + page_num + "&base_department_id=" + department_id+"&student="+student+"&hasBsTea="+hasBsTea,
				success: function (data) {
					/* 界面属性改变*/
					$("#user_message_page").attr("style", "display: none;");
					$("#stu_byxt_page").attr("style", "display:none;");
					$("#tea_byxtsz_page").attr("style", "display:none;");
					$("#student_manger_page").attr("style", "display:block;");
					$("#teacher_manger_page").attr("style", "display:none;");
					$("#add_user_page").attr("style", "display:none;");
                    $("#statistical_page").attr("style", "display:none;");

					var stuMessageByMangerPage = data;
					if (stuMessageByMangerPage) {
						/*去掉原有的tr*/
						$("#stuMessageByDMBody > tr").remove();
						$("#stuMessageByDMBody").append('<tr>\n' +
								'                        <th>#</th>\n' +
								'                        <th>学号</th>\n' +
								'                        <th>姓名</th>\n' +
								'                        <th>专业方向</th>\n' +
								'                        <th>班级</th>\n' +
								'                        <th>学习老师</th>\n' +
								'                        <th>辅导员</th>\n' +
								'                        <th>毕业设计老师</th>\n' +
								'                        <th>毕设选题</th>\n' +
								'                    </tr>')
						stuMessageByMangerPage.stuMessageByMangers.forEach(function (stuMessage, index) {
							var studentId = stuMessage.studentId;
							var studentName = stuMessage.studentName;
							var majorName;
							if (stuMessage.majorName != null) {
								majorName = stuMessage.majorName;
							} else {
								majorName = "暂无";
							}
							var baseClassName;
							if (stuMessage.baseClassName != null) {
								baseClassName = stuMessage.baseClassName;
							} else {
								baseClassName = "暂无";
							}
							var studyTeacherName;
							if (stuMessage.studyTeacherName != null) {
								studyTeacherName = stuMessage.studyTeacherName;
							} else {
								studyTeacherName = "暂无";
							}
							var tutorTeacherName;
							if (stuMessage.tutorTeacherName != null) {
								tutorTeacherName = stuMessage.tutorTeacherName;
							} else {
								tutorTeacherName = "暂无";
							}
							var bsTeacherName;
							if (stuMessage.bysjTeacherName != null){
								bsTeacherName = stuMessage.bysjTeacherName;
							} else {
								bsTeacherName = '<button type="button" class="btn btn-success btn-sm" onclick="add_teacher_btn('+studentId+',\''+studentName+'\')">\n' +
										'<span class="glyphicon glyphicon-plus-sign"></span> 分配</button>'
							}
							var readStuBsBtn = '<button type="button" class="btn btn-primary btn-sm" onclick="btn_stu_pjyj_clickV2(\'' + studentName + '\',' + studentId + ')">' +
									'<span class="glyphicon glyphicon-search"></span> 查看</button>';

							var str = '<tr><td>' + (index + 1) + '</td><td>' + studentId + '</td><td>' + studentName +
									'</td><td>' + majorName + '</td><td>' + baseClassName + '</td><td>' + studyTeacherName +
									'</td><td>' + tutorTeacherName + '</td><td>' + bsTeacherName + '</td><td>' + readStuBsBtn + '</td></tr>';

							/*循环拼接内容*/
							$("#stuMessageByDMBody").append(str);

						});
						/*跳页按钮*/
						var btn = '<div class="input-group col-md-3">\n' +
								'    <div class="input-group-btn">\n' +
								'        <button type="button" class="btn btn-default" onclick="student_manger_click(1,\''+student+'\',\''+hasBsTea+'\')">首页</button>\n' +
								'        <button type="button" id="bsTopicPageByDM_before" class=" btn btn-default glyphicon glyphicon-chevron-left" onclick="student_manger_click(' + (stuMessageByMangerPage.pageNum - 1) + ',\''+student+'\',\''+hasBsTea+'\')"></button>\n' +
								'    </div>\n' +
								'    <span class="input-group-addon">' + stuMessageByMangerPage.pageNum + '</span>\n' +
								'    <div class="input-group-btn">\n' +
								'        <button type="button" id="bsTopicPageByDM_after"  class="btn btn-default glyphicon glyphicon-chevron-right" onclick="student_manger_click(' + (stuMessageByMangerPage.pageNum + 1) + ',\''+student+'\',\''+hasBsTea+'\')"></button>\n' +
								'        <button type="button" class="btn btn-default" onclick="student_manger_click(' + stuMessageByMangerPage.pageMax + ',\''+student+'\',\''+hasBsTea+'\')">尾页</button>\n' +
								'    </div>' +
								'    <span class="input-group-addon">共' + stuMessageByMangerPage.stuCount + '条数据</span>\n' +
								'    <span class="input-group-addon">共' + stuMessageByMangerPage.pageMax + '页</span>\n' +
								'    <input type="text" class="form-control" id="bsTopicPageByDMNumInput" style="width:45px;"/>\n' +
								'    ' +
								'<span class="input-group-btn">\n' +
								'         <button class="btn btn-danger" id="stuBsChoiceSkip" onclick="student_manger_clickV2(\'' + student + '\',\'' + hasBsTea + '\')">跳转</button>\n' +
								'    </span>\n' +
								'</div>';
						$("#stuMessageManger_paging_btn > div").remove();
						$("#stuMessageManger_paging_btn").append(btn);
						/*按钮控制显示*/
						if (stuMessageByMangerPage.pageMax < 2) {
							$("#stuMessageManger_paging_btn").attr("style", "display:none;");
						} else {
							$("#stuMessageManger_paging_btn").attr("style", "display:block;");
						}
						if (stuMessageByMangerPage.pageNum == 1) {
							$("#bsTopicPageByDM_before").attr("disabled", true)
						}
						if (stuMessageByMangerPage.pageNum == stuMessageByMangerPage.pageMax) {
							$("#bsTopicPageByDM_after").attr("disabled", true);
						}
					} else {
						alert("暂无毕业生信息");
					}

				},
				error: function (data) {
					alert("系统出错！");
				}
			});
		} else if (identity == "SM"){
			$.ajax({
				type: "GET",
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				url: "bschoice/getStuMessageByMangerV2.do?page_num=" + page_num+"&student="+student+"&hasBsTea="+hasBsTea,
				success: function (data) {
					/* 界面属性改变*/
					$("#user_message_page").attr("style", "display: none;");
					$("#stu_byxt_page").attr("style", "display:none;");
					$("#tea_byxtsz_page").attr("style", "display:none;");
					$("#student_manger_page").attr("style", "display:block;");
					$("#teacher_manger_page").attr("style", "display:none;");
					$("#add_user_page").attr("style", "display:none;");
                    $("#statistical_page").attr("style", "display:none;");

					var stuMessageByMangerPage = data;
					if (stuMessageByMangerPage) {
						/*去掉原有的tr*/
						$("#stuMessageBySMBody > tr").remove();
						$("#stuMessageBySMBody").append('<tr>\n' +
								'                    <th>#</th>\n' +
								'                    <th>学号</th>\n' +
								'                    <th>姓名</th>\n' +
								'                    <th>系别</th>\n' +
								'                    <th>学习老师</th>\n' +
								'                    <th>辅导员</th>\n' +
								'                    <th>毕业设计老师</th>\n' +
								'                    <th>毕设选题</th>\n' +
								'                </tr>');
						stuMessageByMangerPage.stuMessageByMangerV2s.forEach(function (stuMessage, index) {
							var studentId = stuMessage.studentId;
							var studentName = stuMessage.studentName;
							var baseDepartmentName;
							if (stuMessage.baseDepartmentName != null) {
								baseDepartmentName = stuMessage.baseDepartmentName;
							} else {
								baseDepartmentName = "暂无";
							}
							var studyTeacherName;
							if (stuMessage.studyTeacherName != null) {
								studyTeacherName = stuMessage.studyTeacherName;
							} else {
								studyTeacherName = "暂无";
							}
							var tutorTeacherName;
							if (stuMessage.tutorTeacherName != null) {
								tutorTeacherName = stuMessage.tutorTeacherName;
							} else {
								tutorTeacherName = "暂无";
							}
							var bsTeacherName;
							if (stuMessage.bysjTeacherName != null){
								bsTeacherName = stuMessage.bysjTeacherName;
							} else {
								bsTeacherName = "暂无";
							}
							var readStuBsBtn = '<button type="button" class="btn btn-primary btn-sm" onclick="btn_stu_pjyj_clickV2(\'' + studentName + '\',' + studentId + ')">' +
									'<span class="glyphicon glyphicon-search"></span> 查看</button>';

							var str = '<tr><td>' + (index + 1) + '</td><td>' + studentId + '</td><td>' + studentName +
									'</td><td>' + baseDepartmentName + '</td><td>' + studyTeacherName + '</td><td>' + tutorTeacherName +
									'</td><td>' + bsTeacherName + '</td><td>' + readStuBsBtn + '</td></tr>';

							/*循环拼接内容*/
							$("#stuMessageBySMBody").append(str);

						});
						/*跳页按钮*/
						var btn = '<div class="input-group col-md-3">\n' +
								'    <div class="input-group-btn">\n' +
								'        <button type="button" class="btn btn-default" onclick="student_manger_click(1,\'' + student + '\',\'' + hasBsTea + '\')">首页</button>\n' +
								'        <button type="button" id="bsTopicPageByDM_before" class=" btn btn-default glyphicon glyphicon-chevron-left" onclick="student_manger_click(' + (stuMessageByMangerPage.pageNum - 1) + ',\'' + student + '\',\'' + hasBsTea + '\')"></button>\n' +
								'    </div>\n' +
								'    <span class="input-group-addon">' + stuMessageByMangerPage.pageNum + '</span>\n' +
								'    <div class="input-group-btn">\n' +
								'        <button type="button" id="bsTopicPageByDM_after" class="btn btn-default glyphicon glyphicon-chevron-right" onclick="student_manger_click(' + (stuMessageByMangerPage.pageNum + 1) + ',\'' + student + '\',\'' + hasBsTea + '\')"></button>\n' +
								'        <button type="button" class="btn btn-default" onclick="student_manger_click(' + stuMessageByMangerPage.pageMax + ',\'' + student + '\',\'' + hasBsTea + '\')">尾页</button>\n' +
								'    </div>' +
								'    <span class="input-group-addon">共' + stuMessageByMangerPage.stuCount + '条数据</span>\n' +
								'    <span class="input-group-addon">共' + stuMessageByMangerPage.pageMax + '页</span>\n' +
								'    <input type="text" class="form-control" id="bsTopicPageBySMNumInput" style="width:45px;"/>\n' +
								'    ' +
								'<span class="input-group-btn">\n' +
								'         <button class="btn btn-danger" id="stuBsChoiceSkip" onclick="student_manger_clickV2(\'' + student + '\',\'' + hasBsTea + '\')">跳转</button>\n' +
								'    </span>\n' +
								'</div>';
						$("#stuMessageManger_paging_btn > div").remove();
						$("#stuMessageManger_paging_btn").append(btn);
						/*按钮控制显示*/
						if (stuMessageByMangerPage.pageMax < 2) {
							$("#stuMessageManger_paging_btn").attr("style", "display:none;");
						} else {
							$("#stuMessageManger_paging_btn").attr("style", "display:block;");
						}
						if (stuMessageByMangerPage.pageNum == 1) {
							$("#bsTopicPageByDM_before").attr("disabled", true)
						}
						if (stuMessageByMangerPage.pageNum == stuMessageByMangerPage.pageMax) {
							$("#bsTopicPageByDM_after").attr("disabled", true);
						}
					} else {
						alert("暂无毕业生信息");
					}

				},
				error: function (data) {
					alert("系统出错！");
				}
			});
		}
	};


	/*管理员查看该系学生的情况(跳页)*/
	function student_manger_clickV2(student,hasBsTea) {
		var identity = document.getElementById("identity").value;
		if (student == null){
			student = "";
		}
		if (hasBsTea == null){
			hasBsTea = "";
		}
		var page_num;
		if (identity == "SM"){
			page_num = $("#bsTopicPageBySMNumInput").val();
		} else if (identity == "DM"){
			page_num = $("#bsTopicPageByDMNumInput").val();
		}
		if (identity == "DM") {
			var department_id = $("#dm_department_Id").text();
			$.ajax({
				type: "GET",
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				url: "bschoice/getStuMessageByManger.do?page_num=" + page_num + "&base_department_id=" + department_id+"&student="+student+"&hasBsTea="+hasBsTea,
				success: function (data) {
					/* 界面属性改变*/
					$("#user_message_page").attr("style", "display: none;");
					$("#stu_byxt_page").attr("style", "display:none;");
					$("#tea_byxtsz_page").attr("style", "display:none;");
					$("#student_manger_page").attr("style", "display:block;");
					$("#teacher_manger_page").attr("style", "display:none;");
					$("#add_user_page").attr("style", "display:none;");
                    $("#statistical_page").attr("style", "display:none;");

					var stuMessageByMangerPage = data;
					if (stuMessageByMangerPage) {
						/*去掉原有的tr*/
						$("#stuMessageByDMBody > tr").remove();
						$("#stuMessageByDMBody").append('<tr>\n' +
								'                        <th>#</th>\n' +
								'                        <th>学号</th>\n' +
								'                        <th>姓名</th>\n' +
								'                        <th>专业方向</th>\n' +
								'                        <th>班级</th>\n' +
								'                        <th>学习老师</th>\n' +
								'                        <th>辅导员</th>\n' +
								'                    	 <th>毕业设计老师</th>\n' +
								'                        <th>毕设选题</th>\n' +
								'                    </tr>')
						stuMessageByMangerPage.stuMessageByMangers.forEach(function (stuMessage, index) {
							var studentId = stuMessage.studentId;
							var studentName = stuMessage.studentName;
							var majorName;
							if (stuMessage.majorName != null) {
								majorName = stuMessage.majorName;
							} else {
								majorName = "暂无";
							}
							var baseClassName;
							if (stuMessage.baseClassName != null) {
								baseClassName = stuMessage.baseClassName;
							} else {
								baseClassName = "暂无";
							}
							var studyTeacherName;
							if (stuMessage.studyTeacherName != null) {
								studyTeacherName = stuMessage.studyTeacherName;
							} else {
								studyTeacherName = "暂无";
							}
							var tutorTeacherName;
							if (stuMessage.tutorTeacherName != null) {
								tutorTeacherName = stuMessage.tutorTeacherName;
							} else {
								tutorTeacherName = "暂无";
							}
							var bsTeacherName;
							if (stuMessage.bysjTeacherName != null){
								bsTeacherName = stuMessage.bysjTeacherName;
							} else {
								bsTeacherName = '<button type="button" class="btn btn-success btn-sm" onclick="add_teacher_btn('+studentId+',\''+studentName+'\')">\n' +
										'<span class="glyphicon glyphicon-plus-sign"></span> 分配</button>'
							}
							var readStuBsBtn = '<button type="button" class="btn btn-primary btn-sm" onclick="btn_stu_pjyj_clickV2(\'' + studentName + '\',' + studentId + ')">' +
									'<span class="glyphicon glyphicon-search"></span> 查看</button>';

							var str = '<tr><td>' + (index + 1) + '</td><td>' + studentId + '</td><td>' + studentName +
									'</td><td>' + majorName + '</td><td>' + baseClassName + '</td><td>' + studyTeacherName +
									'</td><td>' + tutorTeacherName + '</td><td>' + bsTeacherName + '</td><td>' + readStuBsBtn + '</td></tr>';

							/*循环拼接内容*/
							$("#stuMessageByDMBody").append(str);

						});
						/*跳页按钮*/
						var btn = '<div class="input-group col-md-3">\n' +
								'    <div class="input-group-btn">\n' +
								'        <button type="button" class="btn btn-default" onclick="student_manger_click(1,\'' + student + '\',\'' + hasBsTea + '\')">首页</button>\n' +
								'        <button type="button" id="bsTopicPageByDM_before" class=" btn btn-default glyphicon glyphicon-chevron-left" onclick="student_manger_click(' + (stuMessageByMangerPage.pageNum - 1) + ',\'' + student + '\',\'' + hasBsTea + '\')"></button>\n' +
								'    </div>\n' +
								'    <span class="input-group-addon">' + stuMessageByMangerPage.pageNum + '</span>\n' +
								'    <div class="input-group-btn">\n' +
								'        <button type="button" id="bsTopicPageByDM_after" class="btn btn-default glyphicon glyphicon-chevron-right" onclick="student_manger_click(' + (stuMessageByMangerPage.pageNum + 1) + ',\'' + student + '\',\'' + hasBsTea + '\')"></button>\n' +
								'        <button type="button" class="btn btn-default" onclick="student_manger_click(' + stuMessageByMangerPage.pageMax + ',\'' + student + '\',\'' + hasBsTea + '\')">尾页</button>\n' +
								'    </div>' +
								'    <span class="input-group-addon">共' + stuMessageByMangerPage.stuCount + '条数据</span>\n' +
								'    <span class="input-group-addon">共' + stuMessageByMangerPage.pageMax + '页</span>\n' +
								'    <input type="text" class="form-control" id="bsTopicPageByDMNumInput" style="width:45px;"/>\n' +
								'    ' +
								'<span class="input-group-btn">\n' +
								'         <button class="btn btn-danger" id="stuBsChoiceSkip" onclick="student_manger_clickV2(\'' + student + '\',\'' + hasBsTea + '\')">跳转</button>\n' +
								'    </span>\n' +
								'</div>';
						$("#stuMessageManger_paging_btn > div").remove();
						$("#stuMessageManger_paging_btn").append(btn);
						/*按钮控制显示*/
						if (stuMessageByMangerPage.pageMax < 2) {
							$("#stuMessageManger_paging_btn").attr("style", "display:none;");
						} else {
							$("#stuMessageManger_paging_btn").attr("style", "display:block;");
						}
						if (stuMessageByMangerPage.pageNum == 1) {
							$("#bsTopicPageByDM_before").attr("disabled", true)
						}
						if (stuMessageByMangerPage.pageNum == stuMessageByMangerPage.pageMax) {
							$("#bsTopicPageByDM_after").attr("disabled", true);
						}
					} else {
						alert("暂无毕业生信息");
					}

				},
				error: function (data) {
					alert("系统出错！");
				}
			});
		} else if (identity == "SM") {
			$.ajax({
				type: "GET",
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				url: "bschoice/getStuMessageByMangerV2.do?page_num=" + page_num+"&student="+student+"&hasBsTea="+hasBsTea,
				success: function (data) {
					/* 界面属性改变*/
					$("#user_message_page").attr("style", "display: none;");
					$("#stu_byxt_page").attr("style", "display:none;");
					$("#tea_byxtsz_page").attr("style", "display:none;");
					$("#student_manger_page").attr("style", "display:block;");
					$("#teacher_manger_page").attr("style", "display:none;");
					$("#add_user_page").attr("style", "display:none;");
                    $("#statistical_page").attr("style", "display:none;");

					var stuMessageByMangerPage = data;
					if (stuMessageByMangerPage) {
						/*去掉原有的tr*/
						$("#stuMessageBySMBody > tr").remove();
						$("#stuMessageBySMBody").append('<tr>\n' +
								'                    <th>#</th>\n' +
								'                    <th>学号</th>\n' +
								'                    <th>姓名</th>\n' +
								'                    <th>系别</th>\n' +
								'                    <th>学习老师</th>\n' +
								'                    <th>辅导员</th>\n' +
								'                    <th>毕业设计老师</th>\n' +
								'                    <th>毕设选题</th>\n' +
								'                </tr>');
						stuMessageByMangerPage.stuMessageByMangerV2s.forEach(function (stuMessage, index) {
							var studentId = stuMessage.studentId;
							var studentName = stuMessage.studentName;
							var baseDepartmentName;
							if (stuMessage.baseDepartmentName != null) {
								baseDepartmentName = stuMessage.baseDepartmentName;
							} else {
								baseDepartmentName = "暂无";
							}
							var studyTeacherName;
							if (stuMessage.studyTeacherName != null) {
								studyTeacherName = stuMessage.studyTeacherName;
							} else {
								studyTeacherName = "暂无";
							}
							var tutorTeacherName;
							if (stuMessage.tutorTeacherName != null) {
								tutorTeacherName = stuMessage.tutorTeacherName;
							} else {
								tutorTeacherName = "暂无";
							}
							var bsTeacherName;
							if (stuMessage.bysjTeacherName != null){
								bsTeacherName = stuMessage.bysjTeacherName;
							} else {
								bsTeacherName = "暂无"
							}
							var readStuBsBtn = '<button type="button" class="btn btn-primary btn-sm" onclick="btn_stu_pjyj_clickV2(\'' + studentName + '\',' + studentId + ')">' +
									'<span class="glyphicon glyphicon-search"></span> 查看</button>';

							var str = '<tr><td>' + (index + 1) + '</td><td>' + studentId + '</td><td>' + studentName +
									'</td><td>' + baseDepartmentName + '</td><td>' + studyTeacherName +
									'</td><td>' + tutorTeacherName + '</td><td>' + bsTeacherName + '</td><td>' + readStuBsBtn + '</td></tr>';

							/*循环拼接内容*/
							$("#stuMessageBySMBody").append(str);

						});
						/*跳页按钮*/
						var btn = '<div class="input-group col-md-3">\n' +
								'    <div class="input-group-btn">\n' +
								'        <button type="button" class="btn btn-default" onclick="student_manger_click(1,\'' + student + '\',\'' + hasBsTea + '\')">首页</button>\n' +
								'        <button type="button" id="bsTopicPageByDM_before" class=" btn btn-default glyphicon glyphicon-chevron-left" onclick="student_manger_click(' + (stuMessageByMangerPage.pageNum - 1) + ',\'' + student + '\',\'' + hasBsTea + '\')"></button>\n' +
								'    </div>\n' +
								'    <span class="input-group-addon">' + stuMessageByMangerPage.pageNum + '</span>\n' +
								'    <div class="input-group-btn">\n' +
								'        <button type="button" id="bsTopicPageByDM_after" class="btn btn-default glyphicon glyphicon-chevron-right" onclick="student_manger_click(' + (stuMessageByMangerPage.pageNum + 1) + ',\'' + student + '\',\'' + hasBsTea + '\')"></button>\n' +
								'        <button type="button" class="btn btn-default" onclick="student_manger_click(' + stuMessageByMangerPage.pageMax + ')">尾页</button>\n' +
								'    </div>' +
								'    <span class="input-group-addon">共' + stuMessageByMangerPage.stuCount + '条数据</span>\n' +
								'    <span class="input-group-addon">共' + stuMessageByMangerPage.pageMax + '页</span>\n' +
								'    <input type="text" class="form-control" id="bsTopicPageBySMNumInput" style="width:45px;"/>\n' +
								'    ' +
								'<span class="input-group-btn">\n' +
								'         <button class="btn btn-danger" id="stuBsChoiceSkip" onclick="student_manger_clickV2(\'' + student + '\',\'' + hasBsTea + '\')">跳转</button>\n' +
								'    </span>\n' +
								'</div>';
						$("#stuMessageManger_paging_btn > div").remove();
						$("#stuMessageManger_paging_btn").append(btn);
						/*按钮控制显示*/
						if (stuMessageByMangerPage.pageMax < 2) {
							$("#stuMessageManger_paging_btn").attr("style", "display:none;");
						} else {
							$("#stuMessageManger_paging_btn").attr("style", "display:block;");
						}
						if (stuMessageByMangerPage.pageNum == 1) {
							$("#bsTopicPageByDM_before").attr("disabled", true)
						}
						if (stuMessageByMangerPage.pageNum == stuMessageByMangerPage.pageMax) {
							$("#bsTopicPageByDM_after").attr("disabled", true);
						}
					} else {
						alert("暂无毕业生信息");
					}

				},
				error: function (data) {
					alert("系统出错！");
				}
			});
		}
	};


	/*管理员查看教师的情况(DM/SM)*/
	function teacher_manger_click(page_num) {
		var department_id = $("#dm_department_Id").text();
		$.ajax({
			type: "GET",
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			url: "bschoice/getTeaMessageByManger.do?page_num=" + page_num+"&base_department_id="+department_id,
			success: function (data) {
				/* 界面属性改变*/
				$("#user_message_page").attr("style", "display: none;");
				$("#stu_byxt_page").attr("style", "display:none;");
				$("#tea_byxtsz_page").attr("style", "display:none;");
				$("#student_manger_page").attr("style", "display:none;");
				$("#teacher_manger_page").attr("style", "display:block;");
				$("#add_user_page").attr("style", "display:none;");
                $("#statistical_page").attr("style", "display:none;");

				var teaMessageByManger = data;
				if (teaMessageByManger) {
					/*去掉原有的tr*/
					$("#teaMessageByMangerBody > tr").remove();
					$("#teaMessageByMangerBody").append('<tr>\n' +
							'                    <th>#</th>\n' +
							'                    <th>工号</th>\n' +
							'                    <th>姓名</th>\n' +
							'                    <th>系别</th>\n' +
							'                    <th>毕设学生数</th>\n' +
							'                    <th>毕设题目</th>\n' +
							'                </tr>');
					teaMessageByManger.teaList.forEach(function (teacher, index) {
						var teacherId = teacher.teacherId;
						var teacherName = teacher.teacherName;
						var baseDepartmentName = teacher.baseDepartmentName;
						var stuCount = teacher.stuCount;
						var readStu = "";
						if (stuCount > 0){
							readStu = '<a href="#" onclick="getTeaBsStu('+teacherId+',\''+teacherName+'\')">'+stuCount+'</a>';
						} else {
							readStu = stuCount;
						}
						var readBsTopicBtn = '<button type="button" class="btn btn-success btn-sm" onclick="getTeaBs('+teacherId+',\''+teacherName+'\')">' +
								'<span class="glyphicon glyphicon-search"></span> 查看</button>';
						var str = '<tr><td>' + (index + 1) + '</td><td>' + teacherId + '</td><td>' + teacherName +
								'</td><td>' + baseDepartmentName + '</td><td>' + readStu +
								'</td><td>' + readBsTopicBtn + '</td></tr>';

						/*循环拼接内容*/
						$("#teaMessageByMangerBody").append(str);

					});
					/*跳页按钮*/
					var btn = '<div class="input-group col-md-3">\n' +
							'    <div class="input-group-btn">\n' +
							'        <button type="button" class="btn btn-default" onclick="teacher_manger_click(1)">首页</button>\n' +
							'        <button type="button" id="teaPageByManger_before" class=" btn btn-default glyphicon glyphicon-chevron-left" onclick="teacher_manger_click(' + (teaMessageByManger.pageNum - 1) + ')"></button>\n' +
							'    </div>\n' +
							'    <span class="input-group-addon">' + teaMessageByManger.pageNum + '</span>\n' +
							'    <div class="input-group-btn">\n' +
							'        <button type="button" id="teaPageByManger_after" class="btn btn-default glyphicon glyphicon-chevron-right" onclick="teacher_manger_click(' + (teaMessageByManger.pageNum + 1) + ')"></button>\n' +
							'        <button type="button" class="btn btn-default" onclick="teacher_manger_click(' + teaMessageByManger.pageMax + ')">尾页</button>\n' +
							'    </div>' +
							'    <span class="input-group-addon">共' + teaMessageByManger.teaCount + '条数据</span>\n' +
							'    <span class="input-group-addon">共' + teaMessageByManger.pageMax + '页</span>\n' +
							'    <input type="text" class="form-control" id="teaPageByMangerNumInput" style="width:45px;"/>\n' +
							'    ' +
							'<span class="input-group-btn">\n' +
							'         <button class="btn btn-danger" id="stuBsChoiceSkip" onclick="teacher_manger_clickV2()">跳转</button>\n' +
							'    </span>\n' +
							'</div>';
					$("#teaMessageByManger_paging_btn > div").remove();
					$("#teaMessageByManger_paging_btn").append(btn);
					/*按钮控制显示*/
					if (teaMessageByManger.pageMax < 2) {
						$("#teaMessageByManger_paging_btn").attr("style", "display:none;");
					} else {
						$("#teaMessageByManger_paging_btn").attr("style", "display:block;");
					}
					if (teaMessageByManger.pageNum == 1) {
						$("#teaPageByManger_before").attr("disabled", true)
					}
					if (teaMessageByManger.pageNum == teaMessageByManger.pageMax) {
						$("teaPageByManger_after").attr("disabled", true);
					}
				} else {
					alert("暂无教师信息");
				}

			},
			error: function (data) {
				alert("系统出错！");
			}
		});
	}



	/*管理员查看教师的情况(DM/SM)(跳页)*/
	function teacher_manger_clickV2() {
		var page_num = $("#teaPageByMangerNumInput").val();
		var department_id = $("#dm_department_Id").text();
		$.ajax({
			type: "GET",
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			url: "bschoice/getTeaMessageByManger.do?page_num=" + page_num+"&base_department_id="+department_id,
			success: function (data) {
				/* 界面属性改变*/
				$("#user_message_page").attr("style", "display: none;");
				$("#stu_byxt_page").attr("style", "display:none;");
				$("#tea_byxtsz_page").attr("style", "display:none;");
				$("#student_manger_page").attr("style", "display:none;");
				$("#teacher_manger_page").attr("style", "display:block;");
				$("#add_user_page").attr("style", "display:none;");
                $("#statistical_page").attr("style", "display:none;");

				var teaMessageByManger = data;
				if (teaMessageByManger) {
					/*去掉原有的tr*/
					$("#teaMessageByMangerBody > tr").remove();
					$("#teaMessageByMangerBody").append('<tr>\n' +
							'                    <th>#</th>\n' +
							'                    <th>工号</th>\n' +
							'                    <th>姓名</th>\n' +
							'                    <th>系别</th>\n' +
							'                    <th>毕设学生数</th>\n' +
							'                    <th>毕设题目</th>\n' +
							'                </tr>');
					teaMessageByManger.teaList.forEach(function (teacher, index) {
						var teacherId = teacher.teacherId;
						var teacherName = teacher.teacherName;
						var baseDepartmentName = teacher.baseDepartmentName;
						var stuCount = teacher.stuCount;
						var readStu = "";
						if (stuCount > 0){
							readStu = '<a href="#" onclick="getTeaBsStu('+teacherId+',\''+teacherName+'\')">'+stuCount+'</a>';
						} else {
							readStu = stuCount;
						}
						var readBsTopicBtn = '<button type="button" class="btn btn-success btn-sm" onclick="getTeaBs('+teacherId+',\''+teacherName+'\')">' +
								'<span class="glyphicon glyphicon-search"></span> 查看</button>';
						var str = '<tr><td>' + (index + 1) + '</td><td>' + teacherId + '</td><td>' + teacherName +
								'</td><td>' + baseDepartmentName + '</td><td>' + readStu +
								'</td><td>' + readBsTopicBtn + '</td></tr>';

						/*循环拼接内容*/
						$("#teaMessageByMangerBody").append(str);

					});
					/*跳页按钮*/
					var btn = '<div class="input-group col-md-3">\n' +
							'    <div class="input-group-btn">\n' +
							'        <button type="button" class="btn btn-default" onclick="teacher_manger_click(1)">首页</button>\n' +
							'        <button type="button" id="teaPageByManger_before" class=" btn btn-default glyphicon glyphicon-chevron-left" onclick="teacher_manger_click(' + (teaMessageByManger.pageNum - 1) + ')"></button>\n' +
							'    </div>\n' +
							'    <span class="input-group-addon">' + teaMessageByManger.pageNum + '</span>\n' +
							'    <div class="input-group-btn">\n' +
							'        <button type="button" id="teaPageByManger_after" class="btn btn-default glyphicon glyphicon-chevron-right" onclick="teacher_manger_click(' + (teaMessageByManger.pageNum + 1) + ')"></button>\n' +
							'        <button type="button" class="btn btn-default" onclick="teacher_manger_click(' + teaMessageByManger.pageMax + ')">尾页</button>\n' +
							'    </div>' +
							'    <span class="input-group-addon">共' + teaMessageByManger.teaCount + '条数据</span>\n' +
							'    <span class="input-group-addon">共' + teaMessageByManger.pageMax + '页</span>\n' +
							'    <input type="text" class="form-control" id="teaPageByMangerNumInput" style="width:45px;"/>\n' +
							'    ' +
							'<span class="input-group-btn">\n' +
							'         <button class="btn btn-danger" id="stuBsChoiceSkip" onclick="teacher_manger_clickV2()">跳转</button>\n' +
							'    </span>\n' +
							'</div>';
					$("#teaMessageByManger_paging_btn > div").remove();
					$("#teaMessageByManger_paging_btn").append(btn);
					/*按钮控制显示*/
					if (teaMessageByManger.pageMax < 2) {
						$("#teaMessageByManger_paging_btn").attr("style", "display:none;");
					} else {
						$("#teaMessageByManger_paging_btn").attr("style", "display:block;");
					}
					if (teaMessageByManger.pageNum == 1) {
						$("#teaPageByManger_before").attr("disabled", true)
					}
					if (teaMessageByManger.pageNum == teaMessageByManger.pageMax) {
						$("teaPageByManger_after").attr("disabled", true);
					}
				} else {
					alert("暂无教师信息");
				}

			},
			error: function (data) {
				alert("系统出错！");
			}
		});
	}



	/*
	 * 查看老师所带毕业设计学生
	 */
	function getTeaBsStu(teacher_id,teacherName) {
		$.ajax({
			type: "GET",
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			url: "baseTeacher/selectTeaBsStus.do?teacher_id="+teacher_id,
			success: function (data) {
				$("#getTeaBsStuModalLable").text(teacherName+"老师的毕业设计学生");
				var teaQueryNotChoiceStus = data;
				if (teaQueryNotChoiceStus){
					$("#getTeaBsStuModalBody > tr").remove();
					teaQueryNotChoiceStus.forEach(function (student,index) {
						var studentId = student.studentId;
						var studentName = student.studentName;
						var str = '<tr><th>'+(index+1)+'</th><th>'+studentId+'</th><th>'+studentName+'</th></tr>';
						$("#getTeaBsStuModalBody").append(str);
					});

					$("#getTeaBsStuModal").modal();
				} else {
					alert("暂无毕业设计学生信息！");
				}

			},
			error: function (data) {
				alert("系统出错！");
			}
		});
	}



	/*
	 *查看教师所出的毕业设计题目
	 */
	function getTeaBs(teacher_id,teacherName) {
		$.ajax({
			type: "GET",
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			url: "baseTeacher/selectTeaBsTopic.do?teacher_id="+teacher_id,
			success: function (data) {
				$("#getTeaBsModalLable").text(teacherName+"老师的出题");
				var bsTopicContents = data;
				if (bsTopicContents != null){
					$("#getTeaBsModalBody > tr").remove();
					bsTopicContents.forEach(function (bsTopic,index) {
						var bsTopicId = bsTopic.bsTopicId;
						var bsTopicName = bsTopic.bsTopicName;
						var str = '<tr><th>'+(index+1)+'</th><th>'+bsTopicName+'</th></tr>';
						$("#getTeaBsModalBody").append(str);
					});

					$("#getTeaBsModal").modal();
				} else {
					alert("暂无毕业设计学生信息！");
				}

			},
			error: function (data) {
				alert("系统出错！");
			}
		});
	}




	/*
	 * 部门管理员给未分配毕设老师的学生分配老师的按钮
	 */
	function add_teacher_btn(student_id,student_name) {
		$.ajax({
			type: "GET",
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			url: "bschoice/getTeaListByStu.do?student_id="+student_id,
			success: function (data) {
				var queryTeaLists = data;
				if (queryTeaLists != null){
					//remove掉之前的选择
					$("#teaHidden").val("");
					$("#teaName").val("");
					//隐藏学生idcenter
					$("#stuHidden").val(student_id);
					//li选项
					$("#dropdown_menu_bysjTea > li").remove();
					queryTeaLists.forEach(function (queryTea) {
						var teaLi = '<li value="'+queryTea.teacherId+'" align="center">'+queryTea.teacherName+'</li>';
						$("#dropdown_menu_bysjTea").append(teaLi);
					});
					//标题
					$("#readTeaModalLable").text(student_name+"的毕业设计老师分配");
					$("#readTeaModal").modal();
				} else {
					alert("暂无教师信息！");
				}

			},
			error: function (data) {
				alert("系统出错！");
			}
		});
	}



	/*
	 * 部门管理员给未分配毕设老师的学生分配老师的保存按钮
	 */
	function btn_addTea_submit_fun() {
		var student_id = $("#stuHidden").val();
		var teacher_id = $("#teaHidden").val();
		console.info(student_id+","+teacher_id);
		$.ajax({
			type: "GET",
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			url: "basestudent/addBsTeacher.do?student_id="+student_id+"&bysj_teacher_id="+teacher_id,
			success: function (data) {
				alert(data.message);
			},
			error: function (data) {
				alert("系统出错！");
			}
		});
	}

	


	/*
	 * 超级管理员添加系统用户
	 */
	function add_user_click() {
		$("#user_message_page").attr("style", "display: none;");
		$("#stu_byxt_page").attr("style", "display:none;");
		$("#tea_byxtsz_page").attr("style", "display:none;");
        $("#student_manger_page").attr("style", "display:none;");
        $("#teacher_manger_page").attr("style", "display:none;");
        $("#add_user_page").attr("style", "display:block;");
        $("#statistical_page").attr("style", "display:none;");
	}
</script>



<nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
    <!--container-fluid表示自适应大小，container表示居中-->
	<div class="container">
		<!--定义顶部导航栏-->
		<div class="navbar-header">
			<!--button是当屏幕小于多少时出现的可以打开导航列表的按钮-->
			<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#demo-navbar">
				<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">华软毕业设计管理系统</a>
		</div>

		<!--collapse用于导航栏折叠之后数据在button按钮里点击显示-->
		<div class="collapse navbar-collapse" id="demo-navbar">
			<!--nav navbar-nav表示显示在导航栏里-->
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">首页</a></li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">功能<span class="caret"></span></a>
						<ul id="list_function" class="dropdown-menu" role="menu">
						<!--通过dropdown-header来标记一组动作-->
							<li id="user_message" onclick="user_message_click()"><a>查看个人信息</a></li>
							<li id="stu_byxt" onclick="stu_byxt_click()"><a>毕业选题</a></li>
							<li id="tea_byxtsz" onclick="tea_byxtsz_click()"><a>毕业选题设置</a></li>
							<li id="student_mangerId" onclick="student_manger_click(1,null,null)"><a>毕业生管理</a></li>
							<li id="teacher_mangerId" onclick="teacher_manger_click(1)"><a>教职工管理</a></li>
							<li id="add_userId" onclick="add_user_click()"><a>注册用户</a></li>
                            <li id="statistical" onclick="statistical_click()"><a>统计数据</a></li>
							<script>
								var identity = document.getElementById("identity").value;
								if (identity == "S"){
									$("#user_message").attr("style", "display:block;");
									$("#stu_byxt").attr("style", "display:block;");
									$("#tea_byxtsz").attr("style", "display:none;");
                                    $("#student_mangerId").attr("style", "display:none;");
                                    $("#teacher_mangerId").attr("style", "display:none;");
                                    $("#add_userId").attr("style", "display:none;");
                                    $("#statistical").attr("style", "display:none;");
									//此处的变量用于user_message_page.ftl
									var user_title = "学号";
								} else if (identity == "T"){
									$("#user_message").attr("style", "display:block;");
									$("#stu_byxt").attr("style", "display:none;");
									$("#tea_byxtsz").attr("style", "display:block;");
                                    $("#student_mangerId").attr("style", "display:none;");
                                    $("#teacher_mangerId").attr("style", "display:none;");
                                    $("#add_userId").attr("style", "display:none;");
                                    $("#statistical").attr("style", "display:none;");
									var user_title = "工号";
								} else if (identity == "DM"){;
                                    $("#user_message").attr("style", "display:block;");
                                    $("#stu_byxt").attr("style", "display:none;");
                                    $("#tea_byxtsz").attr("style", "display:none;");
                                    $("#student_mangerId").attr("style", "display:block;");
                                    $("#teacher_mangerId").attr("style", "display:block;");
                                    $("#add_userId").attr("style", "display:none;");
                                    $("#statistical").attr("style", "display:block;");
								} else if (identity == "SM"){
                                    $("#user_message").attr("style", "display:block;");
                                    $("#stu_byxt").attr("style", "display:none;");
                                    $("#tea_byxtsz").attr("style", "display:none;");
                                    $("#student_mangerId").attr("style", "display:block;");
                                    $("#teacher_mangerId").attr("style", "display:block;");
                                    $("#add_userId").attr("style", "display:block;");
                                    $("#statistical").attr("style", "display:block;");
								}
							</script>
						</ul>
				</li>

				<li><a href="#" data-toggle="modal" data-target="#about">帮助</a></li>

				<!--input必须得实现form-control才有样式效果-->
				<li></li>
                <li id="STshow" style="display: none"><a>欢迎您: <B>${userMessage.userName}</B>${userMessage.identity_str}  </a></li>
				<li id="SMDMshow" style="display: none"><a>欢迎您: ${userMessage.identity_str}  </a></li>
				<script>
					var identity = document.getElementById("identity").value;
					if (identity == "SM" || identity == "DM"){
						$("#STshow").attr("style", "display:none;");
						$("#SMDMshow").attr("style", "display:block;");
					} else if (identity == "S" || identity == "T"){
						$("#STshow").attr("style", "display:block;");
						$("#SMDMshow").attr("style", "display:none;");
					}
				</script>
				<!--btn btn-default表示默认样式-->
                <li class="active"><a href="index.ftl">退出登录</a></li>

			</ul>
		</div>
	</div>
</nav>

