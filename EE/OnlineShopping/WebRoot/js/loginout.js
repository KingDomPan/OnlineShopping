$(document).ready(function() {
    $("#loginout").click(function() {
        $.messager.confirm('提示', '<b style="color:red">确定要离开胖嘟嘟商城?</b>', function(c) {
            if (c) {
                $.ajax({
                    method : "post",
                    dataType : "json",
                    url : "user/loginOut!loginOut",
                    success : function(data) {
                        if (data.success) {
                            //处理真正的退出操作
                            $.messager.show({
                                title : '信息提示',
                                msg : data.message,
                                showType : 'show',
                                timeout : 2000
                            });
                            setTimeout(function() {
                                location.replace(data.redirectURL);
                            }, 2000);
                        } else {
                            //处理未处理的订单状态
                            $.messager.confirm("退出", data.message, function(r) {
                                if (r) {
                                    //真正的退出
                                    $.ajax({
                                        dataType : "json",
                                        url : "user/realLoginOut!realLoginOut",
                                        method : "post",
                                        success : function(result) {
                                            //处理真正的退出操作
                                            $.messager.show({
                                                title : '信息提示',
                                                msg : result.message,
                                                showType : 'show',
                                                timeout : 2000
                                            });
                                            setTimeout(function() {
                                                location.replace(result.redirectURL);
                                            }, 2000);
                                        },
                                        error : function() {
                                        }
                                    });
                                } else {
                                    //处理订单
                                    location.replace("order.jsp");
                                }
                            });
                        }
                    },
                    error : function() {
                        alert("ee");
                    }
                });
            } else {

            }
        });
        return false;
    });
});
