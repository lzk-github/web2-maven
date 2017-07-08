$(function(){
	
	$("#password").keydown(function(event){
		if(event.keyCode == 13) {
			$("#loginForm").submit();
		}
	});
	
	$("#loginBtn").click(function(){
		$("#loginForm").submit();
	});
	
	$("#loginForm").validate({
		errorElement:"span",
		errorClass:"text-danger",
		rules:{
			userName:{
				required:true
			},
			password:{
				required:true
			}
		},
		messages:{
			userName:{
				required:"请输入用户名"
			},
			password:{
				required:"请输入密码"
			}
		},
		submitHandler:function(){
			$.post("/login",$("#loginForm").serialize()).done(function(data){
				if(data.state == "success") {
					if(data.data) {
						window.location.href=data.data;
					} else {
						window.location.href="/index.jsp";
					}
				} else {
					layer.alert(data.message);
				}
			}).error(function(){
				layer.alert("服务器忙!")
			});
		}
	});
});