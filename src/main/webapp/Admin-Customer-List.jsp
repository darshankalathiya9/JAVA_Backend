<%@page import="Dao.CustomerDao"%>
<%@page import="Model.Customer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
<head>
<title>Bootie Ecommerce</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Modern Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<!-- Bootstrap Core CSS -->
<link href="Admin/css/bootstrap.min.css" rel='stylesheet'
	type='text/css' />
<!-- Custom CSS -->
<link href="Admin/css/style.css" rel='stylesheet' type='text/css' />
<link href="Admin/css/font-awesome.css" rel="stylesheet">
<!-- jQuery -->
<script src="Admin/js/jquery.min.js"></script>
<!----webfonts--->
<link
	href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900'
	rel='stylesheet' type='text/css'>
<!---//webfonts--->
<!-- Bootstrap Core JavaScript -->
<script src="Admin/js/bootstrap.min.js"></script>
</head>
<body>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="top1 navbar navbar-default navbar-static-top"
			role="navigation" style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="Admin-Home.jsp">Modern</a>
			</div>

			<!-- /.navbar-header -->

			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" value="Search..."
					onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = 'Search...';}">
			</form>
			<ul class="nav navbar-nav navbar-right">
				<%
				String Email = (String) request.getAttribute("Email");
				%>
				<li class="dropdown"><a href="Admin-Home.jsp"
					class="dropdown-toggle" data-toggle="dropdown"
					aria-expanded="false"><i class="fa fa-id-badge"></i><span
						class="badge">
							<%
							out.print(Email);
							%>
					</span></a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" aria-expanded="false"><i
						class="fa fa-comments-o"></i><span class="badge">4</span></a></li>
			</ul>
			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li><a href="Admin-Home.jsp"><i
								class="fa fa-home fa-fw nav_icon"></i>Dashboard</a></li>
						<li><a href="Admin-Seller-List.jsp"><i
								class="fa fa-users nav_icon"></i>Sellers</a></li>
						<li><a href="Admin-Customer-List.jsp"><i
								class="fa fa-users nav_icon"></i>Customers</a></li>
						<li><a href="Admin-Product-List.jsp"><i
								class="fa fa-shopping-cart nav_icon"></i>Products</a></li>
						<li><a href="#"><i class="fa fa-envelope nav_icon"></i>Mailbox</a>
						</li>
						<li><a href="Admin-Total-Payment-List.jsp"><i
								class="fa fa-inr nav_icon"></i>Payments</a></li>
						<li><a href="Admin-Logout.jsp"><i
								class="fa fa-power-off nav_icon"></i>Logout</a></li>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>


		<div id="page-wrapper">
			<div class="col-md-12 graphs">
				<div class="xs">

					<div class="panel panel-warning"
						data-widget="{&quot;draggable&quot;: &quot;false&quot;}"
						data-widget-static="">
						<div class="panel-heading">
							<h2 class = "text-center">Customers Table</h2>
							<div class="panel-ctrls" data-actions-container=""
								data-action-collapse="{&quot;target&quot;: &quot;.panel-body&quot;}">
								<span class="button-icon has-bg"><i
									class="ti ti-angle-down"></i></span>
							</div>
						</div>
						<div class="panel-body no-padding" style="display: block;">
							<table class="table table-striped">
								<thead>
									<tr class="warning">
										<th>ID</th>
										<th>Name</th>
										<th>Contact</th>
										<th>Address</th>
										<th>Email</th>
										<th>Edit</th>
										<th>Delete</th>
									</tr>
								</thead>
								<tbody>
								
								<% List<Customer> list = CustomerDao.getAllCustomers(); %>
								<% for(Customer c : list){ %>
								
									<tr>
										<td><%=c.getID() %></td>
										<td><%=c.getName() %></td>
										<td><%=c.getContact() %></td>
										<td><%=c.getAddress() %></td>
										<td><%=c.getEmail() %></td>
										<td><a href ="Admin-Customer-Edit.jsp?ID=<%=c.getID()%>">Edit</a></td>
										<td><a href ="Admin-Customer-Delete.jsp?ID=<%=c.getID()%>">Delete</a></td>
									</tr>
									
								<%} %>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="copy_layout">
					<p>© 2023 Bootie Ecommerce | All Rights Reserved | Design by DK
					</p>
				</div>
			</div>
		</div>



		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
	<!-- Nav CSS -->
	<link href="Admin/css/custom.css" rel="stylesheet">
	<!-- Metis Menu Plugin JavaScript -->
	<script src="Admin/js/metisMenu.min.js"></script>
	<script src="Admin/js/custom.js"></script>
</body>
</html>

