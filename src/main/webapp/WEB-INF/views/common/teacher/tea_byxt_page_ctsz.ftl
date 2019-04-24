<!--  教师毕业选题界面子功能：出题设置 -->
<style>
    th {
        text-align: center;
    }
</style>


    <!-- 1 -->
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">出题设置<span class="glyphicon glyphicon-plus pull-right" onclick="bsTopic_add_fun()"></span></h3>
        </div>
        <div class="panel-body">
            <table class="table">
                <thead>
                <tr>
                    <th>#</th>
                    <th style="display: none">毕设id</th>
                    <th >毕设题目</th>
                    <th>该题学生</th>
                    <th style="display: none">学生学号</th>
                    <th>确认状态</th>
                    <th><span class="glyphicon glyphicon-cog" aria-hidden="true"></span></th>
                </tr>
                </thead>
                <tbody id="bsTopicsBody">

                </tbody>
            </table>
            <!-- 分页按钮 -->
            <div id="bsTopic_paging_btn" class="col-xs-8 col-md-8" >

            </div>
        </div>
    </div>






<!-- 新增毕业设计题目的显示 -->
<div class="modal fade" id="addBsTopicModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="addNoticeModalLabel">新增毕业设计题目</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="txt_BsTopicName">毕业设计题目</label>
                    <textarea class="form-control" id="txt_bsTopic_content" placeholder="content"></textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                <button type="button" id="btn_addnotice_submit" class="btn btn-primary" data-dismiss="modal" onclick="btn_addBsTopic_submit_fun()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button>
            </div>
        </div>
    </div>
</div>



<!-- 教师为毕业设计题目分配学生的显示 -->
<div class="modal fade" id="addStuBsTopicModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="addNoticeModalLabel">毕业设计题目</h4>
            </div>
            <div class="modal-body">
                <div class="form-group" style="display: none">
                    <label for="txt_BsTopicName">毕业设计题目id</label>
                    <textarea class="form-control" id="txt_bsTopicId_content"  readonly></textarea>
                </div>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="txt_BsTopicName">毕业设计题目</label>
                    <textarea class="form-control" id="txt_bsTopicName_content"  readonly></textarea>
                </div>
            </div>
            <div class="modal-body">
                <div class="input-group">
                    <input type="text" class="form-control" id="stuNameInput" readonly>
                    <input type="hidden" class="form-control" id="stuIdInput" readonly>
                    <div class="input-group-btn">
                        <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            未选题学生<span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" id="dropdown_menu_notChoiceStu">

                        </ul>
                    </div>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                <button type="button" id="btn_addnotice_submit" class="btn btn-primary" data-dismiss="modal" onclick="btn_addStuBsTopic_submit_fun()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button>
            </div>
        </div>
    </div>
</div>

<script>
    $("#dropdown_menu_notChoiceStu").on("click", "li", function(){
        $("#stuNameInput").val($(this).text());
        console.info($(this).val());
        $("#stuIdInput").val($(this).val());
    });
</script>




