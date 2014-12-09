$(document).ready(function() {
    $("#updateForm").submit(function(e) {
        var pass = $("#password").val();
        var repass = $("#repassword").val();
        if (pass != repass) {
            alert("密码不一致");
            return false;
        }
        $.ajax({
            url : $('#updateForm').attr('action'),
            data : $('#updateForm').serialize(),
            type : "POST",
            dataType : "json",
            success : function(response, textStatus, jqXHR) {
                if (response.success) {
                    $.messager.show({
                        msg : "<span style='color:red'>" + response.message + "</span>",
                        showType : 'show',
                    });
                    setTimeout(function() {
                        location.replace(response.redirectURL);
                    }, 2000);
                } else {
                    $.messager.alert("错误提示", "<span style='color:red'>" + "修改个人资料失败" + "</span>", "error");
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
        e.preventDefault();
    });
}); 