<%--
  Created by IntelliJ IDEA.
  User: 兵哥哥
  Date: 2018/10/8
  Time: 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>查看/修改订单明细</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/view/js/jquery-1.10.2.min.js" ></script>
    <script type="text/javascript">
        //window.onload: 页面加载完成
        window.onload = function () {
            document.mainForm.discountType.onchange = function () {
                var _discountType = document.mainForm.discountType.value;
                var unitNum = eval("document.mainForm.unitNum.value");
                var unitPrice = eval("document.mainForm.unitPrice.value");
                if (_discountType == "1") {  //无折扣
                    document.mainForm.discount.disabled = "disabled";
                    document.mainForm.promotionPrice.disabled = "disabled";
                    document.mainForm.promotionNum.disabled = "disabled";
                    document.mainForm.sum.value = unitNum * unitPrice;
                } else if (_discountType == "2") {  //有折扣
                    document.mainForm.discount.disabled = "";
                    document.mainForm.promotionPrice.disabled = "disabled";
                    document.mainForm.promotionNum.disabled = "disabled";
                    var discount = eval("document.mainForm.discount.value");
                    document.mainForm.sum.value = unitNum * unitPrice * discount;
                } else if (_discountType == "3") {  //促销
                    document.mainForm.discount.disabled = "disabled";
                    document.mainForm.promotionPrice.disabled = "";
                    document.mainForm.promotionNum.disabled = "";
                    var promotionNum = eval("document.mainForm.promotionNum.value");
                    var promotionPrice = eval("document.mainForm.promotionPrice.value");
                    document.mainForm.sum.value = (unitNum - promotionNum)* unitPrice + promotionNum * promotionPrice;
                }
            }
        }
        function doUpdate() {
            var temp_discountType = eval("document.mainForm.discountType.value");
            var unitNum = eval("document.mainForm.unitNum.value");
            var unitPrice = eval("document.mainForm.unitPrice.value");
            if (temp_discountType == 1) {  //无折扣
                document.mainForm.sum.value = unitNum * unitPrice;
            } else if (temp_discountType == 2) {  //有折扣
                var discount = eval("document.mainForm.discount.value");
                document.mainForm.sum.value = unitNum * unitPrice * discount;
            } else if (temp_discountType == 3) {  //促销
                var promotionNum = eval("document.mainForm.promotionNum.value");
                var promotionPrice = eval("document.mainForm.promotionPrice.value");
                document.mainForm.sum.value = (unitNum - promotionNum)* unitPrice + promotionNum * promotionPrice;
            }
            document.mainForm.action = "<%=request.getContextPath()%>/OrderItemServlet?method=update";
            document.mainForm.submit();
        }
    </script>
</head>
<body>
<form name="mainForm" action="" method="post">
    <div style="text-align: center"><h2>查看/修改 订单明细</h2></div>
    <table align="center" bgcolor="black" cellpadding="5" cellspacing="1" border="0" width="700px">
        <tr bgcolor="white">
            <td colspan="2">
                订单编号：${requestScope.orderItem.orderId}
                <input type="hidden" name="orderId"value="${requestScope.orderItem.orderId}"/>
            </td>
            <td colspan="2">
                订单明细编号:${requestScope.orderItem.itemId}
                <input type="hidden" name="itemId" value="${requestScope.orderItem.itemId}"/>
            </td>
        </tr>
        <tr bgcolor="white">
            <td>货物名称</td>
            <td><input name="name" type="text" value="${requestScope.orderItem.name}"></td>
            <td>折扣类型</td>
            <td>
                <select id="selects" name="discountType">
                    <option value="1">无折扣</option>
                    <option value="2">有折扣</option>
                    <option value="3">促销</option>
                </select>
                <input type="hidden" name="temp_discountType" value="${requestScope.orderItem.discountType}"/>
                <script type="text/javascript">
                    $(function () {
                       $("#selects").val("${requestScope.orderItem.discountType}");
                    })
                </script>
            </td>
        </tr>
        <tr bgcolor="white">
            <td>货物数量</td>
            <td><input name="unitNum" type="text" value="${requestScope.orderItem.unitNum}"></td>
            <td>货物单价</td>
            <td><input name="unitPrice" type="text" value="${requestScope.orderItem.unitPrice}"></td>
        </tr>
        <tr bgcolor="white">
            <td>货物总价</td>
            <td><input name="sum" type="text" value="${requestScope.orderItem.sum}"></td>
            <td>折扣率</td>
            <td>
                <c:choose>
                    <c:when test="${requestScope.orderItem.discountType == 2}">
                        <input type="text" name="discount" value="${requestScope.orderItem.discount}"/>
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="discount" disabled="disabled"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr bgcolor="white">
            <td>促销单价</td>
            <td>
                <c:choose>
                    <c:when test="${requestScope.orderItem.discountType == 3}">
                        <input type="text" name="promotionPrice" value="${requestScope.orderItem.promotionPrice}""/>
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="promotionPrice" disabled="disabled"/>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>促销数量</td>
            <td>
                <c:choose>
                    <c:when test="${requestScope.orderItem.discountType == 3}">
                        <input type="text" name="promotionNum" value="${requestScope.orderItem.promotionNum}"/>
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="promotionNum" disabled="disabled"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr bgcolor="white">
            <td colspan="4" align="center">
                <input type="button" value="修改" onclick="doUpdate()"/>
                <input value="返回" type="button" onclick="javascript :history.back(-1);"/>
            </td>
        </tr>
    </table>

</form>
</body>
</html>
