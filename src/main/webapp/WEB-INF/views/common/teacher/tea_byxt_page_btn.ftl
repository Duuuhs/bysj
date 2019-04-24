<!--  教师毕业选题设置界面按钮框  -->

<script type="text/javascript" charset="UTF-8">

    /*选题设置触发事件*/
    function btn_tea_xtsz_click(page_num) {
        var bysj_teacher_id = $("#teacher_id").text();
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "baseTeacher/getStuListByTea.do?bysj_teacher_id="+bysj_teacher_id+"&page_num="+page_num,
            success: function (data) {
                /* 界面属性改变*/
                $("#tea_xtsz").attr("style", "display:display;");
                $("#tea_ctsz").attr("style", "display:none;");
                $("#tea_fbgg").attr("style", "display:none;");
                $("#tea_sczy").attr("style", "display:none;");
                $("#tea_pfyj").attr("style", "display:none;");

                var stuListByTea = data;
                /*$("#pageMaxValue").val(stuListByTea.pageMax);
                $("#pageNumValue").val(stuListByTea.pageNum);
                $("#recordValue").val(stuListByTea.stuCount);*/


                if (stuListByTea.stuList){
                    /*去掉原有的tr*/
                    $("#stuBsChoiceByTeaBody > tr").remove();
                    stuListByTea.stuList.forEach(function(stu, index){
                        var studentId = stu.studentId;
                        var studentName = stu.studentName;
                        var bsTopicId = stu.bsTopicId;
                        var bsTopicName = stu.bsTopicName;
                        var isSure = stu.isSure;
                        var isSureValue;
                        if (bsTopicId == null) {
                            bsTopicId = "未选题";
                        }
                        if (bsTopicName == null){
                            bsTopicName = "未选题";
                        }
                        if (isSure == "T") {
                            isSureValue = "已确认";
                        } else if (isSure == "F"){
                            isSureValue = "未确认";
                        } else if (isSure == null){
                            isSureValue = "未选题"
                        }
                        var str = '<tr><th>'+(index+1)+'</th><th>'+studentId+'</th><th>'+studentName+
                            '</th><th style="display: none">'+bsTopicId+'</th><th>'+bsTopicName+'</th><th>'+isSureValue+'</th></tr>';

                        /*循环拼接内容*/
                        $("#stuBsChoiceByTeaBody").append(str);
                    });
                    /*跳页按钮*/
                    var btn = '<div class="input-group col-md-3">\n' +
                        '    <div class="input-group-btn">\n' +
                        '        <button type="button" class="btn btn-default" onclick="btn_tea_xtsz_click(1)">首页</button>\n' +
                        '        <button type="button" id="xtsz_before" class=" btn btn-default glyphicon glyphicon-chevron-left" onclick="btn_tea_xtsz_click('+(stuListByTea.pageNum-1)+')"></button>\n' +
                        '    </div>\n' +
                        '    <span class="input-group-addon">'+stuListByTea.pageNum+'</span>\n' +
                        '    <div class="input-group-btn">\n' +
                        '        <button type="button" id="xtsz_after" class="btn btn-default glyphicon glyphicon-chevron-right" onclick="btn_tea_xtsz_click('+(stuListByTea.pageNum+1)+')"></button>\n' +
                        '        <button type="button" class="btn btn-default" onclick="btn_tea_xtsz_click('+stuListByTea.pageMax+')">尾页</button>\n' +
                        '    </div>' +
                        '    <span class="input-group-addon">共'+stuListByTea.stuCount+'条数据</span>\n' +
                        '    <span class="input-group-addon">共'+stuListByTea.pageMax+'页</span>\n' +
                        '    <input type="text" class="form-control" id="pageNumInput" style="width:45px;"/>\n' +
                        '    ' +
                        '<span class="input-group-btn">\n' +
                        '         <button class="btn btn-danger" id="stuBsChoiceSkip" onclick="btn_tea_xtsz_clickV2()">跳转</button>\n' +
                        '    </span>\n' +
                        '</div>';
                    $("#tea_paging_btn > div").remove();
                    $("#tea_paging_btn").append(btn);
                    /*按钮控制显示*/
                    if (stuListByTea.pageMax < 2){
                        $("#tea_paging_btn").attr("style","display:none;");
                    } else {
                        $("#tea_paging_btn").attr("style","display:block;");
                    }
                    if (stuListByTea.pageNum == 1){
                        $("#xtsz_before").attr("disabled",true)
                    }
                    if (stuListByTea.pageNum == stuListByTea.pageMax){
                        $("#xtsz_after").attr("disabled",true);
                    }
                } else {
                    alert("暂无毕业学生！");
                }

            },
            error: function (data) {
                alert("系统出错！");
            }
        });

    }


    /*选题设置触发的跳转事件*/
    function btn_tea_xtsz_clickV2() {
        var bysj_teacher_id = $("#teacher_id").text();
        var inputValue = $("#pageNumInput").val();
        if (inputValue == null){
            alert("参数不能为空！");
            return false;
        }
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "baseTeacher/getStuListByTea.do?bysj_teacher_id="+bysj_teacher_id+"&page_num="+inputValue,
            success: function (data) {
                /* 界面属性改变*/
                $("#tea_xtsz").attr("style", "display:display;");
                $("#tea_ctsz").attr("style", "display:none;");
                $("#tea_fbgg").attr("style", "display:none;");
                $("#tea_sczy").attr("style", "display:none;");
                $("#tea_pfyj").attr("style", "display:none;");

                var stuListByTea = data;
                /*$("#pageMaxValue").val(stuListByTea.pageMax);
                $("#pageNumValue").val(stuListByTea.pageNum);
                $("#recordValue").val(stuListByTea.stuCount);*/


                if (stuListByTea.stuList){
                    /*去掉原有的tr*/
                    $("#stuBsChoiceByTeaBody > tr").remove();
                    stuListByTea.stuList.forEach(function(stu, index){
                        var studentId = stu.studentId;
                        var studentName = stu.studentName;
                        var bsTopicId = stu.bsTopicId;
                        var bsTopicName = stu.bsTopicName;
                        var isSure = stu.isSure;
                        var isSureValue;
                        if (bsTopicId == null) {
                            bsTopicId = "未选题";
                        }
                        if (bsTopicName == null){
                            bsTopicName = "未选题";
                        }
                        if (isSure == "T") {
                            isSureValue = "已确认";
                        } else if (isSure == "F"){
                            isSureValue = "未确认";
                        } else if (isSure == null){
                            isSureValue = "未选题"
                        }
                        var str = '<tr><th>'+(index+1)+'</th><th>'+studentId+'</th><th>'+studentName+
                            '</th><th style="display: none">'+bsTopicId+'</th><th>'+bsTopicName+'</th><th>'+isSureValue+'</th></tr>';

                        /*循环拼接内容*/
                        $("#stuBsChoiceByTeaBody").append(str);
                    });
                    /*跳页按钮*/
                    var btn = '<div class="input-group col-md-3">\n' +
                        '    <div class="input-group-btn">\n' +
                        '        <button type="button" class="btn btn-default" onclick="btn_tea_xtsz_click(1)">首页</button>\n' +
                        '        <button type="button" id="xtsz_before" class=" btn btn-default glyphicon glyphicon-chevron-left" onclick="btn_tea_xtsz_click('+(stuListByTea.pageNum-1)+')"></button>\n' +
                        '    </div>\n' +
                        '    <span class="input-group-addon">'+stuListByTea.pageNum+'</span>\n' +
                        '    <div class="input-group-btn">\n' +
                        '        <button type="button" id="xtsz_after" class="btn btn-default glyphicon glyphicon-chevron-right" onclick="btn_tea_xtsz_click('+(stuListByTea.pageNum+1)+')"></button>\n' +
                        '        <button type="button" class="btn btn-default" onclick="btn_tea_xtsz_click('+stuListByTea.pageMax+')">尾页</button>\n' +
                        '    </div>' +
                        '    <span class="input-group-addon">共'+stuListByTea.stuCount+'条数据</span>\n' +
                        '    <span class="input-group-addon">共'+stuListByTea.pageMax+'页</span>\n' +
                        '    <input type="text" class="form-control" id="pageNumInput" style="width:45px;"/>\n' +
                        '    ' +
                        '<span class="input-group-btn">\n' +
                        '         <button class="btn btn-danger" id="stuBsChoiceSkip" onclick="btn_tea_xtsz_clickV2()">跳转</button>\n' +
                        '    </span>\n' +
                        '</div>';
                    $("#tea_paging_btn > div").remove();
                    $("#tea_paging_btn").append(btn);
                    /*按钮控制显示*/
                    if (stuListByTea.pageMax < 2){
                        $("#tea_paging_btn").attr("style","display:none;");
                    } else {
                        $("#tea_paging_btn").attr("style","display:block;");
                    }
                    if (stuListByTea.pageNum == 1){
                        $("#xtsz_before").attr("disabled",true)
                    }
                    if (stuListByTea.pageNum == stuListByTea.pageMax){
                        $("#xtsz_after").attr("disabled",true);
                    }
                } else {
                    alert("暂无毕业学生！");
                }

            },
            error: function (data) {
                alert("系统出错！");
            }
        });

    }



    /*出题设置触发事件*/
    function btn_tea_ctsz_click(page_num) {
        var bs_teacher_id = $("#teacher_id").text();
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "bstopic/getTopicListByTea.do?bs_teacher_id="+bs_teacher_id+"&page_num="+page_num,
            success: function (data) {
                /* 界面属性改变*/
                $("#tea_xtsz").attr("style", "display:none;");
                $("#tea_ctsz").attr("style", "display:display;");
                $("#tea_fbgg").attr("style", "display:none;");
                $("#tea_sczy").attr("style", "display:none;");
                $("#tea_pfyj").attr("style", "display:none;");

                var topicListByTea = data;


                if (topicListByTea.topicList){
                    /*去掉原有的tr*/
                    $("#bsTopicsBody > tr").remove();
                    topicListByTea.topicList.forEach(function(topic, index){
                        var bsTopicId = topic.bsTopicId;
                        var bsTopicName = topic.bsTopicName;
                        var studentId = topic.studentId;
                        var studentName = topic.studentName;
                        var isSure = topic.isSure;
                        var isSureValue;
                        if (studentId == null) {
                            /*需要添加学生列表*/
                            studentName = "未选";
                        }
                        if (bsTopicName == null){
                            bsTopicName = "未选题";
                        }
                        if (isSure == "T") {
                            isSureValue = "已确认";
                        } else if (isSure == "F"){
                            isSureValue = "未确认";
                        } else if (isSure == null){
                            isSureValue = "未选";
                        }

                        var chooseStu = '<span class="glyphicon glyphicon-inbox pull-right" onclick="btn_chooseStu('+bsTopicId+','+studentId+',\''+isSure+'\','+bs_teacher_id+')"></span>';
                        var operate = '<button type="button" class="btn btn-primary btn-sm" onclick="sureBsTopic('+bsTopicId+')">\n' +
                            '<span class="glyphicon glyphicon-ok-sign"></span>\n' +
                            '</button>\n' +
                            '<button type="button" class="btn btn-primary btn-sm" id="btn_bsTopic_qxqr" onclick="noSureBsTopic_fun('+bsTopicId+','+studentId+')">\n' +
                            '<span class="glyphicon glyphicon-remove-sign"></span>\n' +
                            '</button>\n' +
                            '<button type="button" class="btn btn-danger btn-sm" id="btn_bsTopic_delete" onclick="bsTopic_delete_fun('+bsTopicId+','+studentId+')">\n' +
                            '<span class="glyphicon glyphicon-trash"></span>\n' +
                            '</button>';
                        var str = '<tr><th>'+(index+1)+'</th><th style="display: none">'+bsTopicId+'</th><th>'+bsTopicName+
                            '</th><th>'+studentName+chooseStu+'</th><th style="display: none">'+studentId+'</th><th>'+isSureValue+'</th><th>'+operate+'</th></tr>';
                        /*循环拼接内容*/
                        $("#bsTopicsBody").append(str + operate);

                    });
                    /*跳页按钮*/
                    var btn = '<div class="input-group col-md-3">\n' +
                        '    <div class="input-group-btn">\n' +
                        '        <button type="button" class="btn btn-default" onclick="btn_tea_ctsz_click(1)">首页</button>\n' +
                        '        <button type="button" id="ctsz_before" class=" btn btn-default glyphicon glyphicon-chevron-left" onclick="btn_tea_ctsz_click('+(topicListByTea.pageNum-1)+')"></button>\n' +
                        '    </div>\n' +
                        '    <span class="input-group-addon">'+topicListByTea.pageNum+'</span>\n' +
                        '    <div class="input-group-btn">\n' +
                        '        <button type="button" id="ctsz_after" class="btn btn-default glyphicon glyphicon-chevron-right" onclick="btn_tea_ctsz_click('+(topicListByTea.pageNum+1)+')"></button>\n' +
                        '        <button type="button" class="btn btn-default" onclick="btn_tea_ctsz_click('+topicListByTea.pageMax+')">尾页</button>\n' +
                        '    </div>' +
                        '    <span class="input-group-addon">共'+topicListByTea.topicCount+'条数据</span>\n' +
                        '    <span class="input-group-addon">共'+topicListByTea.pageMax+'页</span>\n' +
                        '    <input type="text" class="form-control" id="bsTopicPageNumInput" style="width:45px;"/>\n' +
                        '    ' +
                        '<span class="input-group-btn">\n' +
                        '         <button class="btn btn-danger" id="stuBsChoiceSkip" onclick="btn_tea_ctsz_clickV2()">跳转</button>\n' +
                        '    </span>\n' +
                        '</div>';
                    $("#bsTopic_paging_btn > div").remove();
                    $("#bsTopic_paging_btn").append(btn);
                    /*按钮控制显示*/
                    if (topicListByTea.pageMax < 2){
                        $("#bsTopic_paging_btn").attr("style","display:none;");
                    } else {
                        $("#bsTopic_paging_btn").attr("style","display:block;");
                    }
                    if (topicListByTea.pageNum == 1){
                        $("#ctsz_before").attr("disabled",true)
                    }
                    if (topicListByTea.pageNum == topicListByTea.pageMax){
                        $("#ctsz_after").attr("disabled",true);
                    }
                } else {
                    alert("暂无毕业设计题目！");
                }

            },
            error: function (data) {
                alert("系统出错！");
            }
        });



    }



    /*出题设置触发的跳转事件*/
    function btn_tea_ctsz_clickV2() {
        var bs_teacher_id = $("#teacher_id").text();
        var bsTopicPageNumInput = $("#bsTopicPageNumInput").val();
        if (bsTopicPageNumInput == null){
            alert("参数不能为空！");
            return false;
        }
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "bstopic/getTopicListByTea.do?bs_teacher_id="+bs_teacher_id+"&page_num="+bsTopicPageNumInput,
            success: function (data) {
                /* 界面属性改变*/
                $("#tea_xtsz").attr("style", "display:none;");
                $("#tea_ctsz").attr("style", "display:display;");
                $("#tea_fbgg").attr("style", "display:none;");
                $("#tea_sczy").attr("style", "display:none;");
                $("#tea_pfyj").attr("style", "display:none;");

                var topicListByTea = data;


                if (topicListByTea.topicList){
                    /*去掉原有的tr*/
                    $("#bsTopicsBody > tr").remove();
                    topicListByTea.topicList.forEach(function(topic, index){
                        var bsTopicId = topic.bsTopicId;
                        var bsTopicName = topic.bsTopicName;
                        var studentId = topic.studentId;
                        var studentName = topic.studentName;
                        var isSure = topic.isSure;
                        var isSureValue;
                        if (studentId == null) {
                            /*需要添加学生列表*/
                            studentName = "未选";
                        }
                        if (bsTopicName == null){
                            bsTopicName = "未选";
                        }
                        if (isSure == "T") {
                            isSureValue = "已确认";
                        } else if (isSure == "F"){
                            isSureValue = "未确认";
                        } else if (isSure == null){
                            isSureValue = "未选";
                        }
                        var chooseStu = '<span class="glyphicon glyphicon-inbox pull-right" onclick="btn_chooseStu('+bsTopicId+','+studentId+',\''+isSure+'\','+bs_teacher_id+')"></span>';
                        var operate = '<button type="button" class="btn btn-primary btn-sm" onclick="sureBsTopic('+bsTopicId+')">\n' +
                            '<span class="glyphicon glyphicon-ok-sign"></span>\n' +
                            '</button>\n' +
                            '<button type="button" class="btn btn-primary btn-sm" id="btn_bsTopic_qxqr" onclick="noSureBsTopic_fun('+bsTopicId+','+studentId+')">\n' +
                            '<span class="glyphicon glyphicon-remove-sign"></span>\n' +
                            '</button>\n' +
                            '<button type="button" class="btn btn-danger btn-sm" id="btn_bsTopic_delete" onclick="bsTopic_delete_fun('+bsTopicId+','+studentId+')">\n' +
                            '<span class="glyphicon glyphicon-trash"></span>\n' +
                            '</button>';
                        var str = '<tr><th>'+(index+1)+'</th><th style="display: none">'+bsTopicId+'</th><th>'+bsTopicName+
                            '</th><th>'+studentName+chooseStu+'</th><th style="display: none">'+studentId+'</th><th>'+isSureValue+'</th><th>'+operate+'</th></tr>';
                        /*循环拼接内容*/
                        $("#bsTopicsBody").append(str + operate);

                    });
                    /*跳页按钮*/
                    var btn = '<div class="input-group col-md-3">\n' +
                        '    <div class="input-group-btn">\n' +
                        '        <button type="button" class="btn btn-default" onclick="btn_tea_ctsz_click(1)">首页</button>\n' +
                        '        <button type="button" id="ctsz_before" class=" btn btn-default glyphicon glyphicon-chevron-left" onclick="btn_tea_ctsz_click('+(topicListByTea.pageNum-1)+')"></button>\n' +
                        '    </div>\n' +
                        '    <span class="input-group-addon">'+topicListByTea.pageNum+'</span>\n' +
                        '    <div class="input-group-btn">\n' +
                        '        <button type="button" id="ctsz_after" class="btn btn-default glyphicon glyphicon-chevron-right" onclick="btn_tea_ctsz_click('+(topicListByTea.pageNum+1)+')"></button>\n' +
                        '        <button type="button" class="btn btn-default" onclick="btn_tea_ctsz_click('+topicListByTea.pageMax+')">尾页</button>\n' +
                        '    </div>' +
                        '    <span class="input-group-addon">共'+topicListByTea.topicCount+'条数据</span>\n' +
                        '    <span class="input-group-addon">共'+topicListByTea.pageMax+'页</span>\n' +
                        '    <input type="text" class="form-control" id="bsTopicPageNumInput" style="width:45px;"/>\n' +
                        '    ' +
                        '<span class="input-group-btn">\n' +
                        '         <button class="btn btn-danger" id="stuBsChoiceSkip" onclick="btn_tea_ctsz_clickV2()">跳转</button>\n' +
                        '    </span>\n' +
                        '</div>';
                    $("#bsTopic_paging_btn > div").remove();
                    $("#bsTopic_paging_btn").append(btn);
                    /*按钮控制显示*/
                    if (topicListByTea.pageMax < 2){
                        $("#bsTopic_paging_btn").attr("style","display:none;");
                    } else {
                        $("#bsTopic_paging_btn").attr("style","display:block;");
                    }
                    if (topicListByTea.pageNum == 1){
                        $("#ctsz_before").attr("disabled",true)
                    }
                    if (topicListByTea.pageNum == topicListByTea.pageMax){
                        $("#ctsz_after").attr("disabled",true);
                    }
                } else {
                    alert("暂无毕业设计题目！");
                }

            },
            error: function (data) {
                alert("系统出错！");
            }
        });
    }




    /*给毕业设计题目分配学生或者取消学生选择*/
    function btn_chooseStu(bsTopicId, studentId, isSure, bs_teacher_id) {
        if (isSure == "T") {
            alert("该题目已经教师确认过，请先取消该状态！");
        } else if (studentId != null){
            /*有学生信息代表有学生选择过该题，此时需要先删除学生选题记录*/
            var status = confirm("已有学生选择过该题，是否先删除该学生选题记录？");
            if (status){
                $.ajax({
                    type: "GET",
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    url: "bschoice/deleteStuBsChoice.do?student_id="+studentId+"&bs_topic_id="+bsTopicId,
                    success: function (data) {

                        var tMessage = data;
                        if (tMessage){
                            alert(tMessage.message);
                            /*location.reload(true);*/
                            $("#tea_xtsz").attr("style", "display:none;");
                            $("#tea_ctsz").attr("style", "display:display;");
                            $("#tea_fbgg").attr("style", "display:none;");
                            $("#tea_sczy").attr("style", "display:none;");
                            $("#tea_pfyj").attr("style", "display:none;");
                        } else {
                            alert("删除学生选题信息失败！");
                        }

                    },
                    error: function (data) {
                        alert("系统出错！");
                    }
                });
            }
        } else {
            /*无相关学生信息，证明是给学生选择毕业设计题目*/
            <!--获取该教师下所有未选择题目的学生和该题目的信息-->
            $.ajax({
                type: "GET",
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                url: "bschoice/getNotChoiceStuByTea.do?bsTopicId="+bsTopicId+"&bs_teacher_id="+bs_teacher_id,
                success: function (data) {

                    var queryNotChoiceStudents = data;
                    if (queryNotChoiceStudents){
                        var bs_topic_name = queryNotChoiceStudents.bs_topic_name;
                        var teaQueryNotChoiceStus = queryNotChoiceStudents.teaQueryNotChoiceStus;
                        /*给dialog先关组件赋值*/
                        $("#txt_bsTopicId_content").val(bsTopicId);
                        $("#txt_bsTopicName_content").val(bs_topic_name);
                        $("#dropdown_menu_notChoiceStu > li").remove();
                        teaQueryNotChoiceStus.forEach(function(stu){
                            var stuLi = '<li value="'+stu.studentId+'">'+stu.studentName+'</li>';
                            $("#dropdown_menu_notChoiceStu").append(stuLi);
                        });
                        $('#addStuBsTopicModal').modal();
                    } else {
                        alert("查找失败！");
                    }

                },
                error: function (data) {
                    alert("系统出错！");
                }
            });
        }
    }








    /*教师给毕业设计题目选题弹窗的保存按钮*/
    function btn_addStuBsTopic_submit_fun() {
        var bs_topic_id = $("#txt_bsTopicId_content").val();
        var student_id = $("#stuIdInput").val();
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "bstopic/saveOrUpdateStuChoice.do?bs_topic_id="+bs_topic_id+"&student_id="+student_id,
            success: function (data) {
                var tMessage = data;
                if (tMessage){
                    alert(tMessage.message)
                } else {
                    alert("保存失败！");
                }

            },
            error: function (data) {
                alert("系统出错！");
            }
        });
    }




    /* 教师确认选题触发事件*/
    function sureBsTopic(bs_topic_id) {
            var status = confirm("是否确认该学生的选题？");
            if (status){
                $.ajax({
                    type: "GET",
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    url: "bstopic/sureBsTopic.do?bs_topic_id="+bs_topic_id,
                    success: function (data) {

                        var tMessage = data;
                        if (tMessage){
                            alert(tMessage.message);
                            /*location.reload(true);*/
                            $("#tea_xtsz").attr("style", "display:none;");
                            $("#tea_ctsz").attr("style", "display:display;");
                            $("#tea_fbgg").attr("style", "display:none;");
                            $("#tea_sczy").attr("style", "display:none;");
                            $("#tea_pfyj").attr("style", "display:none;");
                        } else {
                            alert("确认选题失败！");
                        }

                    },
                    error: function (data) {
                        alert("系统出错！");
                    }
                });
            }


    }


    /* 教师取消确认选题触发事件*/
    function noSureBsTopic_fun(bsTopicId, studentId) {
        if (studentId == null){
            alert("该题目没有学生选择，取消确认无效！");
            return false;
        }
        var status = confirm("是否取消该题的确认选择状态？");
        if (status){
            $.ajax({
                type: "GET",
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                url: "bstopic/noSureBsTopic.do?bs_topic_id="+bsTopicId+"&student_id="+studentId,
                success: function (data) {
                    var tMessage = data;
                    if (tMessage){
                        alert(tMessage.message);
                        /*location.reload(true);*/
                        $("#tea_xtsz").attr("style", "display:none;");
                        $("#tea_ctsz").attr("style", "display:display;");
                        $("#tea_fbgg").attr("style", "display:none;");
                        $("#tea_sczy").attr("style", "display:none;");
                        $("#tea_pfyj").attr("style", "display:none;");
                    } else {
                        alert("取消失败！");
                    }

                },
                error: function (data) {
                    alert("系统出错！");
                }
            });
        }
    }




    /* 删除题目触发事件*/
    function bsTopic_delete_fun(bs_topic_id, studentId) {
        if (studentId != null){
            alert("该题目已被学生选择，无法删除！");
            return false;
        } else {
            var status = confirm("确认删除该题目吗?");
            if (status) {
                $.ajax({
                    type: "GET",
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    url: "bstopic/deleteBsTopic.do?bs_topic_id="+bs_topic_id,
                    success: function (data) {

                        var tMessage = data;
                        if (tMessage){
                            alert(tMessage.message);
                            /*location.reload(true);*/
                            $("#tea_xtsz").attr("style", "display:none;");
                            $("#tea_ctsz").attr("style", "display:display;");
                            $("#tea_fbgg").attr("style", "display:none;");
                            $("#tea_sczy").attr("style", "display:none;");
                            $("#tea_pfyj").attr("style", "display:none;");
                        } else {
                            alert("删除失败！");
                        }

                    },
                    error: function (data) {
                        alert("系统出错！");
                    }
                });
            }
        }

    }


    /* 添加题目触发事件*/
    function bsTopic_add_fun() {
        $('#addBsTopicModal').modal();
    }



    /* 添加题目保存按钮触发事件*/
    function btn_addBsTopic_submit_fun() {
        var bs_teacher_id = $("#teacher_id").text();
        var bs_topic_name = $("#txt_bsTopic_content").val();
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "bstopic/addBsTopic.do?bs_topic_name="+bs_topic_name+"&bs_teacher_id="+bs_teacher_id,
            success: function (data) {

                var tMessage = data;
                if (tMessage){
                    alert(tMessage.message);
                    /*location.reload(true);*/
                    $("#tea_xtsz").attr("style", "display:none;");
                    $("#tea_ctsz").attr("style", "display:display;");
                    $("#tea_fbgg").attr("style", "display:none;");
                    $("#tea_sczy").attr("style", "display:none;");
                    $("#tea_pfyj").attr("style", "display:none;");
                }else {
                alert("保存失败！");
                }

            },
            error: function (data) {
                alert("系统出错！");
                }
            });
    }




    /*发布公告触发事件*/
    function btn_tea_fbgg_click() {
        var bs_teacher_id = $("#teacher_id").text();
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "bsNotice/getBsNotices.do?bs_teacher_id="+bs_teacher_id,
            success: function (data) {
                /* 界面属性改变*/
                $("#tea_xtsz").attr("style", "display:none;");
                $("#tea_ctsz").attr("style", "display:none;");
                $("#tea_fbgg").attr("style", "display:display;");
                $("#tea_sczy").attr("style", "display:none;");
                $("#tea_pfyj").attr("style", "display:none;");

                var bsNotices = data;
                if (bsNotices){
                    /*去掉原有的tr*/
                    $("#noticeBody > tr").remove();
                    bsNotices.forEach(function(bsNotice, index){
                        var bsNoticeContent = bsNotice.bsNoticeContent;
                        var bsNoticeDate = bsNotice.bsNoticeDate;
                        var bsNoticeId = bsNotice.bsNoticeId;
                        var deleteBtn = '<button type="button" class="btn btn-primary btn-sm" onclick="deleteBsNoticeBtn('+bsNoticeId+')">' +
                            '<span class="glyphicon glyphicon-leaf"></span> 删除</button>';
                        var str = '<tr><th>'+(index+1)+'</th><th>'+bsNoticeContent+'</th><th>'+bsNoticeDate+
                            '</th><th>'+deleteBtn+'</th><th style="display: none">'+bsNoticeId+'</th></tr>';

                        /*循环拼接内容*/
                        $("#noticeBody").append(str);
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



    /*上传资料触发事件*/
    function btn_tea_sczy_click(page_num) {
        var teacher_id = $("#teacher_id").text();
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "file/getStuUpLoads.do?teacher_id="+teacher_id+"&page_num="+page_num,
            success: function (data) {
                /* 界面属性改变*/
                $("#tea_xtsz").attr("style", "display:none;");
                $("#tea_ctsz").attr("style", "display:none;");
                $("#tea_fbgg").attr("style", "display:none;");
                $("#tea_sczy").attr("style", "display:display;");
                $("#tea_pfyj").attr("style", "display:none;");

                var stuUpLoads = data;
                if (stuUpLoads){
                    /*去掉原有的tr*/
                    $("#stuUpBody > tr").remove();
                    stuUpLoads.fileContent.forEach(function(file, index){
                        var studentId = file.studentId;
                        var studentName = file.studentName;
                        var fileId = file.fileId;
                        var fileName = file.fileName;
                        var fileTime = file.fileTime;


                        var downBtn = '<a href="http://172.16.137.24:8080/bsxt_war/file/down.do?file_id='+fileId+'&file_name='+fileName+'">' +
                            '<span class="glyphicon glyphicon-cloud-download"></span></a>';


                        var str = '<tr><th>'+(index+1)+'</th><th>'+studentId+'</th><th>'+studentName+
                            '</th><th style="display: none">'+fileId+'</th><th>'+fileName+'</th><th>'+fileTime+'</th><th>'+downBtn+'</th></tr>';

                        /*循环拼接内容*/
                        $("#stuUpBody").append(str);
                    });

                    /*跳页按钮*/
                    var btn = '<div class="input-group col-md-3">\n' +
                        '    <div class="input-group-btn">\n' +
                        '        <button type="button" class="btn btn-default" onclick="btn_tea_sczy_click(1)">首页</button>\n' +
                        '        <button type="button" id="stuUp_before" class=" btn btn-default glyphicon glyphicon-chevron-left" onclick="btn_tea_sczy_click('+(stuUpLoads.pageNum-1)+')"></button>\n' +
                        '    </div>\n' +
                        '    <span class="input-group-addon">'+stuUpLoads.pageNum+'</span>\n' +
                        '    <div class="input-group-btn">\n' +
                        '        <button type="button" id="stuUp_after" class="btn btn-default glyphicon glyphicon-chevron-right" onclick="btn_tea_sczy_click('+(stuUpLoads.pageNum+1)+')"></button>\n' +
                        '        <button type="button" class="btn btn-default" onclick="btn_tea_sczy_click('+stuUpLoads.pageMax+')">尾页</button>\n' +
                        '    </div>' +
                        '    <span class="input-group-addon">共'+stuUpLoads.fileCount+'条数据</span>\n' +
                        '    <span class="input-group-addon">共'+stuUpLoads.pageMax+'页</span>\n' +
                        '    <input type="text" class="form-control" id="stuUpPageNumInput" style="width:45px;"/>\n' +
                        '    ' +
                        '<span class="input-group-btn">\n' +
                        '         <button class="btn btn-danger" id="stuBsChoiceSkip" onclick="btn_tea_sczy_clickV2()">跳转</button>\n' +
                        '    </span>\n' +
                        '</div>';
                    $("#stuUps_paging_btn > div").remove();
                    $("#stuUps_paging_btn").append(btn);
                    /*按钮控制显示*/
                    if (stuUpLoads.pageMax < 2){
                        $("#stuUps_paging_btn").attr("style","display:none;");
                    } else {
                        $("#stuUps_paging_btn").attr("style","display:block;");
                    }
                    if (stuUpLoads.pageNum == 1){
                        $("#stuUp_before").attr("disabled",true)
                    }
                    if (stuUpLoads.pageNum == stuUpLoads.pageMax){
                        $("#stuUp_after").attr("disabled",true);
                    }

                } else {
                    alert("暂无学生上传信息");
                }

            },
            error: function (data) {
                alert(data);
            }
        });


    }




    /*上传资料触发跳页事件*/
    function btn_tea_sczy_clickV2() {
        var teacher_id = $("#teacher_id").text();
        var page_num = $("#stuUpPageNumInput").val();
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "file/getStuUpLoads.do?page_num="+page_num+"&teacher_id="+teacher_id,
            success: function (data) {
                /* 界面属性改变*/
                $("#tea_xtsz").attr("style", "display:none;");
                $("#tea_ctsz").attr("style", "display:none;");
                $("#tea_fbgg").attr("style", "display:none;");
                $("#tea_sczy").attr("style", "display:display;");
                $("#tea_pfyj").attr("style", "display:none;");

                var stuUpLoads = data;
                if (stuUpLoads){
                    /*去掉原有的tr*/
                    $("#stuUpBody > tr").remove();
                    stuUpLoads.fileContent.forEach(function(file, index){
                        var studentId = file.studentId;
                        var studentName = file.studentName;
                        var fileId = file.fileId;
                        var fileName = file.fileName;
                        var fileTime = file.fileTime;
                        var downBtn = '<a href="http://172.16.137.24:8080/bsxt_war/file/down.do?file_id='+fileId+'&file_name='+fileName+'">' +
                            '<span class="glyphicon glyphicon-cloud-download"></span></a>';


                        var str = '<tr><th>'+(index+1)+'</th><th>'+studentId+'</th><th>'+studentName+
                            '</th><th style="display: none">'+fileId+'</th><th>'+fileName+'</th><th>'+fileTime+'</th><th>'+downBtn+'</th></tr>';

                        /*循环拼接内容*/
                        $("#stuUpBody").append(str);
                    });

                    /*跳页按钮*/
                    var btn = '<div class="input-group col-md-3">\n' +
                        '    <div class="input-group-btn">\n' +
                        '        <button type="button" class="btn btn-default" onclick="btn_tea_sczy_click(1)">首页</button>\n' +
                        '        <button type="button" id="stuUp_before" class=" btn btn-default glyphicon glyphicon-chevron-left" onclick="btn_tea_sczy_click('+(stuUpLoads.pageNum-1)+')"></button>\n' +
                        '    </div>\n' +
                        '    <span class="input-group-addon">'+stuUpLoads.pageNum+'</span>\n' +
                        '    <div class="input-group-btn">\n' +
                        '        <button type="button" id="stuUp_after" class="btn btn-default glyphicon glyphicon-chevron-right" onclick="btn_tea_sczy_click('+(stuUpLoads.pageNum+1)+')"></button>\n' +
                        '        <button type="button" class="btn btn-default" onclick="btn_tea_sczy_click('+stuUpLoads.pageMax+')">尾页</button>\n' +
                        '    </div>' +
                        '    <span class="input-group-addon">共'+stuUpLoads.fileCount+'条数据</span>\n' +
                        '    <span class="input-group-addon">共'+stuUpLoads.pageMax+'页</span>\n' +
                        '    <input type="text" class="form-control" id="stuUpPageNumInput" style="width:45px;"/>\n' +
                        '    ' +
                        '<span class="input-group-btn">\n' +
                        '         <button class="btn btn-danger" id="stuBsChoiceSkip" onclick="btn_tea_sczy_click()">跳转</button>\n' +
                        '    </span>\n' +
                        '</div>';
                    $("#stuUps_paging_btn > div").remove();
                    $("#stuUps_paging_btn").append(btn);
                    /*按钮控制显示*/
                    if (stuUpLoads.pageMax < 2){
                        $("#stuUps_paging_btn").attr("style","display:none;");
                    } else {
                        $("#stuUps_paging_btn").attr("style","display:block;");
                    }
                    if (stuUpLoads.pageNum == 1){
                        $("#stuUp_before").attr("disabled",true)
                    }
                    if (stuUpLoads.pageNum == stuUpLoads.pageMax){
                        $("#stuUp_after").attr("disabled",true);
                    }

                } else {
                    alert("暂无公告信息");
                }

            },
            error: function (data) {
                alert(data);
            }
        });
    }




   /* 下载按钮*/
    function downStuUp(fileId, fileName) {
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "file/down.do?file_id="+fileId+"&file_name="+fileName,
            success: function (data) {
                alert(data.message);
            },
            /*error: function (data) {
                alert("系统出错！");
            }*/
        });
    }



    /*评分意见触发事件*/
    function btn_tea_pfyj_click(page_num) {
        var bysj_teacher_id = $("#teacher_id").text();
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "bschoice/getStuChoiceStatus.do?page_num="+page_num+"&bysj_teacher_id="+bysj_teacher_id,
            success: function (data) {
                /* 界面属性改变*/
                $("#tea_xtsz").attr("style", "display:none;");
                $("#tea_ctsz").attr("style", "display:none;");
                $("#tea_fbgg").attr("style", "display:none;");
                $("#tea_sczy").attr("style", "display:none;");
                $("#tea_pfyj").attr("style", "display:display;");

                var stuBsChoiceStatusPage = data;
                if (stuBsChoiceStatusPage){
                    /*去掉原有的tr*/
                    $("#tea_pfyj_body > tr").remove();
                    stuBsChoiceStatusPage.stuList.forEach(function(student, index){
                        var studentId = student.studentId;
                        var studentName = student.studentName;
                        var bsChoiceId = student.bsChoiceId;
                        var isSure = student.isSure;
                        var statusValue ;
                        var editBtn = "";
                        if (isSure == "T"){
                            statusValue = "已确认可打分";
                            editBtn = '<button type="button" class="btn btn-primary btn-sm" onclick="editPfyjBtn('+bsChoiceId+',\''+studentName+'\')">' +
                                '<span class="glyphicon glyphicon-edit"></span></button>';
                        } else {
                            statusValue = "未确认不可打分";
                        }

                        var str = '<tr><th>'+(index+1)+'</th><th>'+studentId+'</th><th>'+studentName+
                            '</th><th>'+statusValue+'</th><th>'+editBtn+'</th></tr>';

                        /*循环拼接内容*/
                        $("#tea_pfyj_body").append(str);
                    });

                    /*跳页按钮*/
                    var btn = '<div class="input-group col-md-3">\n' +
                        '    <div class="input-group-btn">\n' +
                        '        <button type="button" class="btn btn-default" onclick="btn_tea_pfyj_click(1)">首页</button>\n' +
                        '        <button type="button" id="pfyj_before" class=" btn btn-default glyphicon glyphicon-chevron-left" onclick="btn_tea_pfyj_click('+(stuBsChoiceStatusPage.pageNum-1)+')"></button>\n' +
                        '    </div>\n' +
                        '    <span class="input-group-addon">'+stuBsChoiceStatusPage.pageNum+'</span>\n' +
                        '    <div class="input-group-btn">\n' +
                        '        <button type="button" id="pfyj_after" class="btn btn-default glyphicon glyphicon-chevron-right" onclick="btn_tea_pfyj_click('+(stuBsChoiceStatusPage.pageNum+1)+')"></button>\n' +
                        '        <button type="button" class="btn btn-default" onclick="btn_tea_pfyj_click('+stuBsChoiceStatusPage.pageMax+')">尾页</button>\n' +
                        '    </div>' +
                        '    <span class="input-group-addon">共'+stuBsChoiceStatusPage.stuCount+'条数据</span>\n' +
                        '    <span class="input-group-addon">共'+stuBsChoiceStatusPage.pageMax+'页</span>\n' +
                        '    <input type="text" class="form-control" id="stuBsTopicPageNumInput" style="width:45px;"/>\n' +
                        '    ' +
                        '<span class="input-group-btn">\n' +
                        '         <button class="btn btn-danger" id="stuBsChoiceSkip" onclick="btn_tea_pfyj_clickV2()">跳转</button>\n' +
                        '    </span>\n' +
                        '</div>';
                    $("#pfyj_paging_btn > div").remove();
                    $("#pfyj_paging_btn").append(btn);
                    /*按钮控制显示*/
                    if (stuBsChoiceStatusPage.pageMax < 2){
                        $("#pfyj_paging_btn").attr("style","display:none;");
                    } else {
                        $("#pfyj_paging_btn").attr("style","display:block;");
                    }
                    if (stuBsChoiceStatusPage.pageNum == 1){
                        $("#pfyj_before").attr("disabled",true)
                    }
                    if (stuBsChoiceStatusPage.pageNum == stuBsChoiceStatusPage.pageMax){
                        $("#pfyj_after").attr("disabled",true);
                    }

                } else {
                    alert("暂无公告信息");
                }

            },
            error: function (data) {
                alert(data);
            }
        });
    }




    /*评分意见触发的跳页事件*/
    function btn_tea_pfyj_clickV2() {
        var stuBsTopicPageNumInput = $("#stuBsTopicPageNumInput").val();
        var bysj_teacher_id = $("#teacher_id").text();
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "bschoice/getStuChoiceStatus.do?page_num="+stuBsTopicPageNumInput+"&bysj_teacher_id="+bysj_teacher_id,
            success: function (data) {
                /* 界面属性改变*/
                $("#tea_xtsz").attr("style", "display:none;");
                $("#tea_ctsz").attr("style", "display:none;");
                $("#tea_fbgg").attr("style", "display:none;");
                $("#tea_sczy").attr("style", "display:none;");
                $("#tea_pfyj").attr("style", "display:display;");

                var stuBsChoiceStatusPage = data;
                if (stuBsChoiceStatusPage){
                    /*去掉原有的tr*/
                    $("#tea_pfyj_body > tr").remove();
                    stuBsChoiceStatusPage.stuList.forEach(function(student, index){
                        var studentId = student.studentId;
                        var studentName = student.studentName;
                        var bsChoiceId = student.bsChoiceId;
                        var isSure = student.isSure;
                        var statusValue ;
                        var editBtn = "";
                        if (isSure == "T"){
                            statusValue = "已确认可打分";
                            editBtn = '<button type="button" class="btn btn-primary btn-sm" onclick="editPfyjBtn('+bsChoiceId+',\''+studentName+'\')">' +
                                '<span class="glyphicon glyphicon-edit"></span></button>';
                        } else {
                            statusValue = "未确认不可打分";
                        }

                        var str = '<tr><th>'+(index+1)+'</th><th>'+studentId+'</th><th>'+studentName+
                            '</th><th>'+statusValue+'</th><th>'+editBtn+'</th></tr>';

                        /*循环拼接内容*/
                        $("#tea_pfyj_body").append(str);
                    });

                    /*跳页按钮*/
                    var btn = '<div class="input-group col-md-3">\n' +
                        '    <div class="input-group-btn">\n' +
                        '        <button type="button" class="btn btn-default" onclick="btn_tea_pfyj_click(1)">首页</button>\n' +
                        '        <button type="button" id="pfyj_before" class=" btn btn-default glyphicon glyphicon-chevron-left" onclick="btn_tea_pfyj_click('+(stuBsChoiceStatusPage.pageNum-1)+')"></button>\n' +
                        '    </div>\n' +
                        '    <span class="input-group-addon">'+stuBsChoiceStatusPage.pageNum+'</span>\n' +
                        '    <div class="input-group-btn">\n' +
                        '        <button type="button" id="pfyj_after" class="btn btn-default glyphicon glyphicon-chevron-right" onclick="btn_tea_pfyj_click('+(stuBsChoiceStatusPage.pageNum+1)+')"></button>\n' +
                        '        <button type="button" class="btn btn-default" onclick="btn_tea_pfyj_click('+stuBsChoiceStatusPage.pageMax+')">尾页</button>\n' +
                        '    </div>' +
                        '    <span class="input-group-addon">共'+stuBsChoiceStatusPage.stuCount+'条数据</span>\n' +
                        '    <span class="input-group-addon">共'+stuBsChoiceStatusPage.pageMax+'页</span>\n' +
                        '    <input type="text" class="form-control" id="stuBsTopicPageNumInput" style="width:45px;"/>\n' +
                        '    ' +
                        '<span class="input-group-btn">\n' +
                        '         <button class="btn btn-danger" id="stuBsChoiceSkip" onclick="btn_tea_pfyj_clickV2()">跳转</button>\n' +
                        '    </span>\n' +
                        '</div>';
                    $("#pfyj_paging_btn > div").remove();
                    $("#pfyj_paging_btn").append(btn);
                    /*按钮控制显示*/
                    if (stuBsChoiceStatusPage.pageMax < 2){
                        $("#pfyj_paging_btn").attr("style","display:none;");
                    } else {
                        $("#pfyj_paging_btn").attr("style","display:block;");
                    }
                    if (stuBsChoiceStatusPage.pageNum == 1){
                        $("#pfyj_before").attr("disabled",true)
                    }
                    if (stuBsChoiceStatusPage.pageNum == stuBsChoiceStatusPage.pageMax){
                        $("#pfyj_after").attr("disabled",true);
                    }

                } else {
                    alert("暂无公告信息");
                }

            },
            error: function (data) {
                alert(data);
            }
        });
    }





    /*给学生编辑评分意见触发按钮*/
    function editPfyjBtn(bsChoiceId,studentName) {
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "bschoice/getStuBsChoiceV2.do?bs_choice_id="+bsChoiceId,
            success: function (data) {
                var bsChoice = data;
                if (bsChoice != null){
                    var guidance = bsChoice.guidance;
                    var reviewPoint = bsChoice.reviewPoint;
                    var replyPoint = bsChoice.replyPoint;
                    $("#editPfyjModalLable").text(studentName+"的毕业设计情况");
                    $("#bsChoiceIdInput").val(bsChoiceId);
                    $("#review_point_input").val(reviewPoint);
                    $("#reply_point_input").val(replyPoint);
                    $("#txt_guidance_content").val(guidance);
                    $("#editPfyjModal").modal();
                }
            },
            error: function (data) {
                alert("系统出错！");
            }
        });

    }


    /* 保存评分意见按钮*/
    function btn_saveStuBsChoice_submit_fun() {
        var bs_choice_id = $("#bsChoiceIdInput").val();
        var review_point = $("#review_point_input").val();
        var reply_point = $("#reply_point_input").val();
        var guidance = $("#txt_guidance_content").val();
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "bschoice/saveStuBsChoice.do?bs_choice_id="+bs_choice_id+"&review_point="+review_point+"&reply_point="+reply_point+"&guidance="+guidance,
            success: function (data) {

                alert(data.message);

            },
            error: function (data) {
                alert("系统出错！");
            }
        });
    }




    /* 删除公告按钮*/
    function deleteBsNoticeBtn(bsNoticeId) {
        var status = confirm("是否删除此条公告？");
        if (!status){
            return false;
        } else {
            $.ajax({
                type: "GET",
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                url: "bsNotice/deleteBsNotice.do?bs_notice_id="+bsNoticeId,
                success: function (data) {
                    alert(data.message);
                    /*location.reload(true);*/
                    $("#tea_xtsz").attr("style", "display:none;");
                    $("#tea_ctsz").attr("style", "display:none;");
                    $("#tea_fbgg").attr("style", "display:display;");
                    $("#tea_sczy").attr("style", "display:none;");
                    $("#tea_pfyj").attr("style", "display:none;");
                    },
                error: function (data) {
                    alert(data);
                }
            });
        }
    }



    /* 添加公告按钮*/
    function addBsNoticBtn() {
        $('#addNoticeModal').modal();
    }
    
    
    /*添加公告的保存按钮*/
    function btn_addnotice_submit_fun() {
        var txt_bsnotice_content = $("#txt_bsnotice_content").val();
        var teacher_id = $("#teacher_id").text();
        if (txt_bsnotice_content == null){
            alert("公告内容不能为空！");
        } else {
            $.ajax({
                type: "GET",
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                url: "bsNotice/saveBsNotice.do?bs_teacher_id="+teacher_id+"&bs_notice_content="+txt_bsnotice_content,
                success: function (data) {
                    alert(data.message);
                    /*location.reload(true);*/
                    $("#tea_xtsz").attr("style", "display:none;");
                    $("#tea_ctsz").attr("style", "display:none;");
                    $("#tea_fbgg").attr("style", "display:display;");
                    $("#tea_sczy").attr("style", "display:none;");
                    $("#tea_pfyj").attr("style", "display:none;");
                },
                error: function (data) {
                    alert(data);
                }
            });
        }
    }


</script>


<h2>毕业选题设置</h2>
<hr class="divider">

<button type="button" class="btn btn-primary btn-md" id="btn_tea_xtsz" onclick="btn_tea_xtsz_click(1)">
    <span class="glyphicon glyphicon-flag"></span> 选题设置
</button>

<button type="button" class="btn btn-primary btn-md" id="btn_tea_ctsz" onclick="btn_tea_ctsz_click(1)">
    <span class="glyphicon glyphicon-inbox"></span> 出题设置
</button>

<button type="button" class="btn btn-primary btn-md" id="btn_tea_fbgg" onclick="btn_tea_fbgg_click()">
    <span class="glyphicon glyphicon-bullhorn"></span> 发布公告
</button>

<button type="button" class="btn btn-primary btn-md" id="btn_tea_sczy" onclick="btn_tea_sczy_click(1)">
    <span class="glyphicon glyphicon-upload"></span> 资源管理
</button>

<button type="button" class="btn btn-primary btn-md" id="btn_tea_pfyj" onclick="btn_tea_pfyj_click()">
    <span class="glyphicon glyphicon-star"></span> 评分意见
</button>


<br>














