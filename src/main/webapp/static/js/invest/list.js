$(function(){
	$('.del').click(function(){
		var id = $(this).attr("rel");
		layer.confirm("确定删除吗?",function(){
			$.get('/invest/del',{"id":id}).done(function(data){
				if(data.state == "success") {
					layer.alert("删除成功",function(){
						window.history.go(0);
					});
				} else {
					layer.alert(data.message);
				}
			}).error(function(){
				layer.alert("服务器异常!");
			});
		});
	});

	$('.unuse').click(function(){
		var id = $(this).attr("rel");
		$.get('/invest/unuse',{"id":id}).done(function(data){
			if(data.state == "success") {
				layer.alert("解约成功",function(){
					window.history.go(0);
				});
			} else {
				layer.alert(data.message);
			}
		}).error(function(){
			layer.alert("服务器异常!");
		});
	});
	
	$('.concat').click(function(){
		var id = $(this).attr("rel");
		$.ajax({
			url:'/invest/concat',
			type:'get',
			data:{"id":id},
			success:function(data){
				if(data.state == 'success') {
					$('#cust').val(data.customer.id);
					$("#custName").val(data.customer.name);
					$("#pro").val(data.project.id);
					$("#proName").val(data.project.projectName);
					$("#remainMoney").val(data.project.remainMoney);
					$("#investMoney").val(data.invest.investMoney);
					$("#repayRate").val(data.invest.rate);
					$("#months").val(data.invest.months);
					
					var months = data.invest.months;
					var sd = data.invest.endDate;
					var ed = moment(sd).add(months,"months").format("YYYY-MM-DD");
					$("#signDate").val(sd);
					$("#endDate").val(ed);
					
					$('#concatModal').modal({
						show:true,
						backdrop:'static'
					});
				} else {
					layer.alert(data.message);
				}
			},
			error:function(){
				layer.alert("系统忙碌中....");
			}
		});
	});
	
	$("#concatBtn").click(function(){
		  $.ajax({
			 url:"/invest/add",
			 type:"post",
			 data:$("#concatForm").serialize(),
			 success:function(data){
				 if(data.state == 'success') {
						layer.alert("续约成功",function(){
							window.history.go(0);
						});
					} else{
						layer.msg(data.message);
					}
			 },
			 error:function(){
				 layer.msg("系统异常");
			 }
		  });
	  });
	
});











