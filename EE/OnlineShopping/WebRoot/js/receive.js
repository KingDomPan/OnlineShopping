$(document).ready(function() {
    $("#receiveForm").submit(function(e) {
        $.ajax({
            url : $('#receiveForm').attr('action'),
            data : $('#receiveForm').serialize(),
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
                    }, 1000);
                } else {
                    $.messager.alert("错误提示", "<span style='color:red'>" + "提交失败,请重试" + "</span>", "error");
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