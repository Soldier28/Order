<%--
  Created by IntelliJ IDEA.
  User: 兵哥哥
  Date: 2018/10/4
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增订单</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/view/js/util.js"></script>
    <script language="JavaScript">
        function doAdd() {
            var _orderCode = document.mainForm.orderCode.value;
            if (_orderCode == null|| _orderCode == undefined || _orderCode == "") {
                alert("添加订单失败！");
                return false;
            }
            document.mainForm.submit();
        }
    </script>
</head>
<body>
<form action="${pageContext.request.contextPath}/OrderServlet?method=add" name="mainForm" method="post">
    <div style="text-align: center"><h2>新增订单</h2></div>
    <table align="center" bgcolor="black" cellpadding="5" cellspacing="1" border="0" width="700px">
        <tr bgcolor="white">
            <td colspan="2" style="text-align: center">
                订单详情
            </td>
        </tr>
        <tr bgcolor="white">
            <td>
                订单名称
            </td>
            <td>
                <input name="orderCode" type="text">
            </td>
        </tr>
        <tr bgcolor="white">
            <td>
                订单配送方式
            </td>
            <td>
                <select name="deliveryMethod">
                    <option value="自取">
                        自取
                    </option>
                    <option value="邮寄">
                        邮寄
                    </option>
                    <option value="快递">
                        快递
                    </option>
                </select>
            </td>
        </tr>
        <tr bgcolor="white">
            <td colspan="4" style="text-align: center">
                <input value="添加" type="button" onclick="doAdd()">
                <input value="返回" type="button" onclick="javascript :history.back(-1);"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
