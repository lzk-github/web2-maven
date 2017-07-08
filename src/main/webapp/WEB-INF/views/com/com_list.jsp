<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>查询分公司信息</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<%@include file="/WEB-INF/views/include/css.jsp"%>


</head>
<body class="hold-transition skin-blue sidebar-mini">
	<!-- Site wrapper -->
	<div class="wrapper">

		<%@ include file="/WEB-INF/views/include/header.jsp"%>
		<%@ include file="/WEB-INF/views/include/sider.jsp"%>




		<div class="content-wrapper">

			<!-- Main content -->
			<section class="content">
				<div class="box box-solid box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">搜索</h3>
					</div>
					<div class="box-body">
						<form action="/com/list" class="form-inline">
							<select name="key" id="key" class="form-control">
								<option value="">--请选择查询条件--</option>
								<option value="companyName">公司名称</option>
								<option value="companyTel">电话</option>
								<option value="city">区域</option>
							</select> 
							<input type="text" class="form-control" name="value" id="value" value="${value}" placeholder="关键字" />
							<button class="btn btn-primary">搜索</button>
						</form>
					</div>

				</div>
				<!-- Default box -->
				<div class="box box-solid box-primary">
					<div class="box-header with-border">
						<h5 class="pull-left">查询公司信息</h5>
						<a href="/com/add" class="btn btn-success pull-right">新增公司</a>
					</div>
					<div class="box-body">

						<table class="table" id="cust_table">
							<thead>
							
								<tr>
									<th>公司名称</th>
									<th>电话</th>
									<th>负责人</th>
									<th>区域/城市</th>
									<th>备注</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							
								<c:forEach items="${pageList.items}" var="com">
									
									<tr>
										<td>${com.companyName}</td>
										<td>${com.companyTel}</td>
										<td>${com.controller}</td>
										<td>${com.city }</td>
										<td>${com.remark}</td>
										<td>
											<a class="del" rel="${com.id}" href="javascript:;">删除</a> 
											<a class="edit" href="/com/edit?id=${com.id}">编辑</a>
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
			<strong>Copyright &copy; 2014-2016 <a href="">凯盛软件</a>.
			</strong> All rights reserved.
		</footer>


		<div class="modal fade" id="newModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Modal title</h4>
					</div>
					<div class="modal-body">
						<p>One fine body&hellip;</p>
						<p>One fine body&hellip;</p>
						<p>One fine body&hellip;</p>
						<p>One fine body&hellip;</p>
						<p>One fine body&hellip;</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary">Save
							changes</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->

	</div>

	<%@ include file="/WEB-INF/views/include/js.jsp"%>
	
<script src="/static/js/jquery.twbsPagination.min.js"></script>	
<script src="/static/js/com/list.js"></script>
<script>
	$(function(){
		$("#key").val("${key}");
		
		$('#pagination').twbsPagination({
			totalPages:"${pageList.pageTotal}",
			visiblepages:4,
			first:'首页',
			last:'末页',
			prev:'上一页',
			next:'下一页',
			href:'/com/list?p={{number}}&key=${key}&value=${value}'
		});
	});
	
	</script>
</body>
</html>





