<html>
<head>
    <title>
        测试页面
    </title>
</head>
<body>
学生信息：<br>
学号：${student.id}<br>
姓名：${student.name}<br>
年龄：${student.age}<br>
家庭住址：${student.address}<br>
学生列表：<br>
<table border="1">
    <tr>
        <td>
            序号
        </td>
        <td>
            学号
        </td>
        <td>
            姓名
        </td>
        <td>
            年龄
        </td>
        <td>
            家庭住址
        </td>
    </tr>
    <#list studentList as student>
        <#if student_index%2==0>
            <tr bgcolor="red">
            <#else>
            <tr bgcolor="blue">
        </#if>
        <td>
            ${student_index}
        </td>
        <td>
            ${student.id}
        </td>
        <td>
            ${student.name}
        </td>
        <td>
            ${student.age}
        </td>
        <td>
            ${student.address}
        </td>
        </tr>
    </#list>
</table>
<br>
日期类型的处理：${date?string("yyyy-MM-DD HH:mm:ss")}
<br>
include标签测试：
<#include "hello.ftl">
</body>
</html>