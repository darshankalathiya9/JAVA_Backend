<%@page import="java.util.Random"%>
<%@page import="Dao.CartDao"%>
<%@page import="Model.Cart"%>
<%@page import="Dao.WishlistDao"%>
<%@page import="Model.Wishlist"%>
<%@page import="Model.Product"%>
<%@page import="Dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@page import="Model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%	Random randomGenerator = new Random();
	int randomInt = randomGenerator.nextInt(1000000); %>

<!DOCTYPE html>
<html lang="zxx">

<head>
<title>Bootie Ecommerce</title>
<!-- Meta tag Keywords -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8" />
<meta name="keywords"
	content="Bootie Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script>
	addEventListener("load", function() {
		setTimeout(hideURLbar, 0);
	}, false);
	function hideURLbar() {
		window.scrollTo(0, 1);
	}
</script>
<!-- //Meta tag Keywords -->

<!-- Custom-Files -->
<link rel="stylesheet" href="css/bootstrap.css">
<!-- Bootstrap-Core-CSS -->
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<!-- Style-CSS -->
<!-- font-awesome-icons -->
<link href="css/font-awesome.css" rel="stylesheet">
<!-- //font-awesome-icons -->
<!-- /Fonts -->
<link
	href="//fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700"
	rel="stylesheet">
<link
	href="//fonts.googleapis.com/css?family=Source+Sans+Pro:200,200i,300,300i,400,400i,600,600i,700,700i,900"
	rel="stylesheet">
<!-- //Fonts -->

</head>

<body>
<%
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);

  if(session.getAttribute("data")==null)
      response.sendRedirect("Index.jsp");
%>

	<%
	Customer c = null;
	if (session.getAttribute("data") != null) {
		c = (Customer) session.getAttribute("data");
	} else {
		response.sendRedirect("Customer-Login.jsp");
	}
	%>

	<!-- mian-content -->
	<div class="main-banner" id="home">
		<!-- header -->
		<header class="header">
			<div class="container-fluid px-lg-5">
				<!-- nav -->
				<nav class="py-4">
					<div id="logo">
						<h1>
							<a href="Customer-Home.jsp"><span class="fa fa-bold"
								aria-hidden="true"></span>ootie</a>
						</h1>
					</div>

					<label for="drop" class="toggle">Menu</label> <input
						type="checkbox" id="drop" />
					<ul class="menu mt-2">
						<li><a href="Customer-Home.jsp">Home</a></li>
						<li>
							<!-- First Tier Drop Down --> <label for="drop-2" class="toggle">Drop
								Down <span class="fa fa-angle-down" aria-hidden="true"></span>
						</label> <a href="#"><%=c.getName()%><span class="fa fa-angle-down"
								aria-hidden="true"></span></a> <input type="checkbox" id="drop-2" />
							<ul>
								<li><a href="Customer-Profile.jsp">Profile</a></li>
								<li><a href="Customer-Change-Password.jsp">Change
										Password</a></li>
								<li><a href="Customer-Logout.jsp">Logout</a></li>
							</ul>
						</li>
						<li><a href="#">Offer Zone</a></li>
						<li><a href="#">Coupons</a></li>

						<%
						List<Cart> list1 = CartDao.getCartByCustomerID(c.getID());
						%>
						<li class="active"><a href="Customer-Cart.jsp">View Cart (<%=list1.size()%>)
						</a></li>

						<%
						List<Wishlist> list2 = WishlistDao.getWishlistByCustomerID(c.getID());
						%>
						<li><a href="Customer-Wishlist.jsp">Wishlist (<%=list2.size()%>)
						</a></li>
					</ul>
				</nav>
				<!-- //nav -->
			</div>
		</header>
		<!-- //header -->
	</div>
	<!--//main-content-->
	<br><br>
	<div class="container">
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Product Name</th>
					<th scope="col">Product Price</th>
					<th scope="col">Product Quantity</th>
					<th scope="col">Total</th>				
				</tr>
			</thead>
			<tbody>
				<%List<Cart> list = CartDao.getCartByCustomerID(c.getID()); %>
				<%int Net_Price = 0; %>
				<%for(Cart cart1 : list){ %>
				<%Net_Price = Net_Price + cart1.getTotalPrice(); %>
				
					<tr>
						<th scope="row"><%=cart1.getPName()%></th>
						<td><%=cart1.getPPrice() %></td>
						<td>
							<form action="CartController" method="post">
								<input type="hidden" name="CID" value="<%=cart1.getCID()%>">
								<input type="number" min="1" max="10" value="<%=cart1.getPQuantity()%>" name="PQuantity" onchange="this.form.submit();" >
							</form>
						</td>
						<td><%=cart1.getTotalPrice()%></td>
					</tr>
				<%} %>	
			</tbody>
		</table>
		<h2 class="text-center">Net Price : <%=Net_Price%></h2>
	</div><hr><hr>
	<br><br>

	<div class="container">
		<form method="post" action="payment.jsp?amount=<%=Net_Price%>">
		<table border="1">
			<tbody>
				<tr>
					<th class="text-center">S.No</th>
					<th class="text-center">Label</th>
					<th class="text-center">Value</th>
				</tr>
				<tr>
					<td class="text-center">1</td>
					<td class="text-center"><label>txnAmount*</label></td>
					<td class="text-center"><input title="TXN_AMOUNT" tabindex="10" type="text" name="TXN_AMOUNT" value="<%=Net_Price%>"></td>
				</tr>
				<tr>
					<td class="text-center" colspan="3"><input value="CheckOut" type="submit"	onclick=""></td>
				</tr>
			</tbody>
		</table>
		* - Mandatory Fields
	</form>
	</div>
	<br><br>
	<!-- footer -->
	<footer>
        <div class="container">
            <div class="row footer-top">
                <div class="col-lg-4 footer-grid_section_w3layouts">
                    <h2 class="logo-2 mb-lg-4 mb-3">
                        <a href="Index.jsp"><span class="fa fa-bold" aria-hidden="true"></span>ootie</a>
                    </h2>
                    <p> You can Purchase good Qulaity shoes from here & for Seller, Sell Awesome Products to Customer by this E-Commerce Platform.</p>
                    <h4 class="sub-con-fo ad-info my-4">Catch on Social</h4>
                    <ul class="w3layouts_social_list list-unstyled">
                        <li>
                            <a href="#" class="w3pvt_facebook">
                                <span class="fa fa-facebook-f"></span>
                            </a>
                        </li>
                        <li class="mx-2">
                            <a href="#" class="w3pvt_twitter">
                                <span class="fa fa-twitter"></span>
                            </a>
                        </li>
                        <li>
                            <a href="#" class="w3pvt_dribble">
                                <span class="fa fa-dribbble"></span>
                            </a>
                        </li>
                        <li class="ml-2">
                            <a href="#" class="w3pvt_google">
                                <span class="fa fa-google-plus"></span>
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="col-lg-8 footer-right">
                    <div class="w3layouts-news-letter">
                        <h3 class="footer-title text-uppercase text-wh mb-lg-4 mb-3">Newsletter</h3>

                        <p>By subscribing to our mailing list you will always get latest news and updates from us.</p>
                        <form action="#" method="post" class="w3layouts-newsletter">
                            <input type="email" name="Email" placeholder="Enter your email..." required="">
                            <button class="btn1" name="" value=""><span class="fa fa-paper-plane-o" aria-hidden="true"></span></button>
                        </form>
                    </div>
                    <div class="row mt-lg-4 bottom-w3layouts-sec-nav mx-0">
                        <div class="col-md-4 footer-grid_section_w3layouts">
                            <h3 class="footer-title text-uppercase text-wh mb-lg-4 mb-3">Information</h3>
                            <ul class="list-unstyled w3layouts-icons">
                                <li>
                                    <a href="Index.jsp">Home</a>
                                </li>
                                <li class="mt-3">
                                    <a href="#">About Us</a>
                                </li>
                                <li class="mt-3">
                                    <a href="#">Gallery</a>
                                </li>
                                <li class="mt-3">
                                    <a href="#">Services</a>
                                </li>
                                <li class="mt-3">
                                    <a href="#">Contact Us</a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-md-4 footer-grid_section_w3layouts">
                            <!-- social icons -->
                            <div class="agileinfo_social_icons">
                                <h3 class="footer-title text-uppercase text-wh mb-lg-4 mb-3">Customer Service</h3>
                                <ul class="list-unstyled w3layouts-icons">

                                    <li>
                                        <a href="#">About Us</a>
                                    </li>
                                    <li class="mt-3">
                                        <a href="#">Delivery & Returns</a>
                                    </li>
                                    <li class="mt-3">
                                        <a href="#">Warranty</a>
                                    </li>
                                    <li class="mt-3">
                                        <a href="#">Terms & Condition</a>
                                    </li>
                                    <li class="mt-3">
                                        <a href="#">Privacy Policy</a>
                                    </li>
                                </ul>
                            </div>
                            <!-- social icons -->
                        </div>
                        <div class="col-md-4 footer-grid_section_w3layouts my-md-0 my-5">
                            <h3 class="footer-title text-uppercase text-wh mb-lg-4 mb-3">Contact Info</h3>
                            <div class="contact-info">
                                <div class="footer-address-inf">
                                    <h4 class="ad-info mb-2">Phone</h4>
                                    <p>+91 9624360699</p>
                                </div>
                                <div class="footer-address-inf my-4">
                                    <h4 class="ad-info mb-2">Email </h4>
                                    <p><a href="mailto:ecommercebootie9@gmail.com">ecommercebootie9@gmail.com</a></p>
                                </div>
                                <div class="footer-address-inf">
                                    <h4 class="ad-info mb-2">Location</h4>
                                    <p>Somewhere in Earth</p>
                                </div>
                            </div>
                        </div>


                    </div>
                    <div class="cpy-right text-left row">
                        <p class="col-md-10">© 2023 Bootie. All rights reserved | Design by DK </p>
                        <!-- move top icon -->
                        <a href="#home" class="move-top text-right col-md-2"><span class="fa fa-long-arrow-up" aria-hidden="true"></span></a>
                        <!-- //move top icon -->
                    </div>
                </div>
            </div>
        </div>
    </footer>
	<!-- //footer -->

</body>

</html>