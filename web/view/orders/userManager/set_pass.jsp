<%--
  Created by IntelliJ IDEA.
  User: 兵哥哥
  Date: 2018/10/3
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改密码</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/mybs.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/view/js/jquery-1.10.2.min.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/view/js/bootstrap.min.js" ></script>
    <script type="text/javascript">
        function checkPass() {
            var old_pass = document.mainForm.old_pass.value;
            var old_pass2 = "${sessionScope.user.passWord}";
            var new_pass = document.mainForm.new_pass.value;
            var new_pass2 = document.mainForm.new_pass2.value;

            if (old_pass  == null || old_pass==undefined || old_pass=="") {
               alert("原密码不能为空！");
               return false;
            } else if (old_pass != old_pass2) {
                alert("旧密码不一致！");
                return false;
            } else if (new_pass  == null || new_pass==undefined || new_pass=="") {
                alert("密码不能为空！");
                return false;
            } else if (new_pass.length<6) {
                alert("密码必须大于6位字符！");
                return false;
            } else if (new_pass != new_pass2) {
                alert("新密码不一致！");
                return false;
            }
            document.mainForm.submit();
        }
        function resetQueryCondition() {
            // 设置所有的下拉框选择第一个
            // $('select').prop('selectedIndex', 0);
            document.mainForm.old_pass.value = "";
            document.mainForm.new_pass.value = "";
            document.mainForm.new_pass2.value = "";
        }
    </script>
</head>
<body>
<div class="container">
    <h1 class="page-header">修改密码</h1>
    <form action="<%=request.getContextPath()%>/UserServlet?method=update_pass" name="mainForm" class="form-horizontal" method="post">
        <div class="form-group">
            <div class="col-md-4">
                <input type="password" name="old_pass" class="form-control" placeholder="原密码" />
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-4">
                <input type="password" name="new_pass" class="form-control" placeholder="6位以上的新密码" />
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-4">
                <input type="password" name="new_pass2" class="form-control" placeholder="再次输出新密码"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-5">
                <input value="修改" type="button" class="btn btn-primary" onclick="checkPass()">
                <input value="重置" type="button" class="btn btn-primary" onclick="resetQueryCondition()">
                <input value="返回" type="button" class="btn btn-primary" onclick="javascript :history.back(-1);"/>
            </div>
        </div>
    </form>
</div>
</body>
</html>
