<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->

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
<div style="width: 70%; margin-left: 15%">
    <a  style="margin-top: 10px;" class="btn btn-info" href="${pageContext.request.contextPath}/UserFile/addview.do">添加文件</a>
    <table class="table table-bordered" style="margin-top: 10px;">
        <tr class="success">
            <th>序号</th>
            <th>作者名称</th>
            <th>上传时间</th>
            <th>图书图片</th>
            <th>图书txt</th>

            <th>修改</th>
            <th>删除</th>

        </tr>
        <c:forEach varStatus="vs" var="v" items="${list}">
            <tr class="">

                <td>${vs.index+1 }</td>
                <td>${v.author }</td>
                <td>${v.times }</td>
                <td>
                    <img src="${pageContext.request.contextPath}/uploadimg/${v.imgsrc}" width="50px" height="50px"/>
                </td>

                <td><a
                        href="${pageContext.request.contextPath}/UserFile/showtxt.do?id=${v.id}">查看</a>
                </td>

                <td><a
                        href="${pageContext.request.contextPath}/UserFile/toupdate.do?id=${v.id}">修改</a>
                </td>
                <td><a
                        href="${pageContext.request.contextPath}/UserFile/del.do?id=${v.id}"
                        onclick='return confirm("确认要删除吗?")'>删除</a></td>


            </tr>

        </c:forEach>


    </table>


</div>
</body>
</html>