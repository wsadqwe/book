<%--
  Created by IntelliJ IDEA.
  User: 嘿嘿嘿
  Date: 2022/12/10
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src="/js/jquery.js"></script>

    <script type="text/javascript">
        $(function(){
            //导航切换
            $(".menuson li").click(function(){
                $(".menuson li.active").removeClass("active")
                $(this).addClass("active");
            });

            $('.title').click(function(){
                var $ul = $(this).next('ul');
                $('dd').find('ul').slideUp();
                if($ul.is(':visible')){
                    $(this).next('ul').slideUp();
                }else{
                    $(this).next('ul').slideDown();
                }
            });
        })
    </script>


</head>

<body style="background:#f0f9fd;">
<div class="lefttop"><span></span>功能列表</div>

<dl class="leftmenu">

    <dd>
        <div class="title">
            <span><img src="/images/leftico01.png" /></span>管理信息
        </div>
        <ul class="menuson">
            <li class="active"><cite></cite><a href="/userServlet" target="rightFrame">用户管理</a><i></i></li>
            <li><cite></cite><a href="/bookServlet?type=query" target="rightFrame">书库管理</a><i></i></li>
            <li><cite></cite><a href="/UserFile/list.do" target="rightFrame">用户文件管理</a><i></i></li>
            <li><cite></cite><a href="form.html" target="rightFrame">待定功能</a><i></i></li>
            <li><cite></cite><a href="imglist.html" target="rightFrame">待定功能</a><i></i></li>
            <li><cite></cite><a href="imglist1.html" target="rightFrame">待定功能</a><i></i></li>
            <li><cite></cite><a href="tools.html" target="rightFrame">待定功能</a><i></i></li>
            <li><cite></cite><a href="filelist.html" target="rightFrame">待定功能</a><i></i></li>
            <li><cite></cite><a href="tab.html" target="rightFrame">待定功能</a><i></i></li>
            <li><cite></cite><a href="error.html" target="rightFrame">待定功能</a><i></i></li>
        </ul>
    </dd>


    <dd>
        <div class="title">
            <span><img src="/images/leftico02.png" /></span>待定
        </div>
        <ul class="menuson">
            <li><cite></cite><a href="#">待定</a><i></i></li>
            <li><cite></cite><a href="#">待定</a><i></i></li>
            <li><cite></cite><a href="#">待定</a><i></i></li>
        </ul>
    </dd>


    <dd><div class="title"><span><img src="/images/leftico03.png" /></span>待定</div>
        <ul class="menuson">
            <li><cite></cite><a href="#">待定</a><i></i></li>
            <li><cite></cite><a href="#">待定</a><i></i></li>
            <li><cite></cite><a href="#">待定</a><i></i></li>
            <li><cite></cite><a href="#">待定</a><i></i></li>
        </ul>
    </dd>


    <dd><div class="title"><span><img src="/images/leftico04.png" /></span>待定</div>
        <ul class="menuson">
            <li><cite></cite><a href="#">待定</a><i></i></li>
            <li><cite></cite><a href="#">待定</a><i></i></li>
            <li><cite></cite><a href="#">待定</a><i></i></li>
            <li><cite></cite><a href="#">待定</a><i></i></li>
        </ul>

    </dd>

</dl>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
