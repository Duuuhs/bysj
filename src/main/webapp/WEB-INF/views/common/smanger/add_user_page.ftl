
<!-- 添加系统用户 -->
<div class="panel-body">
    <div class="container">
        <div class="form row">
            <div class="form-horizontal col-md-offset-3" >
                <h3 class="form-title">添加系统用户</h3>
                <div class="col-md-9">
                    <#--<form id="add_user" name="add_user" action=""  >-->
                        <div class="form-group">
                            <i class="fa fa-user fa-lg"></i>
                            <input class="form-control required" type="text" placeholder="工号/学号" id="addd_userId"  autofocus="autofocus" maxlength="20"/>
                        </div>
                        <div class="form-group">
                            <i class="fa fa-lock fa-lg"></i>
                            <input class="form-control required" type="text" placeholder="姓名" id="add_username"  />
                        </div>
                        <!--身份-->
                        <div class="form-group">
                            <i class="fa fa-tag fa-lg"></i>
                            <select class="form-control required" id="add_identity"  >
                                <option value="" disabled selected class="placeholder">身份</option>
                                <option value="S">学生</option>
                                <option value="T">教师</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <i class="fa fa-tree-conifer fa-lg"></i>
                            <select class="form-control required" id="add_departmentId"  >
                                <option value="" disabled selected class="placeholder">系别</option>
                                <option value="1">软件工程系</option>
                                <option value="2">电子系</option>
                                <option value="3">网络技术系</option>
                                <option value="4">游戏系</option>
                                <option value="5">数码媒体系</option>
                                <option value="6">外语系</option>
                                <option value="7">管理系</option>
                                <option value="8">国际贸易系</option>
                                <option value="9">财会系</option>
                                <option value="10">计算机系</option>
                            </select>
                        </div>
                        <div class="form-group col-md-offset-9">
                            <button type="submit" class="btn btn-success pull-right"  onclick="addUserClick()">添加</button>
                        </div>

                       <#-- <form>-->
                </div>
            </div>
        </div>
    </div>
</div>




<div class="modal fade" id="completeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="addNoticeModalLabel">完善学生信息</h4>
            </div>
            <!--学号-->
            <div class="modal-body">
                <div class="form-group">
                    <i class="fa fa-user fa-lg"></i>
                    <input class="form-control required" type="text"  id="complete_userId"  autofocus="autofocus" maxlength="20" readonly/>
                </div>
            </div>
            <!--姓名-->
            <div class="modal-body">
                <i class="fa fa-user fa-lg"></i>
                <input class="form-control required" type="text"  id="complete_userName"  autofocus="autofocus" maxlength="20" readonly/>
            </div>
            <!--性别-->
            <div class="modal-body">
                <i class="fa fa-tag fa-lg"></i>
                <select class="form-control required" id="complete_sex"  >
                    <option value="" disabled selected class="placeholder">性别</option>
                    <option value="0">女</option>
                    <option value="1">男</option>
                </select>
            </div>
            <!--专业方向-->
            <div class="modal-body">
                <i class="fa fa-tag fa-lg"></i>
                <select class="form-control required" id="complete_major">
                </select>
            </div>
            <!--班级-->
            <div class="modal-body">
                <i class="fa fa-tree-conifer fa-lg"></i>
                <select class="form-control required" id="complete_class"  >
                </select>
            </div>
            <!--学习老师-->
            <div class="modal-body">
                <i class="fa fa-tree-conifer fa-lg"></i>
                <select class="form-control required" id="complete_studyTeacher"  >
                </select>
            </div>
            <!--辅导员-->
            <div class="modal-body">
                <i class="fa fa-tree-conifer fa-lg"></i>
                <select class="form-control required" id="complete_tutorTeacher"  >
                </select>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                <button type="button" id="btn_addnotice_submit" class="btn btn-primary" data-dismiss="modal" onclick="saveUserComplete()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button>
            </div>
        </div>
    </div>
</div>


<script>
    /*添加用户*/
    function addUserClick() {
        var addd_userId = $("#addd_userId").val();
        var add_username = $("#add_username").val();
        var add_identity = $("#add_identity").val();
        var add_departmentId = $("#add_departmentId").val();
        if (addd_userId == null ){
            $("#addd_userId").focus();
            alert("工号或学号不能为空!!");
            return false;
        } else if (isNaN(addd_userId)) {
            $("#addd_userId").focus();
            alert("用户名应该为数字!!");
            return false;
        }
        if (add_username == null){
            $("#add_username").focus();
            alert("用户名不能为空!!");
            return false;
        }
        if (add_identity == null){
            $("#add_identity").focus();
            alert("身份必须选择!!");
            return false;
        }
        if (add_departmentId == null){
            $("#add_departmentId").focus();
            alert("系别必须选择!!");
            return false;
        }
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "addUser.do?add_userId="+addd_userId+"&add_username="+add_username+"&add_identity="+add_identity+"&add_departmentId="+add_departmentId,

            success: function (data) {
                var message = data.rollback;
                alert(data.message);
                $("#addd_userId").val('');
                $("#add_username").val('');
                $("#add_identity").val('');
                $("#add_departmentId").val('');
                if (data.status == 200){
                    var status = confirm("新增了一个学生用户,是否接着完善该学生信息？");
                    if (status){
                        /*完善学生用户的信息的弹窗及渲染*/
                        $.ajax({
                            type: "GET",
                            dataType: "json",
                            contentType: "application/json; charset=utf-8",
                            url: "getcompleteStuInfo.do?message="+message,

                            success: function (data) {
                                var updateStuInfo = data;
                                if (updateStuInfo){
                                    var student_id = updateStuInfo.student_id;
                                    var student_name = updateStuInfo.student_name;
                                    var classes = updateStuInfo.classess;
                                    var majors = updateStuInfo.majors;
                                    var teachers = updateStuInfo.teachers;
                                    if (student_id){//学号
                                        $("#complete_userId").val(student_id);
                                    }
                                    if (student_name){//姓名
                                        $("#complete_userName").val(student_name);
                                    }
                                    if (majors != null){//专业方向
                                         $("#complete_major > option").remove();
                                        $("#complete_major").append('<option value="" disabled selected >&nbsp;&nbsp;专业方向</option>');
                                        majors.forEach(function (major) {
                                            var majorId = major.majorId;
                                            var majorName = major.majorName;
                                            var majorOption = '<option value="'+majorId+'">'+majorName+'</option>';
                                            $("#complete_major").append(majorOption);
                                        });
                                    }
                                    if (classes != null){//班级
                                        $("#complete_class > option").remove();
                                        $("#complete_class").append('<option value="" disabled selected class="placeholder">&nbsp;&nbsp;班级</option>');
                                        classes.forEach(function (classs) {
                                            var baseClassId = classs.baseClassId;
                                            var baseClassName = classs.baseClassName;
                                            var classOption = '<option value="'+baseClassId+'">'+baseClassName+'</option>';
                                            $("#complete_class").append(classOption);
                                        });
                                    }
                                    if (teachers != null){//学习导师与辅导员
                                        $("#complete_studyTeacher > option").remove();
                                        $("#complete_studyTeacher").append('<option value="" disabled selected class="placeholder">&nbsp;&nbsp;学习导师</option>');
                                        $("#complete_tutorTeacher > option").remove();
                                        $("#complete_tutorTeacher").append('<option value="" disabled selected class="placeholder">&nbsp;&nbsp;辅导员</option>');
                                        teachers.forEach(function (teacher) {
                                            var teacherId = teacher.teacherId;
                                            var teacherName = teacher.teacherName;
                                            var teacherOption = '<option value="'+teacherId+'">'+teacherName+'</option>';
                                            $("#complete_studyTeacher").append(teacherOption);
                                            $("#complete_tutorTeacher").append(teacherOption);
                                        });
                                    }
                                    /*展示学生用户更新信息的dialog*/
                                    $("#completeModal").modal();
                                }
                            },
                            error: function (data) {
                                alert("系统出错！");
                            }
                        });
                    }
                }
            },
            error: function (data) {
                alert("系统出错！");
            }
        });
    }


/*保存用户完善的信息*/
function saveUserComplete() {
    var student_id = $("#complete_userId").val();
    var student_sex = $("#complete_sex").val();
    var major_id = $("#complete_major").val();
    var class_id = $("#complete_class").val();
    var study_teacher_id = $("#complete_studyTeacher").val();
    var tutor_teacher_id = $("#complete_tutorTeacher").val();

    if (student_id == null || student_sex == null || major_id == null || class_id == null || study_teacher_id == null || tutor_teacher_id == null) {
        alert("有未选的选项!");
        return false;
    }

    $.ajax({
        type: "GET",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "savecompleteStuInfo.do?student_id="+student_id+"&student_sex="+student_sex+"&major_id="+major_id+"&class_id="+class_id+"&study_teacher_id="+study_teacher_id+"&tutor_teacher_id="+tutor_teacher_id,

        success: function (data) {
            alert(data.message);

        },
        error: function (data) {
            alert("系统出错！");
        }
    });

}


</script>