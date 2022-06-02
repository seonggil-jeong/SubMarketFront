<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html dir="ltr" lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords"
          content="airbnb, booking, city guide, directory, events, hotel booking, listings, marketing, places, restaurant, restaurant">
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
    <link href="//images/favicon.ico" sizes="128x128" rel="shortcut icon" type="image/x-icon"/>
    <link href="//images/favicon.ico" sizes="128x128" rel="shortcut icon"/>

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
                <ul id="respMenu" class="ace-responsive-menu text-right" data-menu-style="horizontal"
                    style="margin-bottom: 1%">
                    <li class="list-inline-item add_listing"><a href="/index"><span class="dn-lg">HOME</span></a></li>
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
            </div>
        </div><!-- /.mobile-menu -->
    </div>

    <!-- Inner Page Breadcrumb -->
    <section class="inner_page_breadcrumb">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-xl-6">
                    <div class="breadcrumb_content">
                        <h2 class="breadcrumb_title">Sign in</h2>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="#">Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">userLogin</li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Our SigIn -->
    <section class="our-log">
        <div class="container">
            <div class="row">
                <div class="col-sm-12 col-lg-6 offset-lg-3">
                    <div class="login_form inner_page">
                        <form action="/user/login" method="post">
                            <div class="input-group mb-2 mr-sm-2">
                                <input type="text" class="form-control" id="inlineFormInputGroupUsername3"
                                       placeholder="UserId" name="userId">
                            </div>
                            <div class="input-group form-group mb5">
                                <input type="password" class="form-control" id="exampleInputPassword4"
                                       placeholder="UserPassword" name="userPassword">
                            </div>
                            <a class="btn-fpswd float-right" href="#" style="margin-left: 5%">Forgot password?</a>
                            <a class="btn-fpswd float-right" href="#">Forgot id?</a>
                            <button type="submit" class="btn btn-log btn-block btn-thm">Sign in</button>
                            <p class="text-center mb30 mt20">Don't have an account? <a class="text-thm" href="#">Sign
                                up</a></p>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Start Partners -->
    <section class="start-partners home1 bgc-thm pt60 pb60">
        <div class="container">
            <div class="row">
                <div class="col-lg-8">
                    <div class="start_partner tac-smd">
                        <h2>Submit Your Property Today!</h2>
                        <p>Explore some of the best tips from around the city from our partners and friends.</p>
                    </div>
                </div>
                <div class="col-lg-4 pr10">
                    <div class="parner_reg_btn text-right tac-smd">
                        <a class="btn" href="#">Submit Now</a>
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
<!-- Custom script for all pages -->
<script src="/js/script.js"></script>
</body>
</html>