<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/12
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>获取头部信息</title>
</head>
<body>
<h1>Http 头部请求实例</h1>
<table width="100%" border="1" align="center">
    <tr bgcolor="aqua">
        <td>Header Name</td><td>Header Value(s)</td>
    </tr>
    <%
        Enumeration headersName = request.getHeaderNames();
        while (headersName.hasMoreElements()){
            String headerName = (String)headersName.nextElement();
            out.print("<tr><td>"+headerName+"</td>");
            String headerValue = request.getHeader(headerName);
            out.println("<td>"+headerValue+"</td></tr>");
        }
    %>
</table>

</body>
</html>
