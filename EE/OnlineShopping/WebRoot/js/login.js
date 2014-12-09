$(document).ready(
		function() {
			$('#login').submit(
					function(e) {
						$.post($('#login').attr('action'),
								$('#login').serialize(), function(
										response, textStatus, jqXHR) {
									if (response.success) {
										$.messager.show({
											msg : "<span style='color:red'>"+response.message+"</span>",
											showType : 'show',
										});
									} else {
										$.messager.alert("错误提示",
												"<span style='color:red'>"+response.message+"</span>", "error");
									}
									setTimeout(function(){
										location.replace(response.redirectURL);
									}, 2000);
								}, 'json');
						e.preventDefault();
					});
		});
