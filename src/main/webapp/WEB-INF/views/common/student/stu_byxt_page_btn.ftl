<!--  学生毕业选题界面按钮框  -->

<script type="text/javascript">
    /*可选题目触发事件*/
    function btn_stu_kxtm_click() {

        var bs_teacher_id = $("#bysj_teacher_id").val();
        var student_id = $("#userId").text();
        /* 调用getTeaBsTopics()方法*/
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "bstopic/getTeaBsTopicsV2.do?bs_teacher_id="+bs_teacher_id+"&student_id="+student_id,
            success: function (data) {
                /* 界面属性改变*/
                $("#stu_kxtm").attr("style", "display:block;");
                $("#stu_yxtm").attr("style", "display:none;");
                $("#stu_ckgg").attr("style", "display:none;");
                $("#stu_xzzy").attr("style", "display:none;");
                $("#stu_pjyj").attr("style", "display:none;");


                if (data.teaBsTopics && data.teaBsTopics.bsTopics) {
                    var bsTopics = data.teaBsTopics.bsTopics;
                    /*去掉原有的tr和td*/
                    $("#bsTbody > tr").remove();
                    bsTopics.forEach(function (bsTopic,index) {
                            var isChoice = bsTopic.isChoice;
                            var isChoice_str = null;

                            /*$("#bsTbody").removeChild();*/
                            if (isChoice == 'T') {
                                isChoice_str = '<button id="choiceBs" class="btn" disabled="true">被选</button>';
                            } else {
                                isChoice_str = '<button id="choiceBs" class="btn btn-primary" onclick="btn_choiceBs_click('+bsTopic.bsTopicId+')">选择</button>';
                            }
                            var str = '<tr> <th>'+(index+1)+'</th><th>'+bsTopic.bsTopicName+'</th> <th>'+isChoice_str+'</th> <th style="display: none" id="bsTopicIdValue">'+bsTopic.bsTopicId+'</th> </tr>';
                            $("#bsTbody").append(str);
                         }

                    );
                }
                var hasChoice = data.hasChoice;
                var hasTeaChoice = data.hasTeaChoice;
                $("#hasChoiceValue").attr("value", hasChoice);
                $("#hasTeaChoiceValue").attr("value", hasTeaChoice);

            },
            error: function (data) {
                alert(data);
            }
        });

    }



    /*已选题目触发事件*/
    function btn_stu_yxtm_click() {
        var userId = $("#userId").text();
        /* 调用getStuBsChoice()方法*/
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "bschoice/getStuBsChoice.do?student_id="+userId,
            success: function (data) {
                /* 界面属性改变*/
                $("#stu_kxtm").attr("style", "display:none;");
                $("#stu_yxtm").attr("style", "display:block;");
                $("#stu_ckgg").attr("style", "display:none;");
                $("#stu_xzzy").attr("style", "display:none;");
                $("#stu_pjyj").attr("style", "display:none;");

                var stuBsChoice = data;
                if (stuBsChoice.bsChoiceId) {
                    $("#bsTopicNameVaule").text(stuBsChoice.bsTopicName);
                    $("#isSureVaule").text(stuBsChoice.isSure);
                } else {
                    /*还未选题情况*/
                    $("#bsTopicNameVaule").text("未选题");
                    $("#isSureVaule").text("未选题");
                }

            },
            error: function (data) {
                alert(data);
            }
        });



    };


    /*查看公告触发事件*/
    function btn_stu_ckgg_click() {
        var bs_teacher_id = $("#bysj_teacher_id").val();
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "bsNotice/getBsNotices.do?bs_teacher_id="+bs_teacher_id,
            success: function (data) {
                /* 界面属性改变*/
                $("#stu_kxtm").attr("style", "display:none;");
                $("#stu_yxtm").attr("style", "display:none;");
                $("#stu_ckgg").attr("style", "display:block;");
                $("#stu_xzzy").attr("style", "display:none;");
                $("#stu_pjyj").attr("style", "display:none;");

                var bsNotices = data;
                if (bsNotices){
                    /*去掉原有的textarea*/
                    $("#noticeValue > blockquote").remove();
                    var bysj_teacher_name = $("#bysj_teacher_name").text();
                    bsNotices.forEach(function(bsNotice, index){
                        var bsNoticeContent = bsNotice.bsNoticeContent;
                        var bsNoticeDate = bsNotice.bsNoticeDate;
                        var str = '<blockquote><p><span class="glyphicon glyphicon-comment">&nbsp;&nbsp;'+bsNoticeContent+'</p>' +
                            '<cite class="pull-right"><small>由'+bysj_teacher_name+'老师于'+bsNoticeDate+'发布</small></cite>' +
                            '</blockquote>';

                        /*循环拼接内容*/
                        $("#noticeValue").append(str);
                    });
                } else {
                    alert("暂无公告信息");
                }

            },
            error: function (data) {
                alert(data);
            }
        });


    }

    /*查看教师发布资源触发事件*/
    function btn_stu_xzzy_click() {
        var teacher_id = $("#bysj_teacher_id").val();
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "file/getTeaUpLoad.do?teacher_id="+teacher_id,
            success: function (data) {
                /* 界面属性改变*/
                $("#stu_kxtm").attr("style", "display:none;");
                $("#stu_yxtm").attr("style", "display:none;");
                $("#stu_ckgg").attr("style", "display:none;");
                $("#stu_xzzy").attr("style", "display:block;");
                $("#stu_pjyj").attr("style", "display:none;");

                var teaUpLoad = data;
                if (teaUpLoad) {
                    $("#teaFileBody > tr").remove();
                    teaUpLoad.forEach(function (teacherUp, index) {
                        var fileId = teacherUp.fileId;
                        var fileName = teacherUp.fileName;
                        var fileTime = teacherUp.fileTime;
                        var fileDownNum = teacherUp.fileDownNum;
                        var downBtn = '<a href="http://172.16.137.24:8080/bsxt_war/file/down.do?file_id=' + fileId + '&file_name=' + fileName + '">' +
                            '<span class="glyphicon glyphicon-cloud-download"></span></a>';

                        var str = '<tr><th>' + (index + 1) + '</th><th style="display: none">' + fileId + '</th><th>' + fileName +
                            '</th><th>' + fileTime + '</th><th>' + fileDownNum + '</th><th>' + downBtn + '</th></tr>';

                        $("#teaFileBody").append(str);

                    });
                }
            },
            error: function (data) {
                alert("系统出错！");
            }
        });





    }



    /*评价及意见触发事件*/
    function btn_stu_pjyj_click() {
        var userId = $("#userId").text();
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "bschoice/getStuBsChoice.do?student_id="+userId,
            success: function (data) {
                /* 界面属性改变*/
                $("#stu_kxtm").attr("style", "display:none;");
                $("#stu_yxtm").attr("style", "display:none;");
                $("#stu_ckgg").attr("style", "display:none;");
                $("#stu_xzzy").attr("style", "display:none;");
                $("#stu_pjyj").attr("style", "display:block;");

                var stuBsChoice = data;
                if (stuBsChoice.bsChoiceId) {
                    $("#bsTopicNameVauleV2").text(stuBsChoice.bsTopicName);
                    /*答辩分数*/
                    if (stuBsChoice.replyPoint) {
                        if (stuBsChoice.replyPoint >= 60){
                            $("#replyPointValue").text(stuBsChoice.replyPoint);
                            $("#replyPointValue").attr("style", "color:green");
                        } else {
                            $("#replyPointValue").text(stuBsChoice.replyPoint+"(不及格)");
                            $("#replyPointValue").attr("style", "color:red");
                        }
                    } else {
                        $("#replyPointValue").text("暂无!");
                    }
                    /*评阅分数*/
                    if (stuBsChoice.reviewPoint) {
                        if (stuBsChoice.reviewPoint >= 60){
                            $("#reviewPointValue").text(stuBsChoice.reviewPoint);
                            $("#reviewPointValue").attr("style", "color:green");
                        } else {
                            $("#reviewPointValue").text(stuBsChoice.reviewPoint+"(不及格)");
                            $("#reviewPointValue").attr("style", "color:red");
                        }
                    } else {
                        $("#reviewPointValue").text("暂无!");
                    }
                    /*指导老师指导意见*/
                    if (stuBsChoice.guidance) {
                        $("#guidanceValue").text(stuBsChoice.guidance);
                    } else {
                        $("#guidanceValue").text("暂无指导意见");
                    }

                } else {
                    /*还未选题情况*/
                    $("#bsTopicNameVauleV2").text("未选题");
                    $("#replyPointValue").text("暂无");
                    $("#reviewPointValue").text("暂无");
                    $("#guidanceValue").text("暂无指导意见");
                }

            },
            error: function (data) {
                alert(data);
            }
        });

    }


    /*选择毕设题目触发事件*/
    function btn_choiceBs_click(bsTopicId) {
        var hasChoiceValue = $("#hasChoiceValue").val();
        var hasTeaChoiceValue = $("#hasTeaChoiceValue").val();
        if (hasTeaChoiceValue == "T"){
            alert("教师已经确认了你的题目,无法再选题!");
            return false;
        } else if (hasChoiceValue == "T"){
            var status = confirm("你已经选过题目，是否重新选择该题目？");//在js里面写confirm，在页面中弹出提示信息。
            if (!status){//如果点击的是取消就返回页面
                return false;
            }
        }
        //如果点击的是确认或者hasChoiceValue为F
        var student_id = $("#userId").text();
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "bstopic/saveOrUpdateStuChoice.do?bs_topic_id="+bsTopicId+"&student_id="+student_id,
            success: function (data) {
                if (data){
                    alert(data.message);
                    /*location.reload(true);//页面以GET方式刷新*/
                    /* 界面属性改变(暂不生效)*/
                    $("#user_message_page").attr("style", "display:none;");
                    $("#stu_byxt_page").attr("style", "display:none;");
                    $("#stu_kxtm").attr("style", "display:block;");
                    $("#stu_yxtm").attr("style", "display:none;");
                    $("#stu_ckgg").attr("style", "display:none;");
                    $("#stu_xzzy").attr("style", "display:none;");
                    $("#stu_pjyj").attr("style", "display:none;");
                }
            },
            error: function (data) {
                alert(data);
            }
        });



    }
</script>


<h2>毕业设计选题</h2>
<hr class="divider">


<button type="button" class="btn btn-primary btn-md" id="btn_stu_kxtm" onclick="btn_stu_kxtm_click()">
    <span class="glyphicon glyphicon-indent-right"></span> 可选题目
</button>

<button type="button" class="btn btn-primary btn-md" id="btn_stu_yxtm" onclick="btn_stu_yxtm_click()">
    <span class="glyphicon glyphicon-leaf"></span> 已选题目
</button>

<button type="button" class="btn btn-primary btn-md id="btn_stu_ckgg" onclick="btn_stu_ckgg_click()">
    <span class="glyphicon glyphicon-bullhorn"></span> 查看公告
</button>

<button type="button" class="btn btn-primary btn-md" id="btn_stu_xzzy" onclick="btn_stu_xzzy_click()">
    <span class="glyphicon glyphicon-download"></span> 查看资源
</button>

<button type="button" class="btn btn-primary btn-md" id="btn_stu_pjyj" onclick="btn_stu_pjyj_click()">
    <span class="glyphicon glyphicon-star"></span> 评级意见
</button>
