<%--
  Created by IntelliJ IDEA.
  User: 兵哥哥
  Date: 2018/9/30
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <!-- 一个“../”是返回当前文件所在文件夹的上一层目录 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/mybs.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/view/js/jquery-1.10.2.min.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/view/js/bootstrap.min.js" ></script>
</head>
<body>
    <div class="container">
        <h1 class="page-header"><span class="glyphicon glyphicon-user"></span> 用户登录</h1>
        <form action="${pageContext.request.contextPath}/LoginServlet" name="mainForm" class="form-horizontal" method="post">
            <div class="form-group">
                <div class="col-md-4">
                    <input type="text" name="username" class="form-control" placeholder="用户名/Email" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-4">
                    <input type="password" name="password" class="form-control" placeholder="密码" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-6">
                    <div>自动登录时间:</div>
                    <input type="radio" name="auto_login" value="1">一周
                    <input type="radio" name="auto_login" value="2">一个月
                    <input type="radio" name="auto_login" value="3">三个月
                    <input type="radio" name="auto_login" value="4">一年
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-5">
                    <input type="submit"  class="btn btn-primary" value="登录"/>
                    <input type="button"  class="btn btn-primary" value="注册" onclick="window.location.href='../reg/register.jsp'"/>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
