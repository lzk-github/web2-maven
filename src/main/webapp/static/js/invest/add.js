$(function(){
	//定义标记,表征投资金额是否大于剩余金额
	var flag = true;
	
	$('#addBtn').click(function(){
		
		if(flag) {
			$('#addForm').submit();
		} else {
			layer.msg("warning:投资金额不能大于剩余金额!");
		}
		
	});
	
	$('#addForm').validate({
		errorElement:'span',
		errorClass:'text-danger',
		rules:{
			cust:{
				required:true
			},
			pro:{
				required:true
			},
			investMoney:{
				required:true,
				digits:true,
				min:1000,
			},
			signDate:{
				required:true
			}
			
		},
		messages:{
			cust:{
				required:"请输入客户姓名"
			},
			pro:{
				required:"请输入项目名称"
			},
			investMoney:{
				required:"请输入投资金额",
				digits:"请输入正确的数字",
				min:"最低投资金额1000￥",
			},
			signDate:{
				required:"请输入开始日期"
			}
		},
		submitHandler:function(){
			$.ajax({
				url:'/invest/add',
				type:'post',
				data:$('#addForm').serialize(),
				beforeSend:function(){
					$("#addBtn").attr("disabled","disabled").text("提交中...");
				},
				success:function(data){
					if(data.state == 'success') {
						layer.alert("新增成功",function(){
							window.location.href="/project/list";
						});
					} else {
						layer.msg(data.message);
					}
				},
				error:function(){
					layer.alert("服务器忙!");
				},
				complete:function(){
					$("#addBtn").removeAttr("disabled").text("新增");
				}
				
			});
		}
		
	});
	
	
	$("#pro").change(function(){
		var id = $(this).val();
				  
		$.getJSON("/pro/proChangeInInvest",{"id":id}).done(function(data){
			if(data.state == "success") {
				var choosedProject = data.data;
				$('#remainMoney').val(choosedProject.remainMoney);
				$('#repayRate').val(choosedProject.repayRate);
				$('#months').val(choosedProject.months);
			} else {
				layer.msg(data.message);
			}
		}).error(function(){
			layer.alert("服务器异常!xx");
		});
		
	});
	
	//投资金额不能大于剩余金额
	$("#investMoney").change(function(){
		var remainMoney = $('#remainMoney').val();
		
		var investMoney = $('#investMoney').val();
		if(parseInt(investMoney) > parseInt(remainMoney)) {
			layer.msg("投资金额不能大于剩余金额!");
			flag=false;
		} else {
			flag=true;
		}
	});
	
	
});








