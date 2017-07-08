<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>新增投资信息(客户-->投资)</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@include file="../include/css.jsp" %>
  <link rel="stylesheet" href="/static/plugins/select2/select2.css" />
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

  <%@include file="../include/header.jsp" %>
  <%@include file="../include/sider.jsp" %>

  <!-- =============================================== -->

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Main content -->
    <section class="content">

      <!-- Default box -->
              
      <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">新增投资信息</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form class="form-horizontal" id="addForm">
              <div class="box-body">
                <div class="form-group">
                  <label class="col-sm-2 control-label">客户名称：(<span class="text-danger"><strong>*</strong></span>)</label>
                  <div class="col-sm-8">
                    <select name="cust" id="cust" class="form-control">
                      <option value="">--请选择客户名称--</option>
                      <c:forEach items="${customerList}" var="cust">
                      	 <option value="${cust.id}">${cust.name}-${cust.tel}</option>
                      </c:forEach>
                    </select>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">项目名称：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <select name="pro" id="pro" class="form-control">
                      <option value="">--请选择项目--</option>
                     <c:forEach items="${projectList}" var="pro">
                     	<option value="${pro.id}" 
                     	>${pro.projectName}</option>
                     </c:forEach>
                    </select>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">剩余金额：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" readonly value="${project.remainMoney }" class="form-control" id="remainMoney" name="remainMoney">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">投资金额：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="investMoney"  name="investMoney">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">回报率：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                      <input type="text" readonly value="${project.repayRate }" class="form-control" id="repayRate" name="repayRate"  placeholder="">
                  </div>
                </div>
                   <div class="form-group">
                  <label class="col-sm-2 control-label">期限：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" readonly value="${project.months }" class="form-control" id="months" name="months">
                  </div>
                </div>
                 <div class="form-group">
                  <label class="col-sm-2 control-label">签订日期：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" name="signDate" id="signDate" >
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">到期日期：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input readonly type="text" class="form-control" id="endDate" name="endDate">
                  </div>
                </div>
                
              </div>
              <!-- /.box-body -->
              <div class="box-footer">
                 <div class="col-sm-offset-2 col-sm-8">
                    <button type="button" id="addBtn" class="btn btn-primary">新增</button>
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
    <strong>Copyright &copy; 2017-2019 <a href="">凯盛软件</a>.</strong> All rights
    reserved.
  </footer>
</div>
<!-- ./wrapper -->
<%@include file="../include/js.jsp" %>
<!-- date -->
<script src="/static/js/moment.js"></script>
<script src="/static/plugins/select2/select2.js"></script>
<script src="/static/js/moment.js"></script>
<script src="/static/plugins/select2/select2.min.js"></script>
<script src="/static/js/invest/add.js"></script>
</body>
<script>
  $(function(){
	  $('#pro').val("${project.id}");
	  $("#cust").select2();
	  $("#pro").select2();
	  
   		$("#signDate").datepicker({
           format:"yyyy-mm-dd",
           language:"zh-CN",
           autoclose: true,
           todayBtn:'linked',
	       todayHighlight:true,
           startDate : moment().format("YYYY-MM-DD")
       }).on('changeDate',function(e){
    	   var months = $('#months').val();
    	   var dateres = moment(e.format("yyyy-mm-dd")).add(months,'months').format("YYYY-MM-DD");
    	   $('#endDate').val(dateres);
       });
   		
   		
  });

</script>
</html>
    