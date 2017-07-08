<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>参数信息</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@include file="/WEB-INF/views/include/css.jsp" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

  	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/sider.jsp"%>
  <!-- =============================================== -->

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Main content -->
    <section class="content">

      <!-- Default box -->
              
      <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">系统参数设置</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form id="setForm" class="form-horizontal">
              
                <div class="form-group">
                  <label class="col-sm-2 control-label">佣金率率：</label>

                  <div class="col-sm-8">	
                    <input type="text" name="commission" class="form-control" id="commission"  value="${commission}">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">积分兑换率：</label>

                  <div class="col-sm-8">
                    <input type="text" name="giftPointRate" class="form-control" id="giftPointRate" value="${giftPointRate}">
                  </div>
                </div>
              <div class="box-footer">
                 <div class="col-sm-offset-2 col-sm-8">
                    <button type="button" id="setBtn" class="btn btn-primary">保存修改</button>
                    <button type="reset" class="btn btn-default">重置</button>
                 </div>
              </div>
              <!-- /.box-footer -->
            </form>        
      </div>
      <!-- /.box -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 2.3.8
    </div>
    <strong>Copyright &copy; 2014-2016 <a href="">凯盛软件</a>.</strong> All rights
    reserved.
  </footer>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/include/js.jsp" %>

</body>
<script>
  $(function(){
    $('#setBtn').click(function(){
    	
    	$('#setForm').submit();
    });
    
    $('#setForm').validate({
    	errorElement:'span',
    	errorClass:'text-danger',
    	rules:{
    		commissionValue:{
    			required:true,
    			number:true,
    			max:1,
    			min:0
    		},
    		giftPointRate:{
    			required:true,
    			number:true,
    			max:1,
    			min:0
    		}
    	},
    	messages:{
    		commission:{
    			required:'请输入佣金率',
    			number:'请输入数字',
    			max:'不能大于1',
    			min:'不能小于0'
    		},
    		giftPointRate:{
    			required:'请输入积分兑换率',
    			number:'必须为数字',
    			max:'不能大于1',
    			min:'不能小于0'
    		}
    	},
    	submitHandler:function(){
    		var commission = $('#commission').val();
    		var giftPointRate = $('#giftPointRate').val();
    		$.post('/settings/set',{
    			'commission':commission,
    			'giftPointRate':giftPointRate
    		}).done(function(data){
    			if(data.state == "success") {
    				layer.alert("保存成功!");
    			} else {
    				layer.alert(data.message);
    			}
    		}).error(function(){
    			layer.alert("服务器异常!");
    		});
    	}
    });
    
    
  });


</script>
</html>