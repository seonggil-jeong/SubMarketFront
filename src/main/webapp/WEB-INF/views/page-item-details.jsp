<%@ page import="com.submarket.front.dto.ItemDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.submarket.front.dto.ItemReviewDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.submarket.front.util.CmmUtil" %>
<%@ page import="com.submarket.front.dto.UserDto" %>
<%@ page import="com.submarket.front.dto.SellerDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    UserDto userInfo = (UserDto) session.getAttribute("SS_USER_INFO");
    SellerDto sellerInfo = (SellerDto) session.getAttribute("SS_SELLER_INFO");

    ItemDto itemDto = new ItemDto();
    List<ItemReviewDto> itemReviewDtoList = new ArrayList<>();
    itemDto = (ItemDto) request.getAttribute("itemDto");
    itemReviewDtoList = (List<ItemReviewDto>) request.getAttribute("itemReviewDtoList");

    List<ItemDto> itemDtoList = (List<ItemDto>) request.getAttribute("itemDtoList");
    int itemStar = 0;
    int i = 0;

    if (itemReviewDtoList.size() > 0){
    for (i = 0; i < itemReviewDtoList.size(); i++) {
        itemStar += itemReviewDtoList.get(i).getReviewStar();
    }
    itemStar = itemStar / i;
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
</head>
<body>
<div class="wrapper">
    <div class="preloader"></div>

    <!-- Main Header Nav -->
    <header class="header-nav menu_style_home_one style2 navbar-scrolltofixed stricky main-menu">
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
                    <%
                        if (session.getAttribute("SS_USER_TOKEN") != null) {

                    %>
                    <li class="user_setting" style="margin-bottom: 1%;">
                        <div class="dropdown">
                            <a class="btn dropdown-toggle" href="#" data-toggle="dropdown"><span class="dn-1200"><%=CmmUtil.nvl(userInfo.getUserName())%><span
                                    class="fa fa-angle-down"></span></span></a>
                            <div class="dropdown-menu">
                                <div class="user_set_header">
                                    <p><%=CmmUtil.nvl(userInfo.getUserName())%><br><span class="address"><%=CmmUtil.nvl(userInfo.getUserEmail())%></span></p>
                                </div>
                                <div class="user_setting_content" style="margin-bottom: 10%">
                                    <a class="dropdown-item active" href="/user/profile" style="color: black">내 정보</a>
                                    <a class="dropdown-item" href="/user/sublist" style="color: black">내 구독 정보</a>
                                    <a class="dropdown-item" href="/logout" style="color: black">Log out</a>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li class="list-inline-item add_listing"><a href="/user/profile"><span class="icon"></span><span
                            class="dn-lg">My Info</span></a></li>
                    <%
                    } else if (session.getAttribute("SS_SELLER_TOKEN") != null) {
                    %>
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
                    <%
                    } else {
                    %>
                    <li class="list-inline-item list_s"><a href="#" class="btn flaticon-avatar" data-toggle="modal" data-target=".bd-example-modal-lg"> <span class="dn-1200">Login/Sign Up</span></a></li>
                    <%
                        }
                    %>
                </ul>
            </nav>
        </div>
    </header>
    <!-- Modal -->
    <div class="sign_up_modal modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-md mt100" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body container pb20 pl0 pr0 pt0">
                    <div class="row">
                        <div class="col-lg-12">
                            <ul class="sign_up_tab nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">USER</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">SELLER</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="tab-content container" id="myTabContent">
                        <div class="row mt40 tab-pane fade show active pl20 pr20" id="home" role="tabpanel" aria-labelledby="home-tab">
                            <div class="col-lg-12">
                                <div class="login_form">
                                    <p class="nav-link active" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">User Login</p>
                                    <%-- 사용자 로그인 창 --%>
                                    <form action="/user/login" method="post">
                                        <div class="input-group mb-2 mr-sm-2">
                                            <input type="text" class="form-control" id="userId" name="userId" placeholder="User Id">
                                        </div>
                                        <div class="input-group form-group mb5">
                                            <input type="password" class="form-control" id="userPassword" name="userPassword" placeholder="Password">
                                        </div>
                                        <a class="btn-fpswd float-right" href="#" style="margin-left: 5%">Forgot password?</a>
                                        <a class="btn-fpswd float-right" href="#">Forgot id?</a>
                                        <button type="submit" class="btn btn-log btn-block btn-thm">Sign in</button>
                                        <p class="text-center mb30 mt20">Don't have an account? <a class="text-thm" href="/regForm">Sign up</a></p>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="row mt40 tab-pane fade pl20 pr20" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                            <div class="col-lg-12">
                                <div class="login_form">
                                    <p class="nav-link active" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Seller Login</p>
                                    <div class="tab-content" id="pills-tabContent">
                                        <%-- 사업자 로그인 창 --%>
                                        <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
                                            <form action="/seller/login", method="post">
                                                <div class="input-group mb-2 mr-sm-2">
                                                    <input type="text" class="form-control" id="SellerId" placeholder="Seller Id" name="sellerId">
                                                </div>
                                                <div class="input-group form-group mb5">
                                                    <input type="password" class="form-control" id="SellerPassword" placeholder="Seller Password" name="sellerPassword">
                                                </div>
                                                <a class="btn-fpswd float-right" href="#" style="margin-left: 5%">Forgot password?</a>
                                                <a class="btn-fpswd float-right" href="#">Forgot id?</a>
                                                <button type="submit" class="btn btn-log btn-block btn-thm">Sign in</button>
                                                <p class="text-center mb30 mt20">Don't have an account? <a class="text-thm" href="/regForm">Sign up</a></p>
                                            </form>
                                        </div>
                                        <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
                                            <form action="#">
                                                <div class="form-group input-group">
                                                    <input type="email" class="form-control" id="exampleInputEmail3" placeholder="Email">
                                                </div>
                                                <div class="form-group input-group">
                                                    <input type="text" class="form-control" id="exampleInputName2" placeholder="Username" required>
                                                </div>
                                                <div class="form-group input-group mb20">
                                                    <input type="password" class="form-control" id="exampleInputPassword3" placeholder="Password" required>
                                                </div>
                                                <button type="submit" class="btn btn-log btn-block btn-thm">Sign Up</button>
                                                <hr>
                                                <div class="row">
                                                    <div class="col-lg-6">
                                                        <button type="submit" class="btn btn-block btn-fb"><i class="fa fa-facebook float-left mt5"></i> Log In via Facebook</button>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <button type="submit" class="btn btn-block btn-googl"><i class="fa fa-google float-left mt5"></i> Log In via Google+</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

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
                    <li class="list-inline-item"><a class="muser_icon" href="page-register.html"><span class="flaticon-avatar"></span></a></li>
                    <li class="list-inline-item"><a class="menubar" href="#menu"><span></span></a></li>
                </ul>
            </div>
        </div><!-- /.mobile-menu -->
        <nav id="menu" class="stylehome1">
            <ul>
                <li><span>Home</span>
                    <ul>
                        <li><a href="/index">Home V1</a></li>
                        <li><a href="index2.html">Home V2</a></li>
                        <li><a href="index3.html">Home V3</a></li>
                    </ul>
                </li>
                <li><span>Explore</span>
                    <ul>
                        <li><a href="page-author-single.html">Author Single</a></li>
                        <li><a href="page-city-single.html">City Single</a></li>
                        <li><a href="page-add-new-listing.html">New Listing</a></li>
                    </ul>
                </li>
                <li><span>Listing</span>
                    <ul>
                        <li><span>Listing Styles</span>
                            <ul>
                                <li><a href="page-listing-v1.html">Listing v1</a></li>
                                <li><a href="page-listing-v2.html">Listing v2</a></li>
                                <li><a href="page-listing-v3.html">Listing v3</a></li>
                                <li><a href="page-listing-v4.html">Listing v4</a></li>
                                <li><a href="page-listing-v5.html">Listing v5</a></li>
                            </ul>
                        </li>
                        <li><span>Listing Map</span>
                            <ul>
                                <li><a href="page-listing-v6.html">Map v1</a></li>
                                <li><a href="page-listing-v7.html">Map v2</a></li>
                                <li><a href="page-listing-v8.html">Map v3</a></li>
                                <li><a href="page-listing-v9.html">Map v4</a></li>
                            </ul>
                        </li>
                        <li><span>Listing Single</span>
                            <ul>
                                <li><a href="page-listing-single-v1.html">Single V1</a></li>
                                <li><a href="page-listing-single-v2.html">Single V2</a></li>
                                <li><a href="page-listing-single-v3.html">Single V3</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li><span>Blog</span>
                    <ul>
                        <li><a href="page-blog-grid.html">Blog Grid</a></li>
                        <li><a href="page-blog-grid-sidebar.html">Blog Grid Sidebar</a></li>
                        <li><a href="page-blog-details.html">Blog Details</a></li>
                        <li><a href="page-blog-list.html">Blog List</a></li>
                        <li><a href="page-blog-single.html">Blog Single</a></li>
                    </ul>
                </li>
                <li><span>Pages</span>
                    <ul>
                        <li><span>Shop</span>
                            <ul>
                                <li><a href="page-shop.html">Shop Pages</a></li>
                                <li><a href="page-shop-single.html">Shop Single</a></li>
                                <li><a href="page-shop-cart.html">Cart</a></li>
                                <li><a href="page-shop-checkout.html">Checkout</a></li>
                                <li><a href="page-shop-order.html">Order</a></li>
                            </ul>
                        </li>
                        <li><a href="page-about.html">About Us</a></li>
                        <li><a href="page-contact.html">Contact</a></li>
                        <li><a href="page-coming-soon.html">Coming Soon</a></li>
                        <li><span>User Detils</span>
                            <ul>
                                <li><a href="page-my-dashboard.html">Dashboard</a></li>
                                <li><a href="page-profile.html">My Profile</a></li>
                                <li><a href="page-my-listing.html">My Listings</a></li>
                                <li><a href="page-my-bookmark.html">Bookmarks</a></li>
                                <li><a href="page-message.html">Messages</a></li>
                                <li><a href="page-my-review.html">Reviews</a></li>
                                <li><a href="page-add-new-listing.html">Add New Property</a></li>
                            </ul>
                        </li>
                        <li><a href="page-gallery.html">Gallery</a></li>
                        <li><a href="page-faq.html">Faq</a></li>
                        <li><a href="page-invoice.html">Invoice</a></li>
                        <li><a href="page-login.html">LogIn</a></li>
                        <li><a href="page-pricing.html">Pricing V1</a></li>
                        <li><a href="page-pricing2.html">Pricing V2</a></li>
                        <li><a href="page-register.html">Register</a></li>
                        <li><a href="page-error.html">404 Page</a></li>
                        <li><a href="page-terms.html">Terms and Conditions</a></li>
                        <li><a href="page-ui-element.html">UI Elements</a></li>
                    </ul>
                </li>
                <li><a href="page-contact.html">Contact</a></li>
                <li><a href="page-login.html"><span class="flaticon-avatar"></span> Login</a></li>
                <li><a href="page-register.html"><span class="flaticon-edit"></span> Register</a></li>
                <li class="cl_btn"><a class="btn btn-block btn-lg btn-thm rounded" href="#"><span class="icon">+</span> Create Listing</a></li>
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

    <!-- Listing Single v5 Home Area -->
    <div class="home10-mainslider">
        <div class="container-fluid p0">
            <div class="row">
                <div class="col-lg-12">
                    <div class="main-banner-wrapper home10">
                        <div class="banner-style-one owl-theme owl-carousel">
                            <div class="slide slide-one" style="background-image: url(<%=itemDto.getMainImagePath()%>);height: 650px;"></div>
                            <%
                                if (itemDto.getSubImagePath() != null) {
                            %>
                            <div class="slide slide-one" style="background-image: url(<%=itemDto.getSubImagePath()%>);height: 650px;"></div>
                            <%
                                }
                            %>
                        </div>
                        <div class="carousel-btn-block banner-carousel-btn">
                            <span class="carousel-btn left-btn"><i class="flaticon-arrow-pointing-to-left left"></i></span>
                            <span class="carousel-btn right-btn"><i class="flaticon-arrow-pointing-to-right right"></i></span>
                        </div><!-- /.carousel-btn-block banner-carousel-btn -->
                    </div><!-- /.main-banner-wrapper -->
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row listing_single_row style2">
                <div class="col-lg-8 col-xl-7">
                    <div class="single_property_title listing_single_v1">
                        <div class="media">
                            <div class="media-body mt20">
                                <h2 class="mt-0"><%=itemDto.getItemTitle()%></h2>
                                <ul class="mb40 agency_profile_contact listing_single_v1">
                                    <li class="list-inline-item"><a href="#"><span class="flaticon-phone"></span> +61-8181-123</a></li>
                                    <li class="list-inline-item"><a href="#"><span class="flaticon-pin"></span> New York</a></li>
                                </ul>
                                <div class="sspd_review listing_single_v1">
                                    <ul class="mb0">
                                        <%
                                            for (int j = 0; j < itemStar; j++) {
                                        %>
                                        <li class="list-inline-item"><a href="#"><i class="fa fa-star"></i></a></li>
                                        <%
                                            }
                                        %>
                                        <li class="list-inline-item text-white">(<%=CmmUtil.nvl(String.valueOf(itemReviewDtoList.size()))%> reviews)</li>
                                        <li class="list-inline-item ml20"><a class="price_range" href="#"><%=itemDto.getItemCount()%>개 남음</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-xl-5">
                    <div class="single_property_social_share listing_single_v1 mt80 mt0-lg">
                        <div class="price listing_single_v1 mt25 float-right fn-lg">
                            <a href="/user/sub/<%=itemDto.getSellerId()%>/<%=itemDto.getItemSeq()%>" class="btn btn-thm spr_btn">Submit Item</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Listing Single V1 -->
    <section class="our-agent-single pb30-991">
        <div class="container">
            <div class="row">
                <div class="col-md-12 col-lg-8">
                    <div class="row">
                        <div class="col-lg-12 pl0 pr0 pl15-767 pr15-767">
                            <div class="listing_single_description mb60">
                                <h4 class="mb30">Overview</h4>
                                <%
                                    if (itemDto.getItemContents().length() > 100) {
                                %>
                                <p class="first-para mb25"><%=itemDto.getItemContents().substring(0, 100)%></p>
                                <p class="gpara second_para white_goverlay mt10 mb20"><%=itemDto.getItemContents().substring(100, 150)%>...</p>
                                <div class="collapse" id="collapseExample">
                                    <div class="card card-body">
                                        <p class="mt10 mb10"><%=itemDto.getItemContents().substring(100)%></p>
                                    </div>
                                </div>
                                <%
                                    } else {
                                %>
                                <p class="first-para mb25"><%=itemDto.getItemContents()%></p>
                                <%
                                    }
                                %>
                                <p class="overlay_close">
                                    <a class="text-thm fz15 tdu" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">Show More</a>
                                </p>
                            </div>
                        </div>
                        <div class="col-lg-12 pl0 pr0 pl15-767 pr15-767">
                            <div class="listing_single_description mb60">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <h4 class="mb30">Photo gallery</h4>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-6 col-md-6 col-lg-3">
                                        <div class="gallery_item">
                                            <img class="img-fluid img-circle-rounded w100" src="<%=itemDto.getMainImagePath()%>" alt="lgs1.jpg">
                                            <div class="gallery_overlay style2"><a class="icon popup-img" href="<%=itemDto.getMainImagePath()%>"><span class="flaticon-zoom"></span></a></div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6 col-md-6 col-lg-3">
                                        <div class="gallery_item">
                                    <%
                                        if (! itemDto.getSubImagePath().equals("/")) {
                                    %>
                                            <img class="img-fluid img-circle-rounded w100" src="<%=itemDto.getSubImagePath()%>" alt="lgs2.jpg">
                                            <div class="gallery_overlay style2"><a class="icon popup-img" href="<%=itemDto.getSubImagePath()%>"><span class="flaticon-zoom"></span></a></div>
                                    <%
                                        } else
                                    %>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12 pl0 pl15-767">
                            <div class="custom_reivews mt30 mb30 row">
                                <div class="col-lg-12">
                                    <h4 class="mb25">(<%=itemReviewDtoList.size()%> reviews)</h4>
                                </div>
                                <div class="col-lg-2">
                                    <div class="title">Overall Rating</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12 pl0 pl15-767">
                            <div class="listing_single_reviews">
                                <div class="product_single_content mb30">
                                    <%
                                        for (ItemReviewDto itemReviewDto : itemReviewDtoList) {
                                    %>
                                    <div class="mbp_first media">
<%--                                        <img src="/images/blog/reviewer2.png" class="mr-3" alt="reviewer2.png"> 리뷰 이미지--%>
                                        <div class="media-body">
                                            <h4 class="sub_title mt-0"><%=itemReviewDto.getUserId()%></h4>
                                            <div class="sspd_postdate fz14 mb20"><%=itemReviewDto.getReviewDate()%>
                                                <div class="sspd_review pull-right">
                                                    <ul class="mb0 pl15">
                                                        <%
                                                            for (int j = 0; j < itemReviewDto.getReviewStar(); j++) {
                                                        %>
                                                        <li class="list-inline-item"><a href="#"><i class="fa fa-star"></i></a></li>
                                                        <%
                                                            }
                                                        %>
                                                        <li class="list-inline-item">(<%=itemReviewDto.getReviewStar()%>)</li>
                                                    </ul>
                                                </div>
                                            </div>
                                            <p class="fz14 mt10"><%=itemReviewDto.getReviewContents()%></p>
                                        </div>
                                    </div>
                                    <%
                                        }
                                    %>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12 pl0 pl15-767">
                            <div class="single_page_review_form p30-lg mb30-991">
                                <div class="wrapper">
                                    <form action="/user/review/<%=itemDto.getItemSeq()%>/create", method="post">
                                    <h4>Add a Review</h4>
                                    <div class="custom_reivews row mt40 mb30">
                                        <div class="col-lg-2 pr0">
                                            <div class="my_profile_setting_input ui_kit_select_search form-group">
                                                <label>reviewStar</label>
                                                <select class="selectpicker" data-width="100%" name="reviewStar">
                                                    <option data-tokens="Category1" value="1" selected>1</option>
                                                    <option data-tokens="Category2" value="2">2</option>
                                                    <option data-tokens="Category3" value="3">3</option>
                                                    <option data-tokens="Category4" value="4">4</option>
                                                    <option data-tokens="Category4" value="5">5</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                        <div class="form-group">
                                            <textarea class="form-control" rows="7" placeholder="리뷰 내용" name="reviewContents" required></textarea>
                                        </div>
                                        <button type="submit" class="btn btn-thm">Submit Review</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-xl-4">
                    <div class="listing_single_sidebar">
                        <div class="sidebar_pricing_widget">
                            <h4 class="title mb20">Price Range</h4>
                            <ul class="mb0">
                                <li><a href="#">Price: <span class="float-right heading-color"><%=itemDto.getItemPrice()%> 원</span></a></li>
                            </ul>
                        </div>
                        <div class="sidebar_author_widget">
                            <h4 class="title mb25">Author</h4>
                            <div class="media">
                                <img class="mr-3" src="/images/team/author.png" alt="author.png">
                                <div class="media-body">
                                    <h5 class="mt15 mb0">Robert Fox</h5>
                                    <p class="mb0">Designer at guido</p>
                                </div>
                            </div>
                        </div>
                        <div class="sidebar_contact_business_widget">
                            <h4 class="title mb25">Contact Business</h4>
                            <ul class="business_contact mb0">
                                <li class="search_area">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="exampleInputName1" placeholder="Name">
                                    </div>
                                </li>
                                <li class="search_area">
                                    <div class="form-group">
                                        <input type="email" class="form-control" id="exampleInputEmail" placeholder="Email">
                                    </div>
                                </li>
                                <li class="search_area">
                                    <div class="form-group">
                                        <input type="number" class="form-control" id="exampleInputName2" placeholder="Phone">
                                    </div>
                                </li>
                                <li class="search_area">
                                    <div class="form-group">
                                        <textarea id="form_message" name="form_message" class="form-control required" rows="5" required="required" placeholder="Message"></textarea>
                                    </div>
                                </li>
                                <li>
                                    <div class="search_option_button">
                                        <button type="submit" class="btn btn-block btn-thm h55">Send Message</button>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Feature Properties -->
    <section class="feature-property bgc-f4">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="main-title text-center">
                        <h2>다른 상품 보기</h2>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="popular_listing_slider1">
                        <%
                            for (ItemDto BItemDto : itemDtoList) {
                        %>
                        <div class="item" onclick="location.href='/items/<%=BItemDto.getItemSeq()%>'">
                            <div class="feat_property">
                                <div class="thumb">
                                    <img class="img-whp" src="<%=BItemDto.getMainImagePath()%>" alt="ra1.jpg">
                                    <div class="thmb_cntnt">
                                        <ul class="tag mb0">
                                            <li class="list-inline-item"><a href="#"><%=itemDto.getItemPrice()%>원</a></li>
                                            <li class="list-inline-item"><a href="#"><%=itemDto.getItemCount()%>개</a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="details">
                                    <div class="tc_content">
                                        <h4><%=BItemDto.getItemTitle()%></h4>
                                        <p><%=BItemDto.getItemContents().substring(0, (BItemDto.getItemContents().length() < 10 ? BItemDto.getItemContents().length() : 10))%>...</p>
                                    </div>
                                    <div class="fp_footer">
                                        <ul class="fp_meta float-left mb0">
                                        </ul>
                                        <ul class="fp_meta float-right mb0">
                                            <li class="list-inline-item"><a href="#"><span class="flaticon-zoom"></span></a></li>
                                            <li class="list-inline-item"><a class="icon" href="#"><span class="flaticon-love"></span></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <a class="scrollToHome" href="#"><i class="fa fa-angle-up"></i></a>
</div>
<!-- Wrapper End -->
<script src="/js/jquery-3.6.0.js"></script>
<script src="/js/jquery-migrate-3.0.0.min.js"></script>
<script src="/js/popper.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/jquery.mmenu.all.js"></script>
<script src="/js/ace-responsive-menu.js"></script>
<script src="/js/bootstrap-select.min.js"></script>
<script src="/js/isotop.js"></script>
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
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAAz77U5XQuEME6TpftaMdX0bBelQxXRlM&callback=initMap"></script>
<script src="/js/googlemaps1.js"></script>
<!-- Custom script for all pages -->
<script src="/js/script.js"></script>
</body>
</html>