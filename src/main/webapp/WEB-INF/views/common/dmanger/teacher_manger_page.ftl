<!--  学生毕业选题界面子功能：下载资源 -->


<!-- 1 -->
<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">教职工管理</h3>
    </div>
    <div class="panel-body">
        <table class="table" id="teaMessageByManger">
            <tbody id="teaMessageByMangerBody">

            </tbody>
        </table>
        <!-- 分页按钮 -->
        <div id="teaMessageByManger_paging_btn" class="col-xs-8 col-md-8" ></div>
    </div>
</div>



<!-- 查看教师所带毕业设计学生的显示 -->
<div class="modal fade" id="getTeaBsStuModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="getTeaBsStuModalLable"></h4>
            </div>
            <div class="modal-body">
                <div class="panel-body">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>学号</th>
                                <th>姓名</th>
                            </tr>
                        </thead>
                        <tbody id="getTeaBsStuModalBody">


                        </tbody>

                    </table>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal"">确定</button>
            </div>
        </div>
    </div>
</div>



<!-- 查看教师出题情况的显示 -->
<div class="modal fade" id="getTeaBsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="getTeaBsModalLable"></h4>
            </div>
            <div class="modal-body">
                <div class="panel-body">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>题目</th>
                            </tr>
                        </thead>
                        <tbody id="getTeaBsModalBody">

                        </tbody>

                    </table>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal"">确定</button>
            </div>
        </div>
    </div>
</div>


