<%@ page import="com.submarket.front.dto.UserDto" %>
<%@ page import="com.submarket.front.util.CmmUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="com.submarket.front.dto.ItemDto" %>
<%@ page import="java.util.LinkedList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    List<ItemDto> itemDtoList = (List<ItemDto>) request.getAttribute("itemDtoList");

    if (itemDtoList == null) {
        itemDtoList = new LinkedList<>();
    }
    UserDto userInfo = (UserDto) session.getAttribute("SS_USER_INFO");

    if (userInfo == null) {
        userInfo = new UserDto();
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
    <title>SubMarket - Item List</title>
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
                                            <form action="/seller/login" method="post">
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
                                                    <input type="text" class="form-control" id="exampleInputName2" placeholder="Username">
                                                </div>
                                                <div class="form-group input-group mb20">
                                                    <input type="password" class="form-control" id="exampleInputPassword3" placeholder="Password">
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
                    <li class="list-inline-item"><a class="muser_icon" href="/user/profile"><span class="flaticon-avatar"></span></a></li>
                    <li class="list-inline-item"><a class="menubar" href="#menu"><span></span></a></li>
                </ul>
            </div>
        </div><!-- /.mobile-menu -->
        <nav id="menu" class="stylehome1">
            <ul>
                <%
                    if (session.getAttribute("SS_USER_TOKEN") != null) {
                %>

                <li><a href="/user/profile"><span class="flaticon-avatar"></span> Profile</a></li>
                <li><a href="/user/sublist"><span class="flaticon-list"></span> My SubList</a></li>
                <li><a href="/user/reviewlist"><span class="flaticon-note"></span> My Reviews</a></li>
                <li><a href="/logout"><span class="flaticon-logout"></span> Logout</a></li>

                <li><a>Categories</a></li>

                <li><a href="/items/category/1"><span class="flaticon-list"></span> 식품</a></li>
                <li><a href="/items/category/2"><span class="flaticon-list"></span> 쇼핑</a></li>
                <li><a href="/items/category/3"><span class="flaticon-list"></span> 생필품</a></li>
                <li><a href="/items/category/4"><span class="flaticon-list"></span> 건강</a></li>
                <li><a href="/items/category/5"><span class="flaticon-list"></span> 뷰티</a></li>
                <li><a href="/items"><span class="flaticon-list"></span> 모든 상품 보기</a></li>
                <%
                } else {
                %>

                <li><a href="/user/page-login"><span class="flaticon-avatar"></span>Login</a></li>
                <li><a href="/regForm"><span class="flaticon-edit"></span>Register</a></li>

                <%
                    }
                %>
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
                                <div class="col-sm-6 col-md-4 col-xl-2" onclick="location.href='/items/category/1'">
                                    <div class="icon-box text-center">
                                        <div class="icon"><span class="flaticon-cutlery"></span></div>
                                        <div class="content-details">
                                            <div class="title">식품</div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-4 col-xl-2" onclick="location.href='/items/category/2'">
                                    <div class="icon-box text-center">
                                        <div class="icon"><span class="flaticon-shopping-bag"></span></div>
                                        <div class="content-details">
                                            <div class="title">쇼핑</div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-4 col-xl-2" onclick="location.href='/items/category/3'">
                                    <div class="icon-box text-center">
                                        <div class="icon"><span class="flaticon-tent"></span></div>
                                        <div class="content-details">
                                            <div class="title">생필품</div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-4 col-xl-2" onclick="location.href='/items/category/4'">
                                    <div class="icon-box text-center">
                                        <div class="icon"><span class="flaticon-desk-bell"></span></div>
                                        <div class="content-details">
                                            <div class="title">건강</div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-4 col-xl-2" onclick="location.href='/items/category/5'">
                                    <div class="icon-box text-center">
                                        <div class="icon"><span class="flaticon-mirror"></span></div>
                                        <div class="content-details">
                                            <div class="title">뷰티</div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-4 col-xl-2" onclick="location.href='/items'">
                                    <div class="icon-box text-center">
                                        <div class="icon"><span class="flaticon-brake"></span></div>
                                        <div class="content-details">
                                            <div class="title">모든 상품 보기</div>
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
    <!-- Inner Page Breadcrumb -->
    <section class="inner_page_breadcrumb style2">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-xl-6">
                    <div class="breadcrumb_content">
                        <h2 class="breadcrumb_title"><%=CmmUtil.nvl((String) request.getAttribute("title"))%></h2>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="/index">Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page"><%=CmmUtil.nvl((String) request.getAttribute("title"))%></li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Main Blog Post Content -->
    <section class="blog_post_container">
        <div class="container">
            <form class="footer_mailchimp_form" style="margin-bottom: 1%">
                <div class="form-row align-items-center">
                    <div class="col-auto">
                        <input type="text" class="form-control" id="itemName" placeholder="Search" style="width: 650px">
                        <button type="submit" class="btn btn-primary">Search</button>
                    </div>
                </div>
            </form>
            <div class="row">
                <div class="col-lg-8">
                    <div class="main_blog_post_content">
                        <%
                            for (ItemDto itemDto : itemDtoList) {

                        %>
                        <div class="for_blog list-type feat_property">
                            <div class="thumb w100 pb10">
                                <img class="img-whp" src="/images/blog/7.jpg" alt="7.jpg">
                            </div>
                            <div class="details pb5" style="width: 100%; height: 100%;">
                                <div class="tc_content pt15">
                                    <div class="bp_meta mb20">
                                        <ul>
                                            <li class="list-inline-item"><a href="#"><span class="flaticon-avatar mr10"></span><%=CmmUtil.nvl(itemDto.getSellerId())%></a></li>
                                            <li class="list-inline-item"><a href="#"><span class="flaticon-date mr10"></span><%=itemDto.getItemPrice()%> 원 (월)</a></li>
                                        </ul>
                                    </div>
                                    <h4 class="mt15 mb20"><%=CmmUtil.nvl(itemDto.getItemTitle())%></h4>
                                    <p class="mb10"><%=CmmUtil.nvl(itemDto.getItemContents())%></p>
                                    <a class="tdu text-thm" href="/items/<%=itemDto.getItemSeq()%>">Read More</a>
                                </div>
                            </div>
                        </div>
                        <%
                            }
                        %>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="mbp_pagination mt30">
<%--                                    <ul class="page_navigation">--%>
<%--                                        <li class="page-item">--%>
<%--                                            <a class="page-link" href="#" tabindex="-1" aria-disabled="true"> <span class="fa fa-angle-left"></span></a>--%>
<%--                                        </li>--%>
<%--                                        <li class="page-item"><a class="page-link" href="#">1</a></li>--%>
<%--                                        <li class="page-item"><a class="page-link" href="#">2</a></li>--%>
<%--                                        <li class="page-item active" aria-current="page">--%>
<%--                                            <a class="page-link" href="#">3 <span class="sr-only">(current)</span></a>--%>
<%--                                        </li>--%>
<%--                                        <li class="page-item"><a class="page-link" href="#">4</a></li>--%>
<%--                                        <li class="page-item"><a class="page-link" href="#">5</a></li>--%>
<%--                                        <li class="page-item"><a class="page-link" href="#">...</a></li>--%>
<%--                                        <li class="page-item"><a class="page-link" href="#">15</a></li>--%>
<%--                                        <li class="page-item">--%>
<%--                                            <a class="page-link" href="#"><span class="fa fa-angle-right"></span></a>--%>
<%--                                        </li>--%>
<%--                                    </ul>--%>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-xl-4">
                    <div class="terms_condition_widget">
                        <h4 class="title">Categories Property</h4>
                        <div class="widget_list">
                            <ul class="list_details order_list list-style-type-bullet">
                                <li><a href="/items/category/1">식품</a></li>
                                <li><a href="/items/category/2">쇼핑</a></li>
                                <li><a href="/items/category/3">생필품</a></li>
                                <li><a href="/items/category/4">건강</a></li>
                                <li><a href="/items/category/5">뷰티</a></li>
                                <li><a href="/items">모든 상품 보기</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="sidebar_feature_listing">
                        <h4 class="title">Top Article</h4>
                        <div class="media">
                            <img class="align-self-start mr-3" src="/images/blog/fls1.jpg" alt="fls1.jpg">
                            <div class="media-body">
                                <h5 class="mt-0 post_title">Great Business Tips in 2020</h5>
                                <a href="#">January 7, 2021</a>
                            </div>
                        </div>
                        <div class="media">
                            <img class="align-self-start mr-3" src="/images/blog/fls2.jpg" alt="fls2.jpg">
                            <div class="media-body">
                                <h5 class="mt-0 post_title">Excited News About Fashion.</h5>
                                <a href="#">January 7, 2021</a>
                            </div>
                        </div>
                        <div class="media mb0">
                            <img class="align-self-start mr-3" src="/images/blog/fls3.jpg" alt="fls3.jpg">
                            <div class="media-body">
                                <h5 class="mt-0 post_title">8 Amazing Tricks About Business</h5>
                                <a href="#">January 7, 2021</a>
                            </div>
                        </div>
                    </div>
<%--                    <div class="blog_tag_widget">--%>
<%--                        <h4 class="title">Tags</h4>--%>
<%--                        <ul class="tag_list">--%>
<%--                            <li class="list-inline-item"><a href="#">Travelling</a></li>--%>
<%--                            <li class="list-inline-item"><a href="#">Art</a></li>--%>
<%--                            <li class="list-inline-item"><a href="#">Vacation</a></li>--%>
<%--                            <li class="list-inline-item"><a href="#">Tourism</a></li>--%>
<%--                            <li class="list-inline-item"><a href="#">Culture</a></li>--%>
<%--                            <li class="list-inline-item"><a href="#">Lifestyle</a></li>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
                </div>
            </div>
        </div>
    </section>

<%--    <!-- Our Footer -->--%>
<%--    <section class="footer_one home1">--%>
<%--        <div class="container pb70">--%>
<%--            <div class="row">--%>
<%--                <div class="col-sm-6 col-md-6 col-lg-3 col-xl-3">--%>
<%--                    <div class="footer_contact_widget">--%>
<%--                        <h4>Contact Us</h4>--%>
<%--                        <ul class="list-unstyled">--%>
<%--                            <li class="text-white df"><span class="flaticon-pin mr15"></span><a href="#">329 Queensberry Street, North Melbourne VIC 3051, Australia.</a></li>--%>
<%--                            <li class="text-white"><span class="flaticon-phone mr15"></span><a href="#">+123 456 7890</a></li>--%>
<%--                            <li class="text-white"><span class="flaticon-email mr15"></span><a href="#">support@skola.com</a></li>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="col-sm-6 col-md-6 col-lg-2 col-xl-3">--%>
<%--                    <div class="footer_qlink_widget">--%>
<%--                        <h4>Company</h4>--%>
<%--                        <ul class="list-unstyled">--%>
<%--                            <li><a href="#">Help Center</a></li>--%>
<%--                            <li><a href="#">About</a></li>--%>
<%--                            <li><a href="#">Career</a></li>--%>
<%--                            <li><a href="#">How It Works</a></li>--%>
<%--                            <li><a href="#">Article & Tips</a></li>--%>
<%--                            <li><a href="#">Terms & Service</a></li>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="col-sm-5 col-md-6 col-lg-2 col-xl-2">--%>
<%--                    <div class="footer_qlink_widget pl0">--%>
<%--                        <h4>Discover</h4>--%>
<%--                        <ul class="list-unstyled">--%>
<%--                            <li><a href="#">Chicago</a></li>--%>
<%--                            <li><a href="#">Los Angels</a></li>--%>
<%--                            <li><a href="#">Miami</a></li>--%>
<%--                            <li><a href="#">New York</a></li>--%>
<%--                            <li><a href="#">Florida</a></li>--%>
<%--                            <li><a href="#">Boston</a></li>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="col-sm-7 col-md-6 col-lg-4 col-xl-4">--%>
<%--                    <div class="footer_social_widget">--%>
<%--                        <h4>Subscribe</h4>--%>
<%--                        <p class="text-white mb20">We don’t send spam so don’t worry.</p>--%>
<%--                        <form class="footer_mailchimp_form">--%>
<%--                            <div class="form-row align-items-center">--%>
<%--                                <div class="col-auto">--%>
<%--                                    <input type="email" class="form-control" id="inlineFormInput" placeholder="Enter your email">--%>
<%--                                    <button type="submit" class="btn btn-primary">Subscribe</button>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </form>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <hr>--%>
<%--        <div class="container pt20 pb30">--%>
<%--            <div class="row">--%>
<%--                <div class="col-md-4 col-lg-4">--%>
<%--                    <div class="copyright-widget mt10 mb15-767">--%>
<%--                        <p>© Guido - All rights reserved.</p>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="col-md-4 col-lg-4">--%>
<%--                    <div class="footer_logo_widget text-center mb15-767">--%>
<%--                        <div class="wrapper">--%>
<%--                            <div class="logo text-center">--%>
<%--                                <img src="/images/footer-logo.svg" alt="footer-logo.svg">--%>
<%--                                <span class="logo_title text-white pl15">Guido</span>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="col-md-4 col-lg-4">--%>
<%--                    <div class="footer_social_widget text-right tac-smd mt10">--%>
<%--                        <ul class="mb0">--%>
<%--                            <li class="list-inline-item"><a href="#"><i class="fa fa-facebook"></i></a></li>--%>
<%--                            <li class="list-inline-item"><a href="#"><i class="fa fa-twitter"></i></a></li>--%>
<%--                            <li class="list-inline-item"><a href="#"><i class="fa fa-instagram"></i></a></li>--%>
<%--                            <li class="list-inline-item"><a href="#"><i class="fa fa-linkedin"></i></a></li>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </section>--%>
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
<!-- Custom script for all pages -->
<script src="/js/script.js"></script>
</body>
</html>