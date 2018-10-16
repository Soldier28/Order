<%--
  Created by IntelliJ IDEA.
  User: 兵哥哥
  Date: 2018/10/3
  Time: 8:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- 实现3秒后跳转页面 -->
    <meta http-equiv="refresh" content="3;url=<%=request.getContextPath()%>/view/share/login/login.jsp">
    <link rel="stylesheet" type="text/css"href="<%=request.getContextPath()%>/view/css/styles.css">
</head>
<body>
<div align="center">
    <table>
        <tr>
            <td><img src="../../images/img1.jpg"></td>
            <td>
                用户名或密码错误，3秒后自动转到登录页面！<<br>
                若没有自动跳转请单击以下连接！<<br>
                <a href="<%=request.getContextPath()%>/view/share/login/login.jsp">前往登录页面</a>
            </td>
        </tr>
    </table>
</div>
<%@include file="../cookie/button.jsp"%>
</body>
</html>
