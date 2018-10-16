<%--
  Created by IntelliJ IDEA.
  User: 兵哥哥
  Date: 2018/10/8
  Time: 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增订单明细</title>
    <script type="text/javascript">
        window.onload = function () {
            document.mainForm.discountType.onchange = function () {
                var _discountType = document.mainForm.discountType.value;
                if (_discountType == "1") {
                    document.mainForm.discount.disabled = "disabled";
                    document.mainForm.promotionPrice.disabled = "disabled";
                    document.mainForm.promotionNum.disabled = "disabled";
                } else if (_discountType == "2") {
                    document.mainForm.discount.disabled = "";
                    document.mainForm.promotionPrice.disabled = "disabled";
                    document.mainForm.promotionNum.disabled = "disabled";
                } else if (_discountType == "3") {
                    document.mainForm.discount.disabled = "disabled";
                    document.mainForm.promotionPrice.disabled = "";
                    document.mainForm.promotionNum.disabled = "";
                }
            }
        }
        function doAdd() {
            var name = document.mainForm.name.value;
            if (name == null || name==undefined || name=="") {
                alert("请输入货物名称！");
                return false;
            }
            document.mainForm.submit();
        }
        function updateSum1() {
            var temp_discountType = eval("document.mainForm.discountType.value");
            var unitNum = eval("document.mainForm.unitNum.value");
            var unitPrice = eval("document.mainForm.unitPrice.value");
            if (temp_discountType == 1) {  //无折扣
                document.mainForm.sum.value = unitNum * unitPrice;
            }
        }
        function updateSum2() {
            var temp_discountType = eval("document.mainForm.discountType.value");
            var unitNum = eval("document.mainForm.unitNum.value");
            var unitPrice = eval("document.mainForm.unitPrice.value");
            if (temp_discountType == 2) {  //有折扣
                var discount = eval("document.mainForm.discount.value");
                document.mainForm.sum.value = unitNum * unitPrice * discount;
            }
        }
        function updateSum3() {
            var temp_discountType = eval("document.mainForm.discountType.value");
            var unitNum = eval("document.mainForm.unitNum.value");
            var unitPrice = eval("document.mainForm.unitPrice.value");
            if (temp_discountType == 3) {  //促销
                var promotionNum = eval("document.mainForm.promotionNum.value");
                var promotionPrice = eval("document.mainForm.promotionPrice.value");
                document.mainForm.sum.value ="";
                document.mainForm.sum.value = (unitNum - promotionNum)* unitPrice + promotionNum * promotionPrice;
            }
        }
    </script>
</head>
<body>
<form name="mainForm" action="/OrderItemServlet?method=add" method="post">
    <div style="text-align: center"><h2>新增订单明细</h2></div>
    <table align="center" bgcolor="black" cellpadding="5" cellspacing="1" border="0" width="700px">
        <tr bgcolor="white">
            <td colspan="4">
                当前订单编号为：${param.orderId}
                <input type="hidden" name="orderId"value="${param.orderId}">
            </td>
        </tr>
        <tr bgcolor="white">
            <td>货物名称</td>
            <td><input name="name" type="text"></td>
            <td>折扣类型</td>
            <td><select name="discountType">
                <option value="1">无折扣</option>
                <option value="2">有折扣</option>
                <option value="3">促销</option>
            </select></td>
        </tr>
        <tr bgcolor="white">
            <td>货物数量</td>
            <td><input name="unitNum" type="text"></td>
            <td>货物单价</td>
            <td><input name="unitPrice" type="text" onchange="updateSum1()"></td>
        </tr>
        <tr bgcolor="white">
            <td>货物总价</td>
            <td><input name="sum" type="text" readonly="readonly"></td>
            <td>折扣率</td>
            <td><input name="discount" type="text" onchange="updateSum2()" disabled="disabled"></td>
        </tr>
        <tr bgcolor="white">
            <td>促销单价</td>
            <td><input name="promotionPrice" type="text" disabled="disabled"></td>
            <td>促销数量</td>
            <td><input name="promotionNum" type="text" onchange="updateSum3()" disabled="disabled"></td>
        </tr>
        <tr bgcolor="white">
            <td colspan="4" align="center">
                <input type="button" value="添加" onclick="doAdd()"/>
                <input value="返回" type="button" onclick="javascript :history.back(-1);"/>
            </td>
        </tr>
    </table>

</form>
</body>
</html>
