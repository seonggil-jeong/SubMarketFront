<%@ page import="com.submarket.front.dto.SellerDto" %>
<%@ page import="com.submarket.front.util.CmmUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%
	SellerDto sellerInfo = (SellerDto) session.getAttribute("SS_SELLER_INFO");

	if (sellerInfo == null) {
		sellerInfo = new SellerDto();
	}
%>

<!DOCTYPE html>
<html dir="ltr" lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="keywords" content="airbnb, booking, city guide, directory, events, hotel booking, listings, marketing, places, restaurant, restaurant">
	<meta name="description" content="Guido - Directory & Listing HTML Template">
	<meta name="CreativeLayers" content="ATFN">
	<!-- css file -->
	<link rel="stylesheet" href="/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/style.css">
	<link rel="stylesheet" href="/css/dashbord_navitaion.css">
	<!-- Responsive stylesheet -->
	<link rel="stylesheet" href="/css/responsive.css">
	<!-- Title -->
	<title>Guido - Directory & Listing HTML Template</title>
	<!-- Favicon -->
	<link href="/images/favicon.ico" sizes="128x128" rel="shortcut icon" type="image/x-icon" />
	<link href="/images/favicon.ico" sizes="128x128" rel="shortcut icon" />

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
	<script type="text/javascript">
		function doOnload() {
			if ('<%=String.valueOf(session.getAttribute("SS_SELLER_TOKEN"))%>'.length < 10) {
				top.location.href = "/index";
			}
		}
	</script>
</head>
<body onload="doOnload()">
<div class="wrapper">
	<div class="preloader"></div>

	<!-- Main Header Nav -->
	<header class="header-nav menu_style_home_one style2 dashbord menu-fixed main-menu">
		<div class="container-fluid p0">
			<!-- Ace Responsive Menu -->
			<nav>
				<!-- Menu Toggle btn-->
				<div class="menu-toggle">
					<img class="nav_logo_img img-fluid" src="/images/header-logo.svg" alt="header-logo.svg">
					<button type="button" id="menu-btn">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				</div>
				<a href="/index" class="navbar_brand float-left dn-smd">
					<img class="logo1 img-fluid" src="/images/header-logo2.svg" alt="header-logo.svg">
					<img class="logo2 img-fluid" src="/images/header-logo2.svg" alt="header-logo2.svg">
					<span>SubMarket</span>
				</a>
				<!-- Responsive Menu Structure-->
				<!--Note: declare the Menu style in the data-menu-style="horizontal" (options: horizontal, vertical, accordion) -->
				<ul id="respMenu" class="ace-responsive-menu text-right" data-menu-style="horizontal">
					<li class="user_setting" style="margin-bottom: 1%;">
						<div class="dropdown">
							<a class="btn dropdown-toggle" href="#" data-toggle="dropdown"><span class="dn-1200"><%=CmmUtil.nvl(sellerInfo.getSellerName())%><span
									class="fa fa-angle-down"></span></span></a>
							<div class="dropdown-menu">
								<div class="user_set_header">
									<p><%=CmmUtil.nvl(sellerInfo.getSellerName())%><br><span class="address"><%=CmmUtil.nvl(sellerInfo.getSellerEmail())%></span></p>
								</div>
								<div class="user_setting_content" style="margin-bottom: 10%">
									<a class="dropdown-item" href="/logout" style="color: black">Log out</a>
								</div>
							</div>
						</div>
					</li>
					<li class="list-inline-item add_listing"><a href="/seller/main"><span class="icon"></span><span
							class="dn-lg">SELLER HOME</span></a></li>
				</ul>
			</nav>
		</div>
	</header>

	<!-- Main Header Nav For Mobile -->
	<div id="page" class="stylehome1 h0">
		<div class="mobile-menu">
			<div class="header stylehome1">
				<div class="main_logo_home2 text-left">
					<img class="nav_logo_img img-fluid mt15" src="/images/header-logo2.svg" alt="header-logo2.svg">
					<span class="mt15">SubMarket</span>
				</div>
				<ul class="menu_bar_home2">
					<li class="list-inline-item"><a class="custom_search_with_menu_trigger msearch_icon" href="#" data-toggle="modal" data-target="#staticBackdrop"><span class="flaticon-loupe"></span></a></li>
					<li class="list-inline-item"><a class="muser_icon" href="/index"><span class="flaticon-arrow-pointing-to-left"></span></a></li>
					<li class="list-inline-item"><a class="menubar" href="#menu"><span></span></a></li>
				</ul>
			</div>
		</div><!-- /.mobile-menu -->
		<nav id="menu" class="stylehome1">
			<ul>
				<li><a href="/index">Home</a></li>
				<li><a href="/logout"><span class="flaticon-logout"></span> Logout</a></li>
			</ul>
		</nav>
	</div>

	<!-- Search Field Modal -->
	<section class="modal fade search_dropdown" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-hidden="true">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">
				<div class="modal-body">
					<div class="popup_modal_wrapper">
						<div class="container-fluid">
							<div class="row">
								<div class="col-lg-12">
									<a class="close closer" data-dismiss="modal" aria-label="Close" href="#"><span><img src="/images/icons/close.svg" alt=""></span></a>
								</div>
							</div>
						</div>
						<div class="container">
							<div class="row">
								<div class="col-lg-12 mb30">
									<h3>Filter by Category</h3>
								</div>
								<div class="col-sm-6 col-md-4 col-xl-2">
									<div class="icon-box text-center">
										<div class="icon"><span class="flaticon-cutlery"></span></div>
										<div class="content-details">
											<div class="title">Restaurant</div>
										</div>
									</div>
								</div>
								<div class="col-sm-6 col-md-4 col-xl-2">
									<div class="icon-box text-center">
										<div class="icon"><span class="flaticon-shopping-bag"></span></div>
										<div class="content-details">
											<div class="title">Shopping</div>
										</div>
									</div>
								</div>
								<div class="col-sm-6 col-md-4 col-xl-2">
									<div class="icon-box text-center">
										<div class="icon"><span class="flaticon-tent"></span></div>
										<div class="content-details">
											<div class="title">Outdoor Activities</div>
										</div>
									</div>
								</div>
								<div class="col-sm-6 col-md-4 col-xl-2">
									<div class="icon-box text-center">
										<div class="icon"><span class="flaticon-desk-bell"></span></div>
										<div class="content-details">
											<div class="title">Hotels</div>
										</div>
									</div>
								</div>
								<div class="col-sm-6 col-md-4 col-xl-2">
									<div class="icon-box text-center">
										<div class="icon"><span class="flaticon-mirror"></span></div>
										<div class="content-details">
											<div class="title">Beautu & Spa</div>
										</div>
									</div>
								</div>
								<div class="col-sm-6 col-md-4 col-xl-2">
									<div class="icon-box text-center">
										<div class="icon"><span class="flaticon-brake"></span></div>
										<div class="content-details">
											<div class="title">Automotive</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-12 mb15 mt20">
									<h3>Explore Hot Location</h3>
								</div>
								<div class="col-sm-6 col-md-6 col-lg-4 col-xl-3">
									<div class="property_city_home6 tac-xsd">
										<div class="thumb"><img class="w100" src="/images/property/pc18.jpg" alt="pc18.jpg"></div>
										<div class="details">
											<h4>Miami</h4>
											<p>62 Listings</p>
										</div>
									</div>
								</div>
								<div class="col-sm-6 col-md-6 col-lg-4 col-xl-3">
									<div class="property_city_home6 tac-xsd">
										<div class="thumb"><img class="w100" src="/images/property/pc19.jpg" alt="pc19.jpg"></div>
										<div class="details">
											<h4>Roma</h4>
											<p>92 Listings</p>
										</div>
									</div>
								</div>
								<div class="col-sm-6 col-md-6 col-lg-4 col-xl-3">
									<div class="property_city_home6 tac-xsd">
										<div class="thumb"><img class="w100" src="/images/property/pc20.jpg" alt="pc20.jpg"></div>
										<div class="details">
											<h4>New Delhi</h4>
											<p>12 Listings</p>
										</div>
									</div>
								</div>
								<div class="col-sm-6 col-md-6 col-lg-4 col-xl-3">
									<div class="property_city_home6 tac-xsd">
										<div class="thumb"><img class="w100" src="/images/property/pc21.jpg" alt="pc21.jpg"></div>
										<div class="details">
											<h4>London</h4>
											<p>74 Listings</p>
										</div>
									</div>
								</div>
								<div class="col-sm-6 col-md-6 col-lg-4 col-xl-3">
									<div class="property_city_home6 tac-xsd">
										<div class="thumb"><img class="w100" src="/images/property/pc22.jpg" alt="pc22.jpg"></div>
										<div class="details">
											<h4>Amsterdam</h4>
											<p>62 Listings</p>
										</div>
									</div>
								</div>
								<div class="col-sm-6 col-md-6 col-lg-4 col-xl-3">
									<div class="property_city_home6 tac-xsd">
										<div class="thumb"><img class="w100" src="/images/property/pc23.jpg" alt="pc23.jpg"></div>
										<div class="details">
											<h4>Berlin</h4>
											<p>92 Listings</p>
										</div>
									</div>
								</div>
								<div class="col-sm-6 col-md-6 col-lg-4 col-xl-3">
									<div class="property_city_home6 tac-xsd">
										<div class="thumb"><img class="w100" src="/images/property/pc24.jpg" alt="pc24.jpg"></div>
										<div class="details">
											<h4>Paris</h4>
											<p>12 Listings</p>
										</div>
									</div>
								</div>
								<div class="col-sm-6 col-md-6 col-lg-4 col-xl-3">
									<div class="property_city_home6 tac-xsd">
										<div class="thumb"><img class="w100" src="/images/property/pc25.jpg" alt="pc25.jpg"></div>
										<div class="details">
											<h4>New Zealand</h4>
											<p>74 Listings</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- Our Dashbord -->
	<section class="extra-dashboard-menu dn-992">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="ed_menu_list mt5">
						<ul>
							<li><a class="active" href="/seller/main"><span class="flaticon-web-page"></span> Dashboard</a></li>
							<li><a href="/seller/profile"><span class="flaticon-avatar"></span>Profile</a></li>
							<li><a href="/seller/my-item"><span class="flaticon-list"></span>My Item List</a></li>
							<li><a href="/seller/item"><span class="flaticon-edit"></span>Add Item</a></li>
							<li><a href="/logout"><span class="flaticon-logout"></span> Logout</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- Our Dashbord -->
	<section class="our-dashbord dashbord bgc-f4">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="dashboard_navigationbar dn db-992">
						<div class="dropdown">
							<button onclick="myFunction()" class="dropbtn"><i class="fa fa-bars pr10"></i> Dashboard Navigation</button>
							<ul id="myDropdown" class="dropdown-content">
								<li class="active"><a href="/seller/main"><span class="flaticon-web-page"></span> Dashboard</a></li>
								<li><a href="/seller/profile"><span class="flaticon-avatar"></span>Profile</a></li>
								<li><a href="/seller/my-item"><span class="flaticon-list"></span>My Item List</a></li>
								<li><a href="/seller/item"><span class="flaticon-edit"></span>Add Item</a></li>
								<li><a href="/logout"><span class="flaticon-logout"></span>Logout</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-12 mb10">
					<div class="breadcrumb_content style2">
						<h2 class="breadcrumb_title float-left">Hello, Cameron!</h2>
						<p class="float-right">Ready to jump back in!</p>
					</div>
				</div>
				<div class="col-sm-6 col-md-6 col-lg-6 col-xl-3">
					<div class="ff_one">
						<div class="icon"><span class="flaticon-list"></span></div>
						<div class="detais">
							<div class="timer">22</div>
							<p>Active Listing</p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-6 col-lg-6 col-xl-3">
					<div class="ff_one style2">
						<div class="icon"><span class="flaticon-note"></span></div>
						<div class="detais">
							<div class="timer">9382</div>
							<p>Total Reviews</p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-6 col-lg-6 col-xl-3">
					<div class="ff_one style3">
						<div class="icon"><span class="flaticon-chat"></span></div>
						<div class="detais">
							<div class="timer">74</div>
							<p>Messages</p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-6 col-lg-6 col-xl-3">
					<div class="ff_one style4">
						<div class="icon"><span class="flaticon-love"></span></div>
						<div class="detais">
							<div class="timer">32</div>
							<p>Bookmarks</p>
						</div>
					</div>
				</div>
				<div class="col-lg-7 col-xl-8">
					<div class="application_statics">
						<h4>Total Views</h4>
						<div class="c_container"></div>
					</div>
				</div>
				<div class="col-lg-5 col-xl-4">
					<div class="recent_job_activity">
						<h4 class="title">Recent Activities</h4>
						<div class="grid style1">
							<ul>
								<li class="list-inline-item"><div class="icon"><span class="fa fa-check"></span></div></li>
								<li class="list-inline-item"><p>Your listing <span>Hotel Gulshan</span> has been approved!.</p></li>
							</ul>
						</div>
						<div class="grid style2">
							<ul>
								<li class="list-inline-item"><div class="icon"><span class="fa fa-check"></span></div></li>
								<li class="list-inline-item"><p>Your listing <span>Burger House</span> has been approved!.</p></li>
							</ul>
						</div>
						<div class="grid style3">
							<ul>
								<li class="list-inline-item"><div class="icon"><span class="flaticon-note"></span></div></li>
								<li class="list-inline-item"><p>Pitter Parker left a review 3.4 on John's <span>John's Coffee Shop</span></p></li>
							</ul>
						</div>
						<div class="grid style4">
							<ul>
								<li class="list-inline-item"><div class="icon"><span class="flaticon-love"></span></div></li>
								<li class="list-inline-item"><p>Someone bookmarked your <span>Burger House</span> listing!</p></li>
							</ul>
						</div>
						<div class="grid style5">
							<ul>
								<li class="list-inline-item"><div class="icon"><span class="fa fa-check"></span></div></li>
								<li class="list-inline-item"><p><span>Your listing Holiday Home has been approved!</span></p></li>
							</ul>
						</div>
						<div class="grid style6 mb0">
							<ul class="pb0 mb0 bb_none">
								<li class="list-inline-item"><div class="icon"><span class="flaticon-love"></span></div></li>
								<li class="list-inline-item"><p><span>Someone bookmarked your Moonlight Hotel listing!</span></p></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- Our Footer -->
	<a class="scrollToHome" href="#"><i class="fa fa-angle-up"></i></a>
</div>
<!-- Wrapper End -->
<script src="/js/jquery-3.6.0.js"></script>
<script src="/js/jquery-migrate-3.0.0.min.js"></script>
<script src="/js/popper.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/jquery.mmenu.all.js"></script>
<script src="/js/ace-responsive-menu.js"></script>
<script src="/js/chart.min.js"></script>
<script src="/js/chart-custome.js"></script>
<script src="/js/bootstrap-select.min.js"></script>
<script src="/js/snackbar.min.js"></script>
<script src="/js/simplebar.js"></script>
<script src="/js/parallax.js"></script>
<script src="/js/scrollto.js"></script>
<script src="/js/jquery-scrolltofixed-min.js"></script>
<script src="/js/jquery.counterup.js"></script>
<script src="/js/wow.min.js"></script>
<script src="/js/progressbar.js"></script>
<script src="/js/slider.js"></script>
<script src="/js/timepicker.js"></script>
<script src="/js/wow.min.js"></script>
<script src="/js/dashboard-script.js"></script>
<!-- Custom script for all pages -->
<script src="/js/script.js"></script>
</body>
</html>