<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/14
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%--<%@ include file="/common/taglibs.jsp"%>--%>
<html>
<head>
    <title>登陆界面</title>

    <script type="text/javascript" src="resource/plugin/jquery/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="resource/plugin/bootstrap-3/css/bootstrap.min.css">
    <link rel="stylesheet" href="resource/css/signin.css">

    <style type="text/css">
        .content {
            top: 0px;
            left:  0px;
            position: fixed;
            width: 100%;
            height: 100%;
            background-image: url("/resource/images/login-bg.jpg");
            background-position: center center;
            background-repeat: no-repeat;
            background-size: cover;
        }
    </style>
    <%
        String userNum = "";
        String userPwd = "";
//        获取当前站点的所有cookie
        Cookie[] cookies = request.getCookies();
        if (cookies[0] != null) {
            for (Cookie foo : cookies) {
                if ("userNum".equals(foo.getName())) {
                    userNum = foo.getValue();
                }else if ("userPwd".equals(foo.getName())) {
                    userPwd = foo.getValue();
                }
            }
        }

    %>

</head>
<body>
<div class="content">
    <div class="container">

        <form class="form-signin" action="/login_servlet" method="post">
            <h3 class="form-signin-heading">请登录</h3>
            <label for="inputLoginName" class="sr-only">账号</label>
            <input type="text" id="inputLoginName" name="loginNum" class="form-control" value="<%=userNum%>" placeholder="账号" autofocus="">
            <label for="inputLoginPwd" class="sr-only">密码</label>
            <input type="password" id="inputLoginPwd" name="loginPwd" class="form-control" value="<%=userPwd%>" placeholder="密码" autofocus="">
            <div class="checkbox">
                <label>
                    <input type="checkbox" name="rememberMe" value="yes"> 记住密码
                </label>
                <label>
                    <span style="color: orangered">${errMsg}</span>
                </label>
            </div>
            <label style="width: 49%">
                <button class="btn btn-lg btn-primary btn-block" id="submitLogin" type="submit">登陆</button>
            </label>
            <label style="width: 49%">
                <%--设置type属性，阻止默认提交方式，不写的话，默认是submit--%>
                <button class="btn btn-lg btn-primary btn-block" onclick="btnregisterClick()" type="button">注册</button>
            </label>


        </form>

    </div> <!-- /container -->
</div>


<script type="text/javascript">
    $("#submitLogin").on("click", function () {
        var loginName = $("#inputLoginName").val();
        var loginPwd = $("#inputLoginPwd").val();

        if (loginName == null || loginName.length <= 0) {
            alert("请输入用户名！")

            return false;
        }else if (loginPwd == null || loginPwd.length <= 0) {
            alert("请输入密码！")

            return false;
        }else {
            return true;
        }
    })
    
    function btnregisterClick() {
        window.location.href = "/register.jsp"
    }
</script>
</body>
</html>