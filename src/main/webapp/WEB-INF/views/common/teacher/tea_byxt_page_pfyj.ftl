<!--  教师毕业选题界面子功能：评分意见 -->


    <!-- 1 -->
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">评分意见</h3>
        </div>
        <div class="panel-body">
            <table class="table">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>状态</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody id="tea_pfyj_body">

                </tbody>
            </table>
            <!-- 分页按钮 -->
            <div id="pfyj_paging_btn" class="col-xs-8 col-md-8" >
        </div>
    </div>




<!-- 编辑评分意见的显示 -->
<div class="modal fade" id="editPfyjModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="editPfyjModalLable"></h4>
            </div>
            <input type="hidden" id="bsChoiceIdInput">
            <div class="modal-body">
                <div class="form-group" >
                    <label>评阅分数: </label>
                    <input type="text" id="review_point_input" >
                </div>
            </div>
            <div class="modal-body">
                <div class="form-group" >
                    <label>答辩分数: </label>
                    <input type="text" id="reply_point_input" >
                </div>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="txt_parentdepartment">指导意见</label>
                    <textarea class="form-control" id="txt_guidance_content" placeholder="Content"></textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                <button type="button" id="btn_addnotice_submit" class="btn btn-primary" data-dismiss="modal" onclick="btn_saveStuBsChoice_submit_fun()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button>
            </div>
        </div>
    </div>
</div>