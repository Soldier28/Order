<%--
  Created by IntelliJ IDEA.
  User: 兵哥哥
  Date: 2018/10/3
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单查询</title>
    <script type="text/javascript">
        function resetQueryCondition() {
            document.getElementById("input1").value = "";
            document.getElementById("input2").value = "";
            document.getElementById("selectId").options[0].selected = true;
        }
    </script>
</head>
<body>

<form action="/OrderServlet?method=search" method="post" name="mainForm">
    <div style="text-align: center"><h2>订单查询</h2></div>
    <table align="center" bgcolor="black" cellpadding="5" cellspacing="1" border="0" width="700px">
        <tr bgcolor="white">
            <td>订单编号</td>
            <td><input name="orderCode" id="input1" type="text"></td>
        </tr>
        <tr bgcolor="white">
            <td>订单金额</td>
            <td><input name="sum" id="input2" type="text"></td>
        </tr>
        <tr bgcolor="white">
            <td>配送方式</td>
            <td>
                <select name="deliveryMethod" id="selectId">
                    <option value="自取">自取</option>
                    <option value="邮寄">邮寄</option>
                    <option value="快递">快递</option>
                </select>
            </td>
        </tr>
        <tr bgcolor="white">
            <td style="text-align: center" colspan="2">
                <input value="查询" type="submit"/>
                <input value="重置" type="button" onclick="resetQueryCondition()"/>
                <input value="返回" type="button" onclick="javascript :history.back(-1);"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
