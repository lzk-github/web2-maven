$(function(){
		$(".del").click(function(){
			var id = $(this).attr("rel");
			
			layer.confirm("确认删除吗?",function(){
				
				$.ajax({
					url:"/com/del?id="+id,
					type:"get",
					success:function(data){
						if(data.state == "success") {
							layer.alert("删除成功!",function(){
								window.history.go(0);
								
							});
						} else {
							layer.msg(data.message);
						}
					},
					error:function(){
						layer.alert("服务器忙!");
					}
				});
			});
		});
		
	});








