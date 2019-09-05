/**
 * Created by Administrator on 2019/8/23.
 */
$(function(){

    //鼠标点击输入框，样式发生改变
    // 点击后
    $('.form_text_ipt input').focus(function(){
        $(this).parent().css({
            'box-shadow':'0 0 3px #bbb',
        });
    });
    // 点击前
    $('.form_text_ipt input').blur(function(){
        $(this).parent().css({
            'box-shadow':'none',
        });
        //$(this).parent().next().hide();
    });

    //显示提示信息
    //propertychange：功能同oninput，用以替代oninput在IE9以下的不兼容性。
    // oninput&onchange：oninput和onchange都是事件对象，当输入框的值发生改变时触发该事件。不同的是，oninput是在值改变时立即触发，
    // 而onchange是在值改变后失去焦点才触发，并且可以用在非输入框中，如：select等。
    $('.form_text_ipt input').bind('input propertychange',function(){
        if($(this).val()==""){
            $(this).css({
                'color':'red',
            });
            $(this).parent().css({
                'border':'solid 1px red',
            });
            //$(this).parent().next().find('span').html('helow');
            $(this).parent().next().show();
        }else{
            $(this).css({
                'color':'#ccc',
            });
            $(this).parent().css({
                'border':'solid 1px #ccc',
            });
            $(this).parent().next().hide();
        }
    });

    $("#iptReUserPwd").bind("input propertychange", function () {
        var userPwd = $("#iptUserPwd").val()
        var reptUserPwd = $("#iptReUserPwd").val()

        if (userPwd != reptUserPwd) {
            $(this).css({
                'color':'red',
            });
            $(this).parent().css({
                'border':'solid 1px red',
            });
            //$(this).parent().next().find('span').html('helow');
            $(this).parent().next().show();
        }else {
            $(this).css({
                'color':'#ccc',
            });
            $(this).parent().css({
                'border':'solid 1px #ccc',
            });
            $(this).parent().next().hide();
        }

    })

});

// function rgtSuccess() {
//     alert("注册成功，点击跳转到登陆页面");
//     window.location.href = "/user_servlet"
//
// }