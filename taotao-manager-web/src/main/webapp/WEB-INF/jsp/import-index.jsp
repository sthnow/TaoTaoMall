<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div>
<a class="easyui-linkbutton" onclick="importIndex()">一键导入商品数据到索引库</a>
</div>

<script type="text/javascript">
    function importIndex(){
    $.post("/indedx/import", function (data) {
        if (data.status == 200) {
            $.messager.alert('提示', '创建' + node.text + ' 索引库导入成功!');
        } else {
            $.messager.alert('提示', '创建' + node.text + ' 索引库导入失败!');

    }
    });
    }
</script>