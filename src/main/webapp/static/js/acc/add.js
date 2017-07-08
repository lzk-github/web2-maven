$(function(){
	/*表单校验*/
	$("#addForm").validate({
		errorElement:'span',
		errorClass:"text-danger",
		rules:{
			"userName":{
				required:true
			},
			"idNo":{
				required:true,
				rangelength:[5,5]
			},
			"tel":{
				required:true,
				rangelength:[5,5]
			},
			"company":{
				required:true
			},
			"dept":{
				required:true
			}
			
		},
		messages:{
			"userName":{
				required:'请输入用户名'
			},
			"idNo":{
				required:'请输入身份证号',
				rangelength:'身份证号长度为5'
			},
			"tel":{
				required:'请输入电话号码',
				rangelength:'电话号码长度为5'
			},
			"company":{
				required:'请选择公司id'
			},
			"dept":{
				required:'请选择部门'
			}
		},
		/*validate下的表单ajax提交*/
		submitHandler:function(){
			$.ajax({
				url:"/acc/add",
				type:"post",
				data:$("#addForm").serialize(),
				beforeSend:function(){
					$("#sendBtn").attr("disabled","disabled").text("保存中...");
				},
				success:function(data){
					if(data.state == "success") {
						layer.alert("保存成功",function(){
							window.location.href="/acc/list";
						});
					} else {
						layer.alert(data.message);
						
					}
				},
				error:function(data){
					console.log(data);
					layer.alert(data.state);
				},
				complete:function(){
					$("#sendBtn").removeAttr("disabled").text("新增");
				}
			});
		}
		
	});
	/*表单提交*/
	
	$("#sendBtn").click(function(){
		$("#addForm").submit();
	});
	
	
});