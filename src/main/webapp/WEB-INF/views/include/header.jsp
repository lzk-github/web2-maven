<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="icon" href="/static/img/favicon.ico" type="image/x-icon" /> 
<header class="main-header">
	<!-- Logo -->
	<a href="/index.jsp" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-lg">中宏昌盛投资控股集团</span>
	</a>
	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
			role="button"> </a>

		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">

				<!-- User Account: style can be found in dropdown.less -->
				<li class="dropdown user user-menu">
					<a href="#" class="dropdown-toggle"> <span class="hidden-xs">${sessionScope.acc.dept} - ${sessionScope.acc.name }</span>
					</a> 
				</li>
				<li class="dropdown user user-menu">
					<a href="/logout" class="dropdown-toggle"><span class="hidden-xs"><i class="fa fa-sign-out"></i> 退出登录</span>
					</a>
				</li>

			</ul>
		</div>
	</nav>
</header>
