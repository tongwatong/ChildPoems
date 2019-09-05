/**
 * Created by Administrator on 2019/8/1.
 */
//    JavaScript 弱类型的语言
//    function 关键字，用来声明方法（函数）
//    function 方法名（参数） {方法体}
function validate() {
    var txtLoginName = document.getElementById("txtLoginName");
    if (txtLoginName != null) {
        if (txtLoginName.value == undefined || txtLoginName.value.length <= 0) {
            alert("请输入登陆名！");
            return false;
        }
    }else {
        alert("代码出错，请联系管理员！");
        return false;
    }

    return true;
}