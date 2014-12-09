$(document).ready(
		function() {
			$('#signupForm').submit(
					function(e) {
						if ($('#submit').hasClass('active'))
							return false;
						$('#submit').addClass('active');// 添加进度滚动条
						$.post($('#signupForm').attr('action'),
								$('#signupForm').serialize(), function(
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
									$('#submit').removeClass('active');
									setTimeout(function(){
										location.replace(response.redirectURL);
									}, 2000);
								}, 'json');
						e.preventDefault();
					});
			$(window).resize();
		});

$(window).resize(
		function() {
			var cf = $('#carbonForm');
			$('#carbonForm').css('margin-top',
					($(window).height() - cf.outerHeight()) / 2)
		});