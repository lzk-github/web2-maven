<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>修改公司</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  
<%@include file="../include/css.jsp" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

	<%@ include file="../include/header.jsp"%>
	<%@ include file="../include/sider.jsp"%>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Main content -->
    <section class="content">

      <!-- Default box -->
              
      <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">修改公司信息</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form class="form-horizontal" id="editForm">
              <div class="box-body">
                <div class="form-group">
                  <label class="col-sm-2 control-label">公司名称：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input disabled type="text" class="form-control" value="${com.companyName }" placeholder="">
                  </div>
                  <input type="hidden" id="companyName" name="companyName" value="${com.companyName }" />
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">电话：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" value="${com.companyTel }" class="form-control" id="companyTel" name="companyTel" placeholder="">
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="col-sm-2 control-label">所属地域：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                  		
                       <select name="city" id="city" class="form-control">
                       		<option value="">--请选择城市--</option>
                          	<c:forEach items="${requestScope.cityList}" var="city">
                  				<option value="${city}">${city}</option>
                  			</c:forEach>
                        </select>
                  </div>
                </div>
                 <div class="form-group">
                  <label class="col-sm-2 control-label">负责人：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" value="${com.controller}" class="form-control" id="controller" name="controller" placeholder="">
                  </div>
                </div>
                
                 <div class="form-group">
                  <label class="col-sm-2 control-label">备注：</label>

                  <div class="col-sm-8">
                    <textarea id="remark" name="remark" class="form-control" rows="6">${com.remark }</textarea>
                  </div>
                </div>
              </div>
              <!-- /.box-body -->
              <div class="box-footer">
                 <div class="col-sm-offset-2 col-sm-8">
                    <button type="button" id="editBtn"  class="btn btn-primary">保存修改</button>
                    <button type="reset" id="reset" class="btn btn-default">重置</button>
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
<%@ include file="../include/js.jsp" %>
<script src="/static/js/com/edit.js"></script>
<script>
$(function(){
	$("#city").val("${com.city}");
});
</script>
</body>
</html>
