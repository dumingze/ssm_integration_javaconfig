<%--
  Created by IntelliJ IDEA.
  User: stone
  Date: 2020/8/20
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>通过request获得请求参数</h1>
    <form action="/request/parameter" method="get">
        用户：<input type="text" name="username"><br>
        密码：<input type="text" name="password"><br>
        <input type="submit">
    </form>
    <hr/>
    <h1>形参直接接收请求参数</h1>
    <form action="/direct/parameter" method="get">
        用户：<input type="text" name="username"><br>
        密码：<input type="text" name="password"><br>
        年龄：<input type="text" name="age"><br>
        婚配：<input type="text" name="married"><br>
        <input type="submit">
    </form><hr>
    <h1>接收日期格式数据</h1>
    <form action="/birthday" method="get">
        生日：<input type="text" name="birthday"><br>
        <input type="submit">
    </form><hr>
    <h1>接收文件格式数据 → 文件上传</h1>
    <form action="/file/upload" enctype="multipart/form-data" method="post">
        文件：<input type="file" name="myfile"><br>
        <input type="submit">
    </form>
</body>
</html>
