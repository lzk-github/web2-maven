$(function(){
	$("#editForm").validate({
		errorElement:'span',
		errorClass:'text-danger',
		rules:{
			company:{
				required:true
			},
			dept:{
				required:true
			}
		},
		messages:{
			company:{
				required:'请选择公司'
			},
			dept:{
				required:'请选择部门'
			}
		},
		submitHandler:function(){
			$.post("/acc/edit",$("#editForm").serialize()).done(function(data){
				
				if(data.state == "success") {
					layer.alert("修改成功",function(){
						window.location.href="/acc/list";
					});
				} else {
					layer.alert(data.message);
				}
			}).error(function(){
				layer.alert("服务器繁忙!")
			});
		}
	});
	$('#sendBtn').click(function(){
		$("#editForm").submit();
	});
	
});