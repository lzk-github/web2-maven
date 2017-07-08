<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>员工登录</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@include file="/WEB-INF/views/include/css.jsp" %>
  <style type="text/css">
		body{
			background-image:url(/static/img/body-bg.png);
			padding-top: 120px;
		}
	
	</style>
</head>
<body class="hold-transition skin-blue sidebar-mini  col-sm-offset-4 col-sm-4">

  
    <!-- Main content -->
    <section class="content">
      <div class="box box-solid box-primary">
        <div class="box-header with-border">
         <h3 class="text-center"><span class="logo-lg">中宏昌盛投资控股集团</span></h3>
        </div>
        <div class="box-body">
            <!-- /.box-header -->
            <!-- form start -->
            <form class="form-horizontal" id="loginForm">
              <div class="box-body">
                <div class="form-group">
                  <label class="control-label col-sm-3">用户名：</label>

                  <div class="col-sm-8">
                  	<input type="hidden" name="callback" value="${param.callback}" />
                    <input autofocus type="text" class="form-control" id="userName" name="userName" value="${username}" placeholder="帐号">
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label col-sm-3">密码：</label>

                  <div class="col-sm-8">
                    <input type="password" class="form-control" id="password" name="password" placeholder="密码">
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-offset-3 col-sm-6">
                    <div class="checkbox">
                      <label>
                        <input type="checkbox" checked id="remember" name="remember" value="remember"> 记住此帐号
                      </label>
                    </div>
                  </div>
                </div>
              </div>
              <!-- /.box-body -->
              <div class="box-footer col-sm-offset-3 col-sm-6">
                <button type="button" id="loginBtn" class="btn btn-primary">登录</button>
                <button type="reset" style="margin-left: 20px" class="btn btn-default">重置</button>
              </div>
              <!-- /.box-footer -->
            </form>
        </div>
        </div>
    </section>
  

<%@ include file="/WEB-INF/views/include/js.jsp" %>
<script src="/static/js/acc/login.js"></script>

</body>
</html>

