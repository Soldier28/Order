<%--
  Created by IntelliJ IDEA.
  User: 兵哥哥
  Date: 2018/10/13
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <link rel="stylesheet" href="../../../view/css/bootstrap.min.css" />
    <link rel="stylesheet" href="../../../view/css/mybs.css" />
    <script type="text/javascript" src="../../../view/js/jquery-1.10.2.min.js" ></script>
    <script type="text/javascript" src="../../../view/js/bootstrap.min.js" ></script>
    <script type="text/javascript">
        function username_change() {
            var _username = document.mainForm.username.value;
            if (_username == null || _username==undefined || _username=="") {
                document.getElementById("matter_username").innerHTML = "<span class='glyphicon glyphicon-info-sign'></span> 用户名/Email不可以为空！";
            } else {
                document.getElementById("matter_username").innerHTML = "";
            }
        }
        function Testing() {
            var _username = document.mainForm.username.value;
            var _password = document.mainForm.password.value;
            if (_username == null || _username==undefined || _username=="") {
                return false;
            } else if (_password == null || _password==undefined || _password=="") {
                alert("用户名不可以为空！");
                return false;
            } else if (_password.length < 6) {
                alert("密码必须大于6位字符！");
                return false;
            }
            document.mainForm.submit();
        }
    </script>
</head>
<body>
<div class="container">
    <h1 class="page-header"><span class="glyphicon glyphicon-user"></span> 用户注册</h1>
    <form action="/UserServlet?method=register" name="mainForm" class="form-horizontal" method="post">
        <div class="form-group">
            <div class="col-md-4">
                <input type="text" name="username" class="form-control" placeholder="用户名/Email" onmouseout="username_change()" />
            </div>
        </div>
        <div class="form-group">
            <div id="matter_username" style="color: #FF0000"></div>
        </div>
        <div class="form-group">
            <div class="col-md-4">
                <input type="password" name="password" class="form-control" placeholder="密码"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-5">
                <input type="button"  class="btn btn-primary" value="注册" onclick="Testing()"/>
            </div>
        </div>
    </form>
</div>
</body>
</html>
