<!--  学生毕业选题界面子功能：已选题目 -->

    <!-- 1 -->
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">已选毕业设计题目</h3>
        </div>
        <div class="panel-body">
            <table class="table">
                <thead>
                <tr>
                    <th>学号:</th>
                    <td>${userMessage.userId}</td>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <th>姓名</th>
                        <td>${userMessage.userName}</td>
                    </tr>
                    <tr>
                        <th>毕设题目</th>
                        <td id="bsTopicNameVaule"></td>
                    </tr>
                    <tr>
                        <th>教师确认</th>
                        <td id="isSureVaule"></td>
                    </tr>
                </tbody>

            </table>
        </div>
    </div>


