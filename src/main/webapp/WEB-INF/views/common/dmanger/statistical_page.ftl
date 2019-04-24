<div id="DMTJ">
    <button type="button" class="btn btn-primary btn-md" onclick="statistical_class()">
        <span class="glyphicon glyphicon-home"></span> 班级统计
    </button>
    <button type="button" class="btn btn-primary btn-md"  onclick="statistical_sex()">
        <span class="glyphicon glyphicon-user"></span> 性别统计
    </button>
    <button type="button" class="btn btn-primary btn-md"  onclick="statistical_xt()">
        <span class="glyphicon glyphicon-list-alt"></span> 选题统计
    </button>
</div>

<div id="SMTJ">
    <button type="button" class="btn btn-primary btn-md"  onclick="statistical_sex()">
        <span class="glyphicon glyphicon-user"></span> 性别统计
    </button>
    <button type="button" class="btn btn-primary btn-md"  onclick="statistical_depart()">
        <span class="glyphicon glyphicon-flag"></span> 系别统计
    </button>
    <button type="button" class="btn btn-primary btn-md"  onclick="statistical_xt()">
        <span class="glyphicon glyphicon-list-alt"></span> 选题统计
    </button>
</div>

<script>

</script>

<div class="modal fade" id="showStatisticalModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="showStatisticalModalLabel"></h4>
            </div>
            <div class="modal-body">
                <div class="form-group" id="showStatisticalBody" style="width:600px; height: 400px;">

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
            </div>
        </div>
    </div>
</div>





<#--<div id="chartmain" style="width:600px; height: 400px;"></div>-->

<script type="text/javascript">

    var identity = document.getElementById("identity").value;
    if (identity == "SM") {
        $("#DMTJ").attr("style", "display:none;");
        $("#SMTJ").attr("style", "display:block;");
    } else if (identity == "DM"){
        $("#DMTJ").attr("style", "display:block;");
        $("#SMTJ").attr("style", "display:none;");
    }


    /*
     * 统计班级(DM)
     */
    function statistical_class() {
        var department_id = $("#dm_department_Id").text();
        //指定图标的配置和数据
        var option = {
            legend: {
                orient : 'vertical',
                x : 'left',
                data:[]
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            series:[{
                type:'pie',
                radius:'60%',
                data:[]
            }]
        };
        //初始化echarts实例
        var myChart = echarts.init(document.getElementById('showStatisticalBody'));
        //使用制定的配置项和数据显示图表
        myChart.setOption(option);
        //利用数组，接受数据
        var nums=[];
        var pics=[];
        var departmentName;

        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "tj/tjclass.do?base_department_id="+department_id,

            success: function (data) {
                var tJclass = data;
                if (tJclass){
                    departmentName = tJclass.departmentName;
                    var tJclasses = tJclass.tJclasses;
                    tJclasses.forEach(function (classes) {
                        nums.push({
                            name : classes.baseClassName,
                            value : classes.studentNum
                        });
                        pics.push(classes.baseClassName);
                    });
                    console.info(nums);
                } else {
                    $("#showStatisticalBody").text("暂无该系数据！");
                    alert("暂无数据");
                }
                myChart.setOption(
                    {

                        series: [{
                            // 根据名字对应到相应的系列
                            name: departmentName,
                            data: nums,
                            type: 'pie'
                        }],
                        legend:[{
                            orient : 'vertical',
                            x : 'left',
                            data: pics
                        }]
                    }
                );
                //显示dialog
                $("#showStatisticalModalLabel").text(departmentName + "的班级数据统计");
                $("#showStatisticalModal").modal();
            },
            error: function (data) {
                alert("系统出错！");
            }
        });
    }


    /*
     * 统计性别(DM/SM)
     */
    function statistical_sex() {
        var identity = document.getElementById("identity").value;
        var department_id;
        var departmentName;
        if (identity == "SM"){
            department_id = "";
            departmentName = "全院"
        } else if (identity == "DM") {
            department_id = $("#dm_department_Id").text();
            departmentName = $("#DMdepart").text();
        }

        //指定图标的配置和数据
        var option = {
            legend: {
                orient : 'vertical',
                x : 'left',
                data:[]
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            series:[{
                type:'pie',
                radius:'60%',
                data:[]
            }]
        };
        //初始化echarts实例
        var myChart = echarts.init(document.getElementById('showStatisticalBody'));
        //使用制定的配置项和数据显示图表
        myChart.setOption(option);
        //利用数组，接受数据
        var nums=[];
        var pics=[];


        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "tj/getTJStuSex.do?department_id="+department_id,

            success: function (data) {
                var tJsexes = data;
                if (tJsexes){
                    tJsexes.forEach(function (tJsex) {
                        var studentSex;
                        if (tJsex.studentSex == 1) {
                            studentSex = "男";
                        } else if (tJsex.studentSex == 0) {
                            studentSex = "女";
                        }
                        nums.push({
                            name : studentSex,
                            value : tJsex.stuCount
                        });
                        pics.push(studentSex);
                    });
                    console.info(nums);
                } else {
                    $("#showStatisticalBody").text("暂无数据！");
                    alert("暂无数据");
                }
                myChart.setOption(
                    {

                        series: [{
                            // 根据名字对应到相应的系列
                            name: departmentName,
                            data: nums,
                            type: 'pie'
                        }],
                        legend:[{
                            orient : 'vertical',
                            x : 'left',
                            data: pics
                        }]
                    }
                );
                //显示dialog
                $("#showStatisticalModalLabel").text(departmentName + "的性别数据统计");
                $("#showStatisticalModal").modal();
            },
            error: function (data) {
                alert("系统出错！");
            }
        });
    }




    /*
     * 统计系别(SM)
     */
    function statistical_depart() {

        //指定图标的配置和数据
        var option = {
            legend: {
                orient : 'vertical',
                x : 'left',
                data:[]
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            series:[{
                type:'pie',
                radius:'60%',
                data:[]
            }]
        };
        //初始化echarts实例
        var myChart = echarts.init(document.getElementById('showStatisticalBody'));
        //使用制定的配置项和数据显示图表
        myChart.setOption(option);
        //利用数组，接受数据
        var nums=[];
        var pics=[];


        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "tj/getDepart.do",

            success: function (data) {
                var tJdeparts = data;
                if (tJdeparts){
                    tJdeparts.forEach(function (tJdepart) {
                        nums.push({
                            name : tJdepart.departmentName,
                            value : tJdepart.departCount
                        });
                        pics.push(tJdepart.departmentName);
                    });
                    console.info(nums);
                } else {
                    $("#showStatisticalBody").text("暂无数据！");
                    alert("暂无数据");
                }
                myChart.setOption(
                    {

                        series: [{
                            // 根据名字对应到相应的系列
                            name: '全院',
                            data: nums,
                            type: 'pie'
                        }],
                        legend:[{
                            orient : 'vertical',
                            x : 'left',
                            data: pics
                        }]
                    }
                );
                //显示dialog
                $("#showStatisticalModalLabel").text("全院的系别数据统计");
                $("#showStatisticalModal").modal();
            },
            error: function (data) {
                alert("系统出错！");
            }
        });
    }



    /*
     * 选题统计
     */
    function statistical_xt() {
        var identity = document.getElementById("identity").value;
        var department_id;
        var departmentName;
        if (identity == "SM"){
            department_id = "";
            departmentName = "全院"
        } else if (identity == "DM") {
            department_id = $("#dm_department_Id").text();
            departmentName = $("#DMdepart").text();
        }

        //指定图标的配置和数据
        var option = {
            legend: {
                orient : 'vertical',
                x : 'left',
                data:[]
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            series:[{
                type:'pie',
                radius:'60%',
                data:[]
            }]
        };
        //初始化echarts实例
        var myChart = echarts.init(document.getElementById('showStatisticalBody'));
        //使用制定的配置项和数据显示图表
        myChart.setOption(option);
        //利用数组，接受数据
        var nums=[];
        var pics=[];
        var name1=[];
        var value1=[];


        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "tj/getBsStatus.do?department_id="+department_id,

            success: function (data) {
                var tJbs = data;
                name1.push('无毕设老师','有毕设老师，未选题','有毕设老师，已选题');
                value1.push(tJbs.notTea,tJbs.hasTeaNoChoice,tJbs.hasTeaHasChoice);
                if (tJbs){
                    nums.push({
                        name : '无毕设老师',
                        value : tJbs.notTea
                    });
                    pics.push('无毕设老师');

                    nums.push({
                        name : '有毕设老师，未选题',
                        value : tJbs.hasTeaNoChoice
                    });
                    pics.push('有毕设老师，未选题');

                    nums.push({
                        name : '有毕设老师，已选题',
                        value : tJbs.hasTeaHasChoice
                    });
                    pics.push('有毕设老师，已选题');

                    console.info(nums);
                    console.info(pics)
                } else {
                    $("#showStatisticalBody").text("暂无数据！");
                    alert("暂无数据");
                }
                myChart.setOption(
                    {

                        series: [{
                            // 根据名字对应到相应的系列
                            name: departmentName,
                            data: nums,
                            type: 'pie'
                        }],
                        legend:[{
                            orient : 'vertical',
                            x : 'left',
                            data: pics
                        }]
                    }
                );
                //显示dialog
                $("#showStatisticalModalLabel").text(departmentName + "的选题数据统计");
                $("#showStatisticalModal").modal();
            },
            error: function (data) {
                alert("系统出错！");
            }
        });
    }
</script>







