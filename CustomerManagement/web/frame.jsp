<%--
  Created by IntelliJ IDEA.
  User: codingBoy
  Date: 16/10/23
  Time: 下午12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>load_method</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <style>
        *{margin: 0;padding: 0}
        html, body{height: 100%}
        #nav{float: left;width:100%;height:20%;background-color: antiquewhite}
        #con{float: right;width: 100%;height:80%;background-color: aqua}
    </style>
    <script>
        $(document).ready(function(){
            //单击 a 链接，加载 a.html
            $("#a1").click(function(){
                $('#con').load('./add.jsp');
            });
            //单击 b 链接，加载 b.html
            $("#a2").click(function(){
                $('#con').load('./query.jsp');
            });
            $("#a3").click(function(){
                $('#con').load('./CustomerServlet?method=findAll');
            });
        })
    </script>
</head>
<body>
<ul id="nav">
    <li style="text-align: center"><a href="#" id="a1" >添加客户</a></li>
    <li style="text-align: center"><a href="#" id="a2">高级查询</a></li>
    <li style="text-align: center"><a href="#" id="a3">查询客户</a></li>
</ul>
<div id="con"></div>
</body>
</html>
