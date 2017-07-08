<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>编辑客户信息</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  
  
  <%@ include file="/WEB-INF/views/include/css.jsp"%>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

  <%@ include file="/WEB-INF/views/include/header.jsp" %>
  <%@ include file="/WEB-INF/views/include/sider.jsp" %>
  
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Main content -->
    <section class="content">

      <!-- Default box -->
              
      <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">编辑客户信息</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form class="form-horizontal" id="editForm">
              <div class="box-body">
                <div class="form-group">
                  <label class="col-sm-2 control-label">客户名称：(<span class="text-danger"><strong>*</strong></span>)</label>
                  <div class="col-sm-8">
                    <select name="name" id="name" class="form-control">
                      <option value="">--请选择客户名称--</option>
                      <c:forEach items="${customerList}" var="cust">
                      	 <option class="option" a="${cust.tel }" value="${cust.id}">${cust.name}-${cust.idNo}</option>
                      </c:forEach>
                    </select>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">身份证号：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input readonly type="text" class="form-control" name="idNo" id="idNo" value="${customerObj.name}">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">手机号码：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" name="tel" id="tel" value="${customerObj.tel}">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">银行卡号：(<span class="text-danger"><strong>*</strong></span>)</label>
                  <div class="col-sm-8">
                    <input type="text" class="form-control" name="bankNo" id="bankNo" value="${customerObj.bankNo}">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">开户行：(<span class="text-danger"><strong>*</strong></span>)</label>
                  <div class="col-sm-8">
                    <input type="text" class="form-control" name="bankName" id="bankName" value="${customerObj.bankName}">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">生日：(<span class="text-danger"><strong>*</strong></span>)</label>
                  <div class="col-sm-8">
                    <input type="text" class="form-control" name="birthday" id="birthday" value="${customerObj.birthday}">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">地址：(<span class="text-danger"><strong>*</strong></span>)</label>
                  <div class="col-sm-8">
                    <input type="text" class="form-control" name="address" id="address" value="${customerObj.address}">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">备注：</label>
                  <div class="col-sm-8">
                    <textarea rows="6" class="form-control" name="remark" id="remark">${customerObj.remark}</textarea>
                  </div>
                </div>
               
                 <div class="form-group">
                </div>
            
              </div>
              <!-- /.box-body -->
              <div class="box-footer">
                 <div class="col-sm-offset-2 col-sm-8">
                    <button type="button" id="editBtn" class="btn btn-primary">保存修改</button>
                    <button type="reset" id="resetBtn" class="btn btn-default">重置</button>
                 </div>
              </div>
              <!-- /.box-footer -->
            </form>        
      </div>
      <!-- /.box -->

    </section>
    <!-- /.content -->
  </div>

  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 2.3.8
    </div>
    <strong>Copyright &copy; 2017-2019 <a href="">凯盛软件</a>.</strong> All rights
    reserved.
  </footer>
</div>

<%@ include file="/WEB-INF/views/include/js.jsp" %>
<script src="/static/js/customer/edit.js"></script>
<script>
	$(function(){
		
		$('#birthday').datepicker({
			format:"yyyy-mm-dd",
	        language:"zh-CN",
	        autoclose: true,
	        todayBtn:'linked',
		    todayHighlight:true,
		});
		
		$("#name").val("${customerObj.id}");
		
		/* $(".option").click(function(){
			var tel = $(this).attr("a");
			alert(tel);
		}); */
		
		
		/* $('#name').change(function(){
			
			alert(tel);
			
		}); */
	});
</script>

</body>
</html>













