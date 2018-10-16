<%--
  Created by IntelliJ IDEA.
  User: 兵哥哥
  Date: 2018/10/3
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>

<%
    Cookie[] cookies = request.getCookies();
    String username_password = "";
    if (cookies != null) {
        for (int i=0; i<cookies.length; i++) {
            if ("studentjspusernamepassword".equals(cookies[i].getName())) {
                username_password = cookies[i].getValue();
                break;
            }
        }
    }
    if (!username_password.equals("")) {
        String[] up = username_password.split(" ");
    %>
    <jsp:forward page="../../orders/orderManager/list.jsp"></jsp:forward>
<%}%>