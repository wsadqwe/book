<%--
  Created by IntelliJ IDEA.
  User: 嘿嘿嘿
  Date: 2022/12/11
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>添加书籍</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css" />
    <script src="/js/jquery.js"></script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">表单</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>书籍信息</span></div>

    <form action="/bookServlet" method="post" id="myForm">
        <ul class="forminfo">
            <c:if test="${empty book}">
            <input type="hidden" name="type" value="save">
            </c:if>
            <c:if test="${not empty book}">
                <input type="hidden" name="type" value="update">
                <input type="hidden" name="id" value="${book.id}">
            </c:if>

            <li>
                <label>书名</label>
                <input name="bookname"  type="text" class="dfinput" value="${requestScope.book.bookname}"}/>
                <i id="usernameI">书名不能超过30个字符</i>
            </li>
            <li>
                <label>作者</label>
                <input name="author" type="text" class="dfinput" value="${requestScope.book.author}"}/>
            </li>
            <li>
                <label>书籍介绍</label>
                <textarea name="bookintroduction" cols="" rows="" class="textinput">
                    ${requestScope.book.bookintroduction}
                </textarea>
            </li>
            <li>
                <label>书籍内容</label>
                <textarea name="bookcontent" cols="50" rows="70" class="textinput">
                    ${requestScope.book.bookcontent}
                </textarea>
            </li>
            <li>
                <label>&nbsp;</label>
                <input name="" type="button" id="btn" class="btn" value="确认保存"/>
            </li>
        </ul>
    </form>

</div>

<script type="application/javascript">
    var flag = true;
    $(function(){
        $("#username").blur(function(){


        });
        $("#btn").click(function(){
                $("#myForm").submit();
        })
    })
</script>


<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
