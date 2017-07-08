$(function(){
	//删除按钮
	$(".del").click(function(){
		var id=$(this).attr("rel");
		layer.alert("确认删除吗?",function(){
			$.get("/acc/del",{"id":id}).done(function(data){
				if(data.state == "success") {
					layer.alert("删除成功!",function(){
						
						window.history.go(0);
					});
				} else {
					layer.msg(data.message);
				}
			}).error(function(){
				layer.alert("服务器异常!");
			});
		});
	});
	
	
});