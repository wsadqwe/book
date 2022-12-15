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
    <title>添加用户</title>
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

    <div class="formtitle"><span>用户信息</span></div>

    <form action="/userServlet" method="post" id="myForm">
    <ul class="forminfo">
        <input type="hidden" name="type" value="save">
        <c:if test="${not empty user}">
            <input type="hidden" id="userId" name="id" value="${user.id}">
        </c:if>
        <li>
            <label>用户名</label>
            <input name="username" id="username" type="text" class="dfinput" value="${user.username}"/>
            <i id="usernameI">用户名不能超过10个字符</i>
        </li>
        <li>
            <label>密码</label>
            <input name="password" type="text" class="dfinput" value="${user.password}"/>
        </li>
        <li>
            <label>手机号</label>
            <input name="phonenum" type="text" class="dfinput" value="${user.phonenum}"/>
        </li>
        <li>
            <label>邮箱</label>
            <input name="email" type="text" class="dfinput" value="${user.email}"/>
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
            var userId = $("#userId").val()
            if(userId != null){
                //说明是更新
                flag = true;
            }else{
                //把录入的账号通过Ajax的方式提交到后端，校验该账号是否存在
                var username = $(this).val();
                $.get("/userServlet?type=check&username="+username,function(data){
                    //alert(data);
                    if(data.trim() === 'success'){
                        flag = true;
                        //表示账号可用
                        $("#usernameI").html("<span style='color:green'>账号可以使用</span>")
                    }else{
                        flag = false;
                        //表示账号不可用
                        $("#usernameI").html("<span style='color:red'>该账号已存在,换一个吧！</span>")
                    }
                })
            }

        });
        $("#btn").click(function(){
            if(flag == false){
                alert("账号已存在,不能提交,请更换账号!!!")
            }else{
                $("#myForm").submit();
            }

        })
    })
</script>


<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
