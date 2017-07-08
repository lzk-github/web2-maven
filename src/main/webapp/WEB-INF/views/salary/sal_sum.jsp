<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>员工薪酬信息</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  
  <%@ include file="/WEB-INF/views/include/css.jsp" %>
  
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

   	<%@ include file="../include/header.jsp"%>
	<%@ include file="../include/sider.jsp"%>
	
  <!-- =============================================== -->

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
  
    <!-- Main content -->
    <section class="content">
      <div class="box box-solid box-primary">
        <div class="box-header with-border">
          <h3 class="box-title">搜索</h3>
        </div>
        <div class="box-body">
          <form action="/sal/sum" class="form-inline">
              <select name="key" id="key" class="form-control">
                <option value="">--请选择查询条件--</option>
                <option value="name">员工姓名</option>
                <option value="tel">电话号码</option>
              </select>
            <input type="text" value="${value}" class="form-control" name="value" id="value" placeholder="关键字"/>
            <button class="btn btn-primary">搜索</button>
          </form>
        </div>

      </div>
      <!-- Default box -->
      <div class="box box-solid box-primary">
        <div class="box-header with-border">
         佣金信息列表
        </div>
        <div class="box-body">
          
          <table class="table" id="">
            <thead>
            	<tr>
					<th>员工编号</th>
					<th>员工电话</th>
					<th>佣金金额</th>
					<th>日期</th>
				</tr>
            </thead>
            <tbody>
            
            <c:forEach items="${pageObj.items}" var="salarysum">
            
					<tr>
						<td>${salarysum.accName}</td>
						<td>${salarysum.accTel}</td>
						<td>${salarysum.sumSalary}</td>
						<td>${salarysum.date}</td>
					</tr>
            </c:forEach>
						
            </tbody>
          </table>
		<ul id="pagination" class="pagination"></ul>

        </div>
        <!-- /.box-body -->
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

<%@ include file="/WEB-INF/views/include/js.jsp"%>
<script src="/static/js/jquery.twbsPagination.min.js"></script>

</body>	
<script>
  $(function(){
	  $("#pagination").twbsPagination({
			 totalPages:"${pageObj.pageTotal}",
			 visiblePages:3,
			 href:"/sal/sum?p={{number}}&key=${key}&value="+encodeURIComponent("${value}"),
			 first: "首页",
			 prev: "上一页",
			 next:"下一页",
			 last:"末页"
		 });  
	  $('#key').val("${requestScope.key}");
  });

</script>
</body>
</html>








