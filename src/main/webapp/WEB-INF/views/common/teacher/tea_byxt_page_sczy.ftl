<!--  教师毕业选题界面子功能：上传资料 -->



    <!-- 1 -->
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">资源管理</h3>
        </div>
        <div class="panel-body">
            <table class="table">
                <thead>
                <tr>
                    <th>#</th>
                    <th <#--style="display: none"-->>学生学号</th>
                    <th>上传学生</th>
                    <th style="display: none">文件id</th>
                    <th>文件名</th>
                    <th>上传日期</th>
                    <th><span class="glyphicon glyphicon-cloud-download" aria-hidden="true"></span></th>
                </tr>
                </thead>
                <tbody id="stuUpBody">

                </tbody>
            </table>
            <!-- 分页按钮 -->
            <div id="stuUps_paging_btn" class="col-xs-8 col-md-8" >

            </div>
            




<br><br>




    <!--上传资源-->
    <form  id ="form1" action="/file/upload.do" enctype="multipart/form-data" method="post">
        <div class="form-group col-md-12 has-feedback" id="file">
            <div class="col-md-4 input-group">
                    <input id="lefile" type="file" name="file" style="display:none">
                    <span class="input-group-addon" onclick="$('input[id=lefile]').click();" style="cursor: pointer; background-color: #e7e7e7"><i class="glyphicon glyphicon-folder-open"></i>&nbsp;Browse</span>
                    <input id="photoCover" class="form-control" type="text" maxlength="25">
                    <span class="input-group-addon glyphicon glyphicon-cloud-upload " onclick="fileupload2()" style="cursor: pointer;pointer-events: auto;"></span>

                    <input type="hidden" id="uploadUserId" name="uploadUserId" value="">
                    <input type="hidden" id="uploadIdentity" name="uploadIdentity" value="">
            </div>
        </div>
    </form>





        </div>
    </div>


<script>

    /*input显示文件名*/
    $('input[id=lefile]').change(function() {
        $('#photoCover').val($(this).val());
    });


    /*上传文件*/
    function fileupload2(){
        $("#uploadUserId").val($("#teacher_id").text());
        $("#uploadIdentity").val($("#identity").val());
        var formData = new FormData($("#form1")[0]);

        $.ajax({
            url:'file/upload.do',
            type:'post',
            data:formData,
            //必须false才会自动加上正确的Content-Type
            contentType: false,
            //必须false才会避开jQuery对 formdata 的默认处理
            //XMLHttpRequest会对 formdata 进行正确的处理
            processData: false,
            success:function(data){
                alert(data);
            },
            error:function(data){
                alert(data);
                alert("后台发生异常");
            },
            cache:false,
            async:true
        });
        $("#photoCover").val('');
    }





</script>









<#--上传成功 原始样式-->
<#--<form  id ="form2" action="/file/upload.do" enctype="multipart/form-data" method="post">
    <input type = "file" name= 'file' />
    <input type="text" name="name" value="dzf"/>
    <input type="button" id = "button2" value="ajax上传" onclick="fileupload2()">
    <input type="hidden" id="uploadUserId" name="uploadUserId" value="">
    <input type="hidden" id="uploadIdentity" name="uploadIdentity" value="">
</form>-->