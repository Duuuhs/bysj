
<script type="text/javascript">
    /*控制用户信息的显示*/
    var identity = document.getElementById("identity").value;
    if (identity == "S"){
        $("#stuMessage").attr("style", "display:block;");
        $("#teaMessage").attr("style", "display:none;");
        $("#dmMessage").attr("style", "display:none;");
        $("#smMessage").attr("style", "display:none;");
    } else if (identity == "T" ){
        $("#stuMessage").attr("style", "display:none;");
        $("#teaMessage").attr("style", "display:block;");
        $("#dmMessage").attr("style", "display:none;");
        $("#smMessage").attr("style", "display:none;");
    } else if (identity == "DM"){
        $("#stuMessage").attr("style", "display:none;");
        $("#teaMessage").attr("style", "display:none;");
        $("#dmMessage").attr("style", "display:block;");
        $("#smMessage").attr("style", "display:none;");
    } else if (identity == "SM"){
        $("#stuMessage").attr("style", "display:none;");
        $("#teaMessage").attr("style", "display:none;");
        $("#dmMessage").attr("style", "display:none;");
        $("#smMessage").attr("style", "display:block;");
    }


</script>


<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">用户身份信息</h3>
    </div>
    <div class="panel-body">
        <div id="stuMessage">
            <table class="table">
                <!-- 学生信息 -->
                <thead>
                    <tr>
                        <th>学号:</th>
                        <th id="userId">${userMessage.userId}</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th>用户名:</th>
                        <th>${userMessage.userName}</th>
                    </tr>
                    <tr>
                        <th>身份:</th>
                        <th>学生</th>
                    </tr>

                    <tr>
                        <th>性别：</th>
                        <th>${stuMessage.student_sex}</th>
                    </tr>
                        <th>系别:</th>
                        <th>${stuMessage.departmenr_name}</th>
                    <tr>
                        <th>专业方向：</th>
                        <th>${stuMessage.major_name}</th>
                    </tr>
                    <tr>
                        <th>班级：</th>
                        <th>${stuMessage.class_name}</th>
                    </tr>
                    <tr>
                        <th>学习指导老师：</th>
                        <th>${stuMessage.study_teacher_name}</th>
                    </tr>
                    <tr>
                        <th>辅导员：</th>
                        <th>${stuMessage.tutor_teacher_name}</th>
                    </tr>
                    <tr>
                        <th>毕业设计指导老师：</th>
                        <th id="bysj_teacher_name">${stuMessage.bysj_teacher_name}</th>
                    </tr>
                    <tr style="display: none">
                        <th>毕业设计指导老师id：</th>
                        <th><input type="hidden" id="bysj_teacher_id" value="${stuMessage.bysj_teacher_id}"></th>
                    </tr>

                </tbody>
            </table>
        </div>

        <!-- 教师信息 -->
        <div id="teaMessage">
            <table class="table">
                <thead>
                    <tr>
                        <th>工号:</th>
                        <th id="teacher_id">${userMessage.userId}</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th>用户名:</th>
                        <th>${userMessage.userName}</th>
                    </tr>
                    <tr>
                        <th>身份:</th>
                        <th>教师</th>
                    </tr>
                    <tr>
                        <th>系别:</th>
                        <th>${teaMessage.deparement_name}</th>
                    </tr>
                    <tr>
                        <th>是否毕业生导师:</th>
                        <th><input type="hidden" id="hasBsStuValue" value="${teaMessage.hasBsStu}">
                            <script>
                                var hasBsStu = $("#hasBsStuValue").val();
                                if (hasBsStu == "T"){
                                    document.write("是");
                                } else {
                                    document.write("否");
                                }
                            </script>
                        </th>
                    </tr>
                    <tr>
                        <th>所带毕业生人数:</th>
                        <th>${teaMessage.bsStuCount}&nbsp;人</th>
                    </tr>
                </tbody>
            </table>
        </div>
        <!-- 部门管理员信息 -->
        <div id="dmMessage">
            <table class="table">
                <thead>
                <tr>
                    <th>身份:</th>
                    <th>部门管理员</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th>系别:</th>
                    <th id="DMdepart">${userMessage.department_str}</th>
                </tr>
                <tr style="display: none">
                    <th>系别ID:</th>
                    <th id="dm_department_Id">${userMessage.department}</th>
                </tr>
                <tr>
                    <th>本系教职工人数:</th>
                    <th>${dmMessage.teaCount}&nbsp;人</th>
                </tr>
                <tr>
                    <th>本系毕业生人数:</th>
                    <th>${dmMessage.stuCount}&nbsp;人</th>
                </tr>
                <tr>
                    <th>已确认选题毕业生人数:</th>
                    <th>${dmMessage.stuChoiceCount}&nbsp;人</th>
                </tr>
                </tbody>
            </table>
        </div>
        <!-- 超级管理员信息 -->
        <div id="smMessage">
            <table class="table">
                <thead>
                <tr>
                    <th>身份:</th>
                    <th>超级管理员</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th>学校院系数目:</th>
                    <th>${smMessage.departmentCount}&nbsp;人</th>
                </tr>
                <tr>
                    <th>教职工人数:</th>
                    <th>${smMessage.teaCount}&nbsp;人</th>
                </tr>
                <tr>
                    <th>毕业生人数:</th>
                    <th>${smMessage.stuCount}&nbsp;人</th>
                </tr>
                <tr>
                    <th>已确认选题毕业生人数:</th>
                    <th>${smMessage.stuChoiceCount}&nbsp;人</th>
                </tr>
                </tbody>
            </table>
        </div>
        <script type="text/javascript">
            /*控制用户信息的显示*/
            var identity = document.getElementById("identity").value;
            if (identity == "S"){
                $("#stuMessage").attr("style", "display:block;");
                $("#teaMessage").attr("style", "display:none;");
                $("#dmMessage").attr("style", "display:none;");
                $("#smMessage").attr("style", "display:none;");
            } else if (identity == "T" ){
                $("#stuMessage").attr("style", "display:none;");
                $("#teaMessage").attr("style", "display:block;");
                $("#dmMessage").attr("style", "display:none;");
                $("#smMessage").attr("style", "display:none;");
            } else if (identity == "DM"){
                $("#stuMessage").attr("style", "display:none;");
                $("#teaMessage").attr("style", "display:none;");
                $("#dmMessage").attr("style", "display:block;");
                $("#smMessage").attr("style", "display:none;");
            } else if (identity == "SM"){
                $("#stuMessage").attr("style", "display:none;");
                $("#teaMessage").attr("style", "display:none;");
                $("#dmMessage").attr("style", "display:none;");
                $("#smMessage").attr("style", "display:block;");
            }


        </script>
    </div>

</div>

