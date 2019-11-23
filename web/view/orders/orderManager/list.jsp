<%--
  Created by IntelliJ IDEA.
  User: 兵哥哥
  Date: 2018/10/3
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/bootstrap.min.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/view/js/jquery-1.10.2.min.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/view/js/bootstrap.min.js" ></script>
    <meta charset="UTF-8">
    <title>订单列表</title>

    <script language="JavaScript">
        function doView(orderId) {
            document.mainForm.action = "<%=request.getContextPath()%>/OrderItemServlet?method=item&orderId="+orderId;
            document.mainForm.submit();
        }
        function doDelete(orderId) {
            document.mainForm.action = "<%=request.getContextPath()%>/OrderServlet?method=delete&orderId="+orderId;
            document.mainForm.submit();
        }
        function showAll() {
            window.location.href = "<%=request.getContextPath()%>/OrderServlet?method=list";
        }
    </script>
</head>
<body>
<form name="mainForm" method="post" action="">
    <div style="text-align: center"><h2 style="text-align: center">订单列表</h2> </div>
    <table align="center" bgcolor="black" cellpadding="5" cellspacing="1" border="1" width="700px">
        <tr bgcolor="white">
            <td colspan="5" style="text-align: center">
                欢迎你&nbsp;&nbsp;[${sessionScope.user.userName}]&nbsp;|<a href="<%=request.getContextPath()%>/view/orders/userManager/set_pass.jsp">[修改密码]</a>
                &nbsp;|&nbsp;<a href="<%=request.getContextPath()%>/LogoutServlet">[ <span title="退出系统" class="glyphicon glyphicon-off"></span> ]</a>
            </td>
            <td colspan="2" style="text-align: center">
                <input type="button" value="显示全部" onclick="showAll()">
                &nbsp;&nbsp;
                <input type="button" value="查询" onclick="document.location.href='<%=request.getContextPath()%>/view/orders/orderManager/search.jsp'">
                &nbsp;&nbsp;
                <input type="button" value="新增" onclick="document.location.href='<%=request.getContextPath()%>/view/orders/orderManager/new.jsp'">
            </td>
        </tr>
        <tr bgcolor="white">
            <th>订单编号</th>
            <th>订单名称</th>
            <th>订单配送方式</th>
            <th>订单金额</th>
            <th>订单修订日期</th>
            <th colspan="2" style="text-align: center">订单操作</th>
        </tr>
        <c:forEach var="list" items="${sessionScope.orderList}">
            <tr bgcolor="white">
                <td>${list.orderId}</td>
                <td>${list.orderCode}</td>
                <td>${list.deliveryMethod}</td>
                <td>${list.sum}</td>
                <td>${list.lastmodifyDate}</td>
                <td style="text-align: center"><a href="#" onclick="doView('${list.orderId}')"><span title="订单详情" class="glyphicon glyphicon-eye-open"></span> </a> </td>
                <td style="text-align: center"><a href="#" onclick="doDelete('${list.orderId}')"><span title="删除订单" class="glyphicon glyphicon-remove"></span> </a></td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
