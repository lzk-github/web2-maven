<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>已付息查询</title>
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
          <form action="/interest/list" class="form-inline">
            <select name="key" id="key" class="form-control">
              <option value="name">客户姓名</option>
              <option value="tel">电话</option>
              
              </select>
            <input type="text" class="form-control" value="${value}" name="value" id="value" placeholder="关键字"/>
            <button class="btn btn-primary">搜索</button>
          </form>
        </div>

      </div>
      <!-- Default box -->
      <div class="box box-solid box-primary">
        <div class="box-header with-border">
         <h5 class="pull-left">投资信息列表</h5>
        </div>
        <div class="box-body">
          
          <table class="table" id="cust_table">
            <thead>
            <tr>
							<th>客户名称</th>
							<th>客户电话</th>
							<th>投资金额</th>
							<th>利息金额</th>
							<th>结息日</th>
							<th>状态</th>
							<th>领取日期</th>
							<th>操作</th>
						</tr>
            </thead>
            <tbody>
            	<c:forEach items="${pageObj.items}" var="interestVO">
            	
						<tr>
							<td>${interestVO.custName}</td>
							<td>${interestVO.tel }</td>
							<td>${interestVO.investMoney }</td>
							<td>${interestVO.interestMoney }</td>
							<td>${interestVO.interestsendday }</td>
							
							<c:choose>
								<c:when test="${interestVO.state == 1}">
									<td>已领取</td>
								</c:when>
								<c:when test="${interestVO.state == 2}">
									<td>未到期</td>
								</c:when>
								<c:otherwise>
									<td>未领取</td>
								</c:otherwise>
							</c:choose>
							
							
							<td>${interestVO.sendtime }</td>
							
							<td>
								
								<a href="editemployee.html">
									<c:choose>
										<c:when test="${interestVO.state==3}">点击领取</c:when>
										<c:otherwise>--</c:otherwise>
									</c:choose>
								</a>
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

	$("#key").val("${key}");
	$("#pagination").twbsPagination({
		 totalPages:"${pageObj.pageTotal}",
		 visiblePages:6,
		 href:"/interest/list?page={{number}}&key=${key}&value=${value}",
		 first: "首页",
		 prev: "上一页",
		 next:"下一页",
		 last:"末页"
	});  


</script>


</body>
</html>











