$(document).ready(function() {
    $("a.addItemToOrder").click(function() {
        var param = $(this).attr("param");
        var params = param.split("&");
        $.ajax({
            type : "post",
            url : 'salesorder/addItemToOrder',
            data : {
                pid : params[0],
                unitPrice : params[1]
            },
            success : function(data, textStatus, jqXHR) {
                if (data.success) {
                	$.messager.show({
                		title:'信息提示',  
                        msg:data.message+"<br/><a href='order.jsp' target='_blank'>查看购物车</a>",  
                        showType:'show',
                        timeout:5000
                	});
                } else {
                	if(data.redirectURL){
                		$.messager.show({
                    		title:'信息提示',  
                            msg:data.message,  
                            showType:'show',
                            timeout:2000
                    	});
                		setTimeout(function(){
							location.replace(data.redirectURL);
						}, 2000);
                	}
                }
            },
            dataType : "json"
        });
        return false;
    });
}); 