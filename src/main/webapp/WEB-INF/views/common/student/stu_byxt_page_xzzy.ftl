<!--  学生毕业选题界面子功能：下载资源 -->


    <!-- 1 -->
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">教师资源</h3>
        </div>
        <div class="panel-body">
            <table class="table">
                <thead>
                    <tr>
                        <th>#</th>
                        <th style="display: none">文件id</th>
                        <th>文件名</th>
                        <th>上传时间</th>
                        <th>下载次数</th>
                        <th><span class="glyphicon glyphicon-cloud-download" aria-hidden="true"></span></th>
                    </tr>
                </thead>
                <tbody id="teaFileBody">

                </tbody>
            </table>
        </div>
    </div>








<!--上传资源-->
<form  id ="form2" action="/file/upload.do" enctype="multipart/form-data" method="post">
    <div class="form-group col-md-12 has-feedback" id="file">
        <div class="col-md-4 input-group">
            <input id="lefile2" type="file" name="file" style="display:none">
            <span class="input-group-addon" onclick="$('input[id=lefile2]').click();" style="cursor: pointer; background-color: #e7e7e7"><i class="glyphicon glyphicon-folder-open"></i>&nbsp;Browse</span>
            <input id="photoCover2" class="form-control" type="text" maxlength="25">
            <span class="input-group-addon glyphicon glyphicon-cloud-upload " onclick="fileuploadV2()" style="cursor: pointer;pointer-events: auto;"></span>

            <input type="hidden" id="uploadUserId" name="uploadUserId" value="">
            <input type="hidden" id="uploadIdentity" name="uploadIdentity" value="">
        </div>
    </div>
</form>


<script>

    /*input显示文件名*/
    $('input[id=lefile2]').change(function() {
        $('#photoCover2').val($(this).val());
    });


    /*上传文件*/
    function fileuploadV2(){
        $("#uploadUserId").val($("#userId").text());
        var formData = new FormData($("#form2")[0]);

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
        $("#photoCover2").val('');
    }

</script>
