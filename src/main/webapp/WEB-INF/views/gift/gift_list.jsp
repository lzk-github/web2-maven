<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>查询礼品库存</title>
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
      <div class="box box-solid box-primary">
        <div class="box-header with-border">
          <h3 class="box-title">搜索</h3>
        </div>
        <div class="box-body">
          
        </div>

      </div>
      <!-- Default box -->
      <div class="box box-solid box-primary">
        <div class="box-header with-border">
         <h5 class="pull-left">查询礼品库存</h5>
         <a href="/gift/add" style="margin-right:20px" class="btn btn-success pull-right">新增礼品</a>
        </div>
        <div class="box-body">
          
          <table class="table" id="cust_table">
            <thead>
            <tr>
						  	<th>礼品名称</th>
							<th>市场单价</th>
							<th>所需积分</th>
							<th>入库时间</th>
							<th>所剩库存</th>
							<th>操作</th>
						</tr>
            </thead>
            <tbody>
            
            	<c:forEach items="${pageObj.items}" var="gift">
            	
					<tr>
						<td>${gift.name}</td>
						<td>${fn:substringBefore(gift.price,'.')}</td>
						<td>${gift.points}</td>
						<td>${gift.createTime}</td>
						<td>${gift.nums}</td>
						<td>
							<a href="javascript:;">兑换</a>
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