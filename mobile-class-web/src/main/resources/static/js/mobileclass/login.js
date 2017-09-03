/**
 * Created by LXChild on 25/04/2017.
 */
function userRegister() {

    var name = $("#name-register-model").val();
    var password = $("#password-register-model").val();
    var passwordDuplicate = $("#password-duplicate-register-model").val();
    var mobile = $("#mobile-register-model").val();
    var email = $("#email-register-model").val();
    var realName = $("#real-name-register-model").val();

    var nameReg = /^[a-z0-9]+$/;
    var mobileReg = /^((13[0-9])|(15[^4,\D])|(18[0,5-9]))\d{8}$/;
    var emailReg = /^([a-z0-9A-Z]+[-|\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\.)+[a-zA-Z]{2,}$/;

    var complexReg = new RegExp('(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,30}');

    if (name == null || name == "") {
        alert("用户名不能为空");
        return;
    }
    if (password == null || password == "") {
        alert("密码不能为空");
        return;
    }
    if (mobile == null || mobile == "") {
        alert("手机号不能为空");
        return;
    }
    if (email == null || email == "") {
        alert("邮箱不能为空");
        return;
    }
    if (realName == null || realName == "") {
        alert("真实姓名不能为空");
        return;
    }

    if (password != passwordDuplicate) {
        alert("两次密码输入不一致");
        return;
    }

    if (name.length < 5 || name.length > 20) {
        alert("用户名长度只能在6-20位之间");
        return;
    }

    if (!nameReg.test(name)) {
        alert("用户名只能是小写字母和数字的组合");
        return;
    }

    if (!complexReg.test(password)) {
        alert("密码必须是8-20位大小写字母数字和特殊符号的组合");
        return;
    }

    if (!mobileReg.test(mobile)) {
        alert("无效的手机号");
        return;
    }

    if (!emailReg.test(email)) {
        alert("无效的邮箱");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/users/register",
        data: {name: name, password: password, mobile: mobile, email: email, realName: realName},
        success: function (result) {
            if (result == true) {
                alert("注册成功");
                window.location.reload();
            } else {
                alert("注册失败");
            }

        },
        error: function (result) {
            alert("请求失败");
        }
    });
}
