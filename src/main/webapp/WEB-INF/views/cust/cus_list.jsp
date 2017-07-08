<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<title>用户customer页面list</title>
 <%@ include file="../include/css.jsp" %>
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
      <div class="box box-solid box-primary">
        <div class="box-header with-border">
          <h3 class="box-title">搜索</h3>
        </div>
        <div class="box-body">
          <form action="/cus/list" id="searchForm" class="form-inline">
             <select name="attr" id="attr" class="form-control">
                <option value="">--请选择查询条件--</option>
                <option value="name">姓名</option>
                <option value="tel">电话</option>
                <option value="idNo">身份证号</option>
                <option value="bankName">开户行</option>
                <option value="bankNo">银行账号</option>
              </select>
            <input type="text" class="form-control" name="value" id="value" placeholder="关键字"/>
            <button type="submit" id="search" class="btn btn-primary">搜索</button>
          </form>
        </div>

      </div>
      <!-- Default box -->
      <div class="box box-solid box-primary">
        <div class="box-header with-border">
         	客户列表
        </div>
        <div class="box-body">
          
          <table class="table" id="cust_table">
            <thead>
              <tr>
                <th>姓名</th>
                <th>电话</th>
                <th>身份证号</th>
                <th>开户行</th>
                <th>银行账号</th>
                <th>地址</th>
                <th>生日</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
            	<c:forEach items="${pageObj.items}" var="cust">
            		 <tr>
							<td>${cust.name}</td>
							<td>${cust.tel}</td>
							<td>${cust.idNo}</td>
							<td>${cust.bankName}</td>
							<td>${cust.bankNo}</td>
							<td>${cust.address}</td>
							<td>${cust.birthday}</td>
							<td>
								<a href="/cust/del">删除</a>
								<a href="/cust/edit?id=${cust.id}">编辑</a>
							</td>
					</tr>
            	
            	
            	</c:forEach>
            
           
          </table>
          <div class="pagination pagination-mini pagination-centered">
            <ul id="pagination" style="margin-bottom:20px;"></ul>
          </div>

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
<!-- ./wrapper -->
<%@ include file="../include/js.jsp" %>
<script src="/static/js/jquery.twbsPagination.min.js"></script>

<script>
  $(function(){
    $("#pagination").twbsPagination({
            totalPages:"${pageObj.pageTotal}",
            visiblePages:5,
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href: '/gift/list?p={{number}}'
        });
    
  });
</script>
</body>
</html>
