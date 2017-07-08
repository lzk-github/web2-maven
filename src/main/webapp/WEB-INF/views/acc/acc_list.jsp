<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>查询员工信息</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@include file="/WEB-INF/views/include/css.jsp" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

  	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/sider.jsp"%>


  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
  
    <!-- Main content -->
    <section class="content">
      <div class="box box-solid box-primary">
        <div class="box-header with-border">
          <h3 class="box-title">搜索</h3>
        </div>
        <div class="box-body">
          <form action="/acc/list" class="form-inline">
            
             <select name="key" id="key" class="form-control">
                <option value="">--请选择查询条件--</option>
                <option value="name">姓名</option>
                <option value="tel">电话</option>
                <option value="idNo">身份证号</option>
                <option value="company_id">公司</option>
                <option value="dept">部门</option>
              </select>
            <input type="text" class="form-control" name="value" id="value" placeholder="关键字"/>
            <button class="btn btn-primary">搜索</button>
          </form>
        </div>

      </div>
      <!-- Default box -->
      <div class="box box-solid box-primary">
        <div class="box-header with-border">
         <h5 class="pull-left">查询员工信息</h5>
         <a href="/acc/add" class="btn btn-success pull-right">新增员工</a>
        </div>
        <div class="box-body">
          
          <table class="table" id="cust_table">
            <thead>
            <tr>
							<th>姓名</th>
							<th>电话号码</th>
							<th>身份证号</th>
							<th>所属公司</th>
							<th>所属部门</th>
							<th>最后登录时间</th>
							<th>最后登录IP地址</th>
							<th>操作</th>
						</tr>
            </thead>
            <tbody>
            	
            	<c:forEach items="${pageList.items }" var="acc">
            	
           					<tr>
							<td>${acc.name}</td>
							<td>${acc.tel}</td>
							<td>${acc.idNo}</td>
							<td>${acc.company.companyName}</td>
							<td>${acc.dept}</td>
							<td>${fn:substringBefore(acc.lastLoginTime,".0") }</td>
							<td>${acc.lastLoginIp }</td>
							<td>
								<a class="edit" href="/acc/edit?id=${acc.id }">编辑</a>
								<a rel="${acc.id }" class="del" href="javascript:;">删除</a>
							</td>
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
    <strong>Copyright &copy; 2014-2016 <a href="">凯盛软件</a>.</strong> All rights
    reserved.
  </footer>

</div>
<!-- ./wrapper -->
<%@ include file="/WEB-INF/views/include/js.jsp" %>

<!-- 分页插件 -->
<script src="/static/js/jquery.twbsPagination.min.js"></script>
<script src="/static/js/acc/list.js"></script>
<script>
	$(function(){
		
		$("#pagination").twbsPagination({
			 totalPages: "${pageList.pageTotal}",
			 visiblePages:3,
			 href:"/acc/list?page={{number}}&key=${key}&value="+encodeURIComponent("${value}"),
			 first: "首页",
			 prev: "上一页",
			 next:"下一页",
			 last:"末页",
			 
		 });
		
		$("#key").val("${key}");
		$("#value").val("${value}");
		
	});

</script>
</body>
</html>
