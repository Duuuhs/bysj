<!-- 毕业生管理页面 -->
<script>

    /*部门管理员查看学生毕设选题按钮*/
    function btn_stu_pjyj_clickV2(studentName,studentId) {
        $("#readStuBsModalLable").text(studentName+"的毕业设计选题");
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "bschoice/getStuBsChoice.do?student_id="+studentId,

            success: function (data) {
                var stuBsChoice = data;
                if (stuBsChoice.bsChoiceId) {
                    $("#bsTopicNameVauleV3").text(stuBsChoice.bsTopicName);
                    /*答辩分数*/
                    if (stuBsChoice.replyPoint) {
                        if (stuBsChoice.replyPoint >= 60){
                            $("#replyPointValue3").text(stuBsChoice.replyPoint);
                            $("#replyPointValue3").attr("style", "color:green");
                        } else {
                            $("#replyPointValue3").text(stuBsChoice.replyPoint+"(不及格)");
                            $("#replyPointValue3").attr("style", "color:red");
                        }
                    } else {
                        $("#replyPointValue3").text("暂无!");
                        $("#replyPointValue3").attr("style", "color:black");
                    }
                    /*评阅分数*/
                    if (stuBsChoice.reviewPoint) {
                        if (stuBsChoice.reviewPoint >= 60){
                            $("#reviewPointValue3").text(stuBsChoice.reviewPoint);
                            $("#reviewPointValue3").attr("style", "color:green");
                        } else {
                            $("#reviewPointValue3").text(stuBsChoice.reviewPoint+"(不及格)");
                            $("#reviewPointValue3").attr("style", "color:red");
                        }
                    } else {
                        $("#reviewPointValue3").text("暂无!");
                        $("#reviewPointValue3").attr("style", "color:black");
                    }
                    /*指导老师指导意见*/
                    if (stuBsChoice.guidance) {
                        $("#guidanceValue3").text(stuBsChoice.guidance);
                    } else {
                        $("#guidanceValue3").text("暂无指导意见");
                    }

                } else {
                    /*还未选题情况*/
                    $("#bsTopicNameVauleV3").text("未选题");
                    $("#replyPointValue3").text("暂无");
                    $("#replyPointValue3").attr("style", "color:black");
                    $("#reviewPointValue3").text("暂无");
                    $("#reviewPointValue3").attr("style", "color:black");
                    $("#guidanceValue3").text("暂无指导意见");
                }
                $("#readStuBsModal").modal();
            },
            error: function (data) {
                alert("系统出错！");
            }
        });
    }
</script>




    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">毕业生管理</h3>
        </div>
        <div class="panel-body">

            <input type="text" placeholder="学号或姓名" id="queryIdOrName">
            <button class="btn btn-small btn-success " onclick="query_student_manger_click()">查询</button>
            <button class="btn btn-small btn-primary " onclick="student_manger_click(1,null,'F')">无毕设老师</button>


            <table class="table" id="stuMessageByDM">
                <thead>

                </thead>
                <tbody id="stuMessageByDMBody">

                </tbody>
            </table>

            <table class="table" id="stuMessageBySM">
                <thead>
                <#--<tr>
                    <th>#</th>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>系别</th>
                    <th>学习老师</th>
                    <th>辅导员</th>
                    <th>毕设选题</th>
                </tr>-->
                </thead>
                <tbody id="stuMessageBySMBody">

                </tbody>
            </table>
            <!-- 分页按钮 -->
            <div id="stuMessageManger_paging_btn" class="col-xs-8 col-md-8" ></div>
        </div>
    </div>


<#--控制表格显示-->
<script>
    var identity = document.getElementById("identity").value;
    if (identity == "DM"){
        $("#stuMessageByDM").attr("style", "display:block;");
        $("#stuMessageByDMBody").attr("style", "display:block;");
        $("#stuMessageBySM").attr("style", "display:none;");
        $("#stuMessageBySMBody").attr("style", "display:none;");
    } else if (identity == "SM"){
        $("#stuMessageByDM").attr("style", "display:none;");
        $("#stuMessageByDMBody").attr("style", "display:none;");
        $("#stuMessageBySM").attr("style", "display:block;");
        $("#stuMessageBySMBody").attr("style", "display:block;");
    } else if (identity == "SM"){
    }
</script>
<script>
    function query_student_manger_click() {
        var queryIdOrName = $("#queryIdOrName").val();
        console.info(queryIdOrName);
        student_manger_click(1,queryIdOrName,null);
    }
</script>


<!-- 查看学生毕设选题情况的显示 -->
<div class="modal fade" id="readStuBsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="readStuBsModalLable"></h4>
            </div>
            <div class="modal-body">
                <div class="panel-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>毕设题目</th>
                            <td id="bsTopicNameVauleV3"></td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th>答辩分数</th>
                            <td id="replyPointValue3"></td>
                        </tr>
                        <tr>
                            <th>评阅分数</th>
                            <td id="reviewPointValue3"></td>
                        </tr>
                        <tr>
                            <th rowspan="2">指导意见</th>
                            <td id="guidanceValue3" rowspan="2"></td>
                        </tr>
                        </tbody>

                    </table>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal""><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>确定</button>
            </div>
        </div>
    </div>
</div>






<!-- 给未有毕业设计老师的学生分配情况的显示 -->
<div class="modal fade" id="readTeaModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="readTeaModalLable"></h4>
            </div>
            <div class="modal-body">
                <div class="input-group">
                    <div class="input-group-btn">
                        <input type="hidden" class="form-control" id="stuHidden" readonly>
                        <input type="hidden" class="form-control" id="teaHidden"  readonly>
                        <input type="text" class="form-control" id="teaName" style="width: 80%" readonly>
                        <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            可选老师<span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" id="dropdown_menu_bysjTea">

                        </ul>
                    </div>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                <button type="button" id="btn_addnotice_submit" class="btn btn-primary" data-dismiss="modal" onclick="btn_addTea_submit_fun()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>分配</button>
            </div>
        </div>
    </div>
</div>


<script>
    /*
	 * 动态显示选项数据
	 */
    $("#dropdown_menu_bysjTea").on("click", "li", function(){
        $("#teaName").val($(this).text());
        $("#teaHidden").val($(this).val());
    });
</script>


