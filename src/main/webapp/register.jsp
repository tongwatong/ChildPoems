<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>注册页面</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/resource/css/reset.css">
    <link rel="stylesheet" href="/resource/css/common.css">
    <link rel="stylesheet" href="/resource/plugin/font-awesome-4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="wrap login_wrap">
    <div class="content">

        <div class="logo"></div>
        <div class="login_box">

            <div class="login_form">
                <div class="login_title">
                    注册
                </div>
                <form action="/user_servlet" method="post">

                    <div class="form_text_ipt">
                        <input name="userNum" type="text" placeholder="注册账号">
                    </div>
                    <div class="ececk_warning"><span>账号不能为空</span></div>
                    <div class="form_text_ipt">
                        <input name="userName" type="text" placeholder="昵称">
                    </div>
                    <div class="ececk_warning"><span>昵称不能为空</span></div>
                    <div class="form_text_ipt" style="border: none">
                        <div style="text-align: left;margin-top: 9px">
                            <span style="font-size: 15px;text-align: center">性别：</span>
                            <select name="gender" style="margin-left: 10px">
                                <option value="man">男</option>
                                <option value="women">女</option>
                                <option value="three" selected="selected">保密</option>
                            </select>
                        </div>

                    </div>
                    <div class="form_text_ipt">
                        <input id="iptUserPwd" name="userPwd" type="password" placeholder="密码">
                    </div>
                    <div class="ececk_warning"><span>密码不能为空</span></div>
                    <div class="form_text_ipt">
                        <input id="iptReUserPwd" name="reUserPwd" type="password" placeholder="重复密码" onkeydown="iptPwdKeydow()">
                    </div>
                    <div class="ececk_warning" ><span>前后密码不一致</span></div>


                    <div class="form_btn">
                        <button type="submit">注册</button>
                    </div>
                    <div class="form_reg_btn">
                        <span>已有帐号？</span><a href="/login.jsp">马上登录</a>
                    </div>
                </form>
                <div class="other_login">
                    <div class="left other_left">
                        <span>其它登录方式</span>
                    </div>
                    <div class="right other_right">
                        <a href="#"><i class="fa fa-qq fa-2x"></i></a>
                        <a href="#"><i class="fa fa-weixin fa-2x"></i></a>
                        <a href="#"><i class="fa fa-weibo fa-2x"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="/resource/plugin/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/resource/js/common.js"></script>

</body>
</html>
