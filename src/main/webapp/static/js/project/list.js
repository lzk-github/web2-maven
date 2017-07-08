$(function(){
	$('#sumMoney').change(function(){
		$('#remainMoney').val($('#sumMoney').val());
		
	});
	$("#addProject").click(function(){
		 
		  $("#addModal").modal({
	           show:true,
	           backdrop:'static'
	       });
		  
	  });
	  
	  $("#startDate").datepicker({
        format: "yyyy-mm-dd",
        language: "zh-CN",
        autoclose: true,
        todayBtn:'linked',
        todayHighlight:true,
        startDate : moment().format("YYYY-MM-DD")
  	}).on('changeDate', function(e) {
	  //获得周期控件的值
	  var month = $("#months").val();
	  var date = moment(e.format('yyyy-mm-dd')).add(month,'months').format("YYYY-MM-DD");
	  $("#endDate").val(date);
  });
	
	  $('#addBtn').click(function(){
		  $('#addForm').submit();
		  
	  });
	  $('#addForm').validate({
		  errorElement:'span',
		  errorClass:'text-danger',
		  rules:{
			  projectName:{
				  required:true
			  },
			  sumMoney:{
				  required:true,
				  digits:true
			  },
			  repayRate:{
				  required:true,
				  number:true
			  },
			  months:{
				  required:true
			  },
			  startDate:{
				  required:true
			  },
			  
			  
		  },
		  messages:{
			  projectName:{
				  required:'请输入项目名称'
			  },
			  sumMoney:{
				  required:'请输入项目总金额',
				  digits:"请输入整数金额"
			  },
			  repayRate:{
				  required:'请输入回报率',
				  number:'请输入有效数字'
			  },
			  months:{
				  required:'请输入项目时长/月'
			  },
			  startDate:{
				  required:'请输入项目开始日期'
			  },
		  },
		  submitHandler:function(){
			  $.post("/project/add",$('#addForm').serialize()).done(function(data){
				  if(data.state == 'success') {
					  layer.msg("新增项目成功!",function(){
						  window.location.href="/project/list";
					  });
				  } else {
					  layer.alert(data.message);
				  }
			  }).error(function(){
				  layer.alert("服务器忙!")
			  });
		  }
	  });
	  
	  
	  
});











