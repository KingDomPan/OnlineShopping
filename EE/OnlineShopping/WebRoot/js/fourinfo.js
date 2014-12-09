$(function(){
	//// 幻灯片图片滑动
	$("html,body").animate({scrollTop:$("#slide").offset().top},800);
	var a =0;
	$("#slide>.slide_box").click(function(){
		var a = $(this).find(".navbox>.navtitle a").attr("href");// 获取方块下的的连接地址打开刚方块对应的图片链接
		window.open(a);
		return !1 
	}),
	$("#slide>.slide_box").mouseover(function(){
		var b =$(this).index();// //获取class的为slide_box的元素的索引值 其父元素id为slide
		if(b!= a){
			$("#slide .navsumary").hide();// /隐藏方块中对应的文字信息
			$(this).find(".navbox>.navsumary").show();
			var c = "bg" + (b + 1);// 获取当前方块的所对应的图片信息ֵ
			b < a && ($.browser.msie?(
				$(".slide_box").stop().animate({backgroundPositionX: "980px"}, 0).removeClass("bg1 bg2 bg3 bg4").addClass(c),
				$("#box_1").parent().stop().animate({backgroundPositionX: "0"}, 100),
				$("#box_2").parent().stop().animate({backgroundPositionX:"-245px"},200),
				$("#box_3").parent().stop().animate({backgroundPositionX: "-490px"},300),
				$("#box_4").parent().stop().animate({backgroundPositionX: "-735px"},400,
				function(){
					$(".slide_box,#slide").removeClass("bg1 bg2 bg3 bg4").addClass(c);
				}
			)):(
				$(".slide_box").stop().animate({backgroundPosition:"980px 0"},0).removeClass("bg1 bg2 bg3 bg4").addClass(c),
				$("#box_1").parent().stop().animate({backgroundPosition:"0 0"},100),
				$("#box_2").parent().stop().animate({backgroundPosition:"-245px 0"},200),
				$("#box_3").parent().stop().animate({backgroundPosition:"-490px 0"},300),
				$("#box_4").parent().stop().animate({backgroundPosition: "-735px 0"},400,
				function(){
					$(".slide_box,#slide").removeClass("bg1 bg2 bg3 bg4").addClass(c)
				}
			)), a = b),b > a &&($.browser.msie?(
				$(".slide_box").stop().animate({backgroundPositionX: "-980px" },0).removeClass("bg1 bg2 bg3 bg4").addClass(c),
				$("#box_1").stop().parent().animate({backgroundPositionX:"0"},400,
					function(){
						$(".slide_box,#slide").removeClass("bg1 bg2 bg3 bg4").addClass(c)
					}
				),
				$("#box_2").parent().stop().animate({ backgroundPositionX: "-245px" }, 300),
				$("#box_3").parent().stop().animate({backgroundPositionX: "-490px"}, 200),
				$("#box_4").parent().stop().animate({backgroundPositionX: "-735px"}, 100)):($(".slide_box").stop().animate({backgroundPosition:"-980px 0"},0).removeClass("bg1 bg2 bg3 bg4").addClass(c),
				$("#box_1").stop().parent().animate({backgroundPosition:"0 0"},400,
					function(){
						$(".slide_box,#slide").removeClass("bg1 bg2 bg3 bg4").addClass(c)
					}
				),
				$("#box_2").parent().stop().animate({backgroundPosition:"-245px 0"}, 300),
				$("#box_3").parent().stop().animate({backgroundPosition:"-490px 0"},200),
				$("#box_4").parent().stop().animate({backgroundPosition: "-735px 0"},100)
			), a = b)
		}
	});
		
});