


<div class="input-group col-md-3">

    <div class="input-group-btn">
        <button type="button" class="btn btn-default" onclick="btn_tea_xtsz_click(1)">首页</button>
        <button type="button" class="btn btn-default" onclick="btn_tea_xtsz_click(pageNumValue-1)">上一页</button>
    </div>
    <span class="input-group-addon" id="stuBsChoicePageValue"></span>
    <div class="input-group-btn">
        <button type="button" id="xtsz_next" class="btn btn-default" onclick="btn_tea_xtsz_click(pageNumValue+1)">下一页</button>
        <button type="button" class="btn btn-default" onclick="btn_tea_xtsz_click(pageMaxValue)">尾页</button>
    </div>Max

    <span class="input-group-addon" id="stuBsChoiceCountValue"></span>
    <span class="input-group-addon" id="stuBsChoicePageNumValue"></span>
    <input type="text" class="form-control" id="pageNumInput"/>
    <span class="input-group-btn">
         <button class="btn btn-danger" onclick="btn_tea_xtsz_click(pageNumInput)">跳转</button>
    </span>

</div>

<script>var inputValue = $("pageNumInput").val();</script>



</div>