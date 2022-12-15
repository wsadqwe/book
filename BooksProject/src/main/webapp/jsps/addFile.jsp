<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <link
            href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css"
            rel="stylesheet">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script
            src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/layer/2.3/layer.js"></script>
</head>
<body>

<div
        style="margin-top: 50px; width: 60%; margin-left: 20%; marign-right: 20%;">
    <h3 align="center">添加文件</h3>

    <div class="message" style="margin-left:50%;color:green">


        <a href="${pageContext.request.contextPath}/UserFile/list.do">返回</a>

    </div>

    <form method="post"
          action="${pageContext.request.contextPath }/UserFile/add.do" enctype="multipart/form-data">

        请选择图书图片
        <input type="file" name="file" required><br><br>

        请选择图书txt文件
        <input type="file" name="file" required><br><br>


        <input value="添加" style="width: 100%;" type="submit"
               class="btn btn-success">
    </form>
</div>

<c:if test="${not empty message }">
    <script>
        var message=${message}
            if(message==1){
                layer.msg("没有选择图片文件");
            }
        if(message==2){
            layer.msg("添加图书成功");
        }
    </script>
</c:if>
</body>
</html>