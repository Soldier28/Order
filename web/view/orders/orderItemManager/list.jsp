<%--
  Created by IntelliJ IDEA.
  User: 兵哥哥
  Date: 2018/10/8
  Time: 9:26
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
    <title>订单详情</title>
    <script language="JavaScript">
        function doView(itemId) {
            document.mainForm.action = "<%=request.getContextPath()%>/OrderItemServlet?method=view&itemId="+itemId;
            document.mainForm.submit();
        }
        function doDelete(itemId , orderId) {
            document.mainForm.action = "<%=request.getContextPath()%>/OrderItemServlet?method=delete&itemId="+itemId+"&orderId="+orderId;
            document.mainForm.submit();
        }
        function checkForm() {
            if (checkNull(document.mainForm.ordercode, "订单名称")) {
                return true;
            }
            return false;
        }
    </script>
</head>
<body>
<form action="" name="mainForm" method="post">
    <div style="text-align: center"><h2>订单详情</h2></div>
    <table align="center" bgcolor="black" cellpadding="5" cellspacing="1" border="1" width="700px">
        <tr bgcolor="white">
            <th colspan="4" style="text-align: center">订单详情</th>
        </tr>
        <tr bgcolor="white">
            <td>订单编号</td>
            <td>${requestScope.order.orderId}</td>
            <td>订单名称</td>
            <td>${requestScope.order.orderCode}</td>
        </tr>
        <tr bgcolor="white">
            <td>订单配送方式</td>
            <td>${requestScope.order.deliveryMethod}</td>
            <td>订单金额</td>
            <td>${requestScope.order.sum}</td>
        </tr>
        <tr bgcolor="white">
            <td>订单创建日期</td>
            <td>${requestScope.order.createdDate}</td>
            <td>订单最后修改日期</td>
            <td>${requestScope.order.lastmodifyDate}</td>
        </tr>
        <tr bgcolor="white">
            <th colspan="4" style="text-align: center">订单明细</th>
        </tr>
        <tr bgcolor="white">
            <td colspan="4">
                <table align="center" bgcolor="black"  cellpadding="5" cellspacing="1" border="1" width="700px">
                    <tr bgcolor="white">
                        <th>货物名称</th>
                        <th>货物数量</th>
                        <th>明细金额</th>
                        <th colspan="2" style="text-align: center">操作</th>
                    </tr>
                    <c:forEach var="list" items="${requestScope.order.orderItems}">
                        <tr bgcolor="white">
                            <td>${list.name}</td>
                            <td>${list.unitNum}</td>
                            <td>${list.sum}</td>
                            <td align="center"><a href="#" onclick="doView('${list.itemId}')">
                                <span title="查看/修改" class="glyphicon glyphicon-eye-open"></span> </a> </td>
                            <td align="center"><a href="#" onclick="doDelete('${list.itemId}','${list.orderId}')">
                                <span title="删除订单" class="glyphicon glyphicon-remove"></span> </a></td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
        <tr bgcolor="white">
            <td colspan="4" style="text-align: center">
                <c:choose>
                    <c:when test="${requestScope.order.orderId != 0}">
                        <input type="button" value="添加明细"
                               onclick="document.location.href='<%=request.getContextPath()%>/view/orders/orderItemManager/new.jsp?orderId=${requestScope.order.orderId}'">
                    </c:when>
                </c:choose>
                <input type="button" value="订单列表" onclick="document.location.href='<%=request.getContextPath()%>/OrderServlet?method=list'">
            </td>
        </tr>
    </table>

</form>
</body>
</html>
