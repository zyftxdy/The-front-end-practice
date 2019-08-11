			$(function(){
				
				var slideBox=$(".slideBox");
				var ul=slideBox.find("ul");
				var oneWidth=slideBox.find("ul li").eq(0).width();
				var spannumber= slideBox.find(".spanBox a");
				var sw=0;
				var t;
				spannumber.bind("click",function(){
					$(this).addClass("active").siblings("a").removeClass("active");
					sw=$(this).index();
					ul.animate({"right":890*sw})
				});
				
				t=setInterval(function(){
					sw++;
					if(sw==spannumber.length){
						sw=0;
					}
					spannumber.eq(sw).trigger("click");
				},2000);
				slideBox.hover(function(){
					clearInterval(t);
				},
				function(){
					t=setInterval(function(){
						sw++;
						if(sw==spannumber.length){
							sw=0;
						}
						spannumber.eq(sw).trigger("click");
					},2000);
				})
			});					