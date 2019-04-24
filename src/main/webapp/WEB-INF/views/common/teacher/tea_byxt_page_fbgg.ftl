<!--  教师毕业选题界面子功能：发布公告 -->


    <!-- 1 -->
<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">发布公告<span class="glyphicon glyphicon-plus pull-right" onclick="addBsNoticBtn()"></span> </h3>
    </div>
    <div class="panel-body">
        <table class="table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>公告内容</th>
                    <th>发布时间</th>
                    <th></th>
                    <th style="display: none" >bsNoticeId</th>
                </tr>
            </thead>
            <tbody id="noticeBody">

            </tbody>
        </table>
    </div>
</div>




<!-- 新增公告的显示 -->
<div class="modal fade" id="addNoticeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="addNoticeModalLabel">新增公告</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="txt_parentdepartment">公告内容</label>
                    <textarea class="form-control" id="txt_bsnotice_content" placeholder="content"></textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                <button type="button" id="btn_addnotice_submit" class="btn btn-primary" data-dismiss="modal" onclick="btn_addnotice_submit_fun()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button>
            </div>
        </div>
    </div>
</div>


