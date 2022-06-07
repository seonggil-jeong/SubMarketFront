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
    <link rel="stylesheet" href="/css/fileuploader.css">
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
<body>
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
                    <span>Guido</span>
                </a>
                <!-- Responsive Menu Structure-->
                <!--Note: declare the Menu style in the data-menu-style="horizontal" (options: horizontal, vertical, accordion) -->
                <ul id="respMenu" class="ace-responsive-menu text-right" data-menu-style="horizontal">
                    <%
                        if (session.getAttribute("SS_SELLER_TOKEN") != null) {

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

    <!-- Main Header Nav For Mobile -->
    <div id="page" class="stylehome1 h0">
        <div class="mobile-menu">
            <div class="header stylehome1">
                <div class="main_logo_home2 text-left">
                    <img class="nav_logo_img img-fluid mt15" src="/images/header-logo2.svg" alt="header-logo2.svg">
                    <span class="mt15">Guido</span>
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

    <!-- Our Dashbord -->
    <section class="our-dashbord dashbord bgc-f4 ovh">
        <div class="container mt80">
            <div class="row">
                <div class="col-lg-12">
                    <div class="dashboard_navigationbar dn db-992">
                        <div class="dropdown">
                            <button onclick="myFunction()" class="dropbtn"><i class="fa fa-bars pr10"></i> Dashboard Navigation</button>
                            <ul id="myDropdown" class="dropdown-content">
                                <li><a href="page-my-dashboard.html"><span class="flaticon-web-page"></span> Dashboard</a></li>
                                <li><a href="page-profile.html"><span class="flaticon-avatar"></span> My Profile</a></li>
                                <li><a href="page-my-listing.html"><span class="flaticon-list"></span> My Listings</a></li>
                                <li><a href="page-my-bookmark.html"><span class="flaticon-love"></span> Bookmarks</a></li>
                                <li><a href="page-message.html"><span class="flaticon-chat"></span> Message</a></li>
                                <li><a href="page-my-review.html"><span class="flaticon-note"></span> Reviews</a></li>
                                <li class="active"><a href="page-add-new-listing.html"><span class="flaticon-web-page"></span> Add New Listing</a></li>
                                <li><a href="page-my-logout.html"><span class="flaticon-logout"></span> Logout</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-lg-12 mb10">
                    <div class="breadcrumb_content style2 mb25">
                        <h2 class="breadcrumb_title">상품 등록</h2>
                    </div>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-lg-7">
                    <form action="/seller/item/add" method="post" enctype="multipart/form-data">
                    <div class="my_dashboard_review">
                        <div class="row">
                            <div class="col-lg-12">
                                <h4 class="mb30">상품 정보</h4>
                                <div class="my_profile_setting_input form-group">
                                    <label for="listingPlace">Item Title</label>
                                    <input type="text" class="form-control" id="listingPlace" name="itemTitle" placeholder="What the name of Item">
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="my_profile_setting_input ui_kit_select_search form-group">
                                    <label>Price</label>
                                    <input type="text" class="form-control" placeholder="(원)" name="itemPrice">
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="my_profile_setting_input form-group">
                                    <label for="propertyDescription">상품 수량</label>
                                    <input type="text" class="form-control" placeholder="상품 수량을 입력해주세요" name="itemCount">
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="my_profile_setting_textarea">
                                    <label for="propertyDescription">상품 설명</label>
                                    <textarea class="form-control" id="propertyDescription" rows="7" name="itemContents">상품 설명을 입력해 주세요.</textarea>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="my_profile_setting_input ui_kit_select_search form-group">
                                    <label>Category</label>
                                    <select class="selectpicker" data-live-search="true" data-width="100%" name="categorySeq">
                                        <option data-tokens="Category1" value="1">식품</option>
                                        <option data-tokens="Category2" value="2">쇼핑</option>
                                        <option data-tokens="Category3" value="3">생필품</option>
                                        <option data-tokens="Category4" value="4">건강</option>
                                        <option data-tokens="Category4" value="5">뷰티</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="my_dashboard_review mt30">
                        <div class="row">
                            <div class="col-lg-12">
                                <h4 class="mb30">Media</h4>
                            </div>
                            <div class="col-lg-12">
                                <h5>Main image</h5>
                                <div class="upload_file_input_add_remove">
                                    <span class="btn_upload"><input type="file" id="imag" title="" class="input-img" name="mainImage"/><span class="flaticon-upload"></span></span>
                                    <img id="ImgPreview" src="/images/resource/upload-img.png" class="preview1" alt="" />
                                    <button id="removeImage1" class="btn-rmv1" type="button"><span class="flaticon-delete"></span></button>
                                </div>
                                <small>Maximum file size: 1000kb.</small>
                            </div>
                            <div class="col-lg-12 mt50">
                                <h5 class="mb20">Gallery Images (optional)</h5>
                                <ul class="mb0">
                                    <li class="list-inline-item vat mb30-767">
                                        <div class="upload_file_input_add_remove">
                                            <span class="btn_upload"><input type="file" id="imag2" title="" class="input-img" name="subImage"/><span class="flaticon-upload"></span></span>
                                            <img id="ImgPreview2" src="/images/resource/upload-img.png" class="preview2" alt="" />
                                            <button id="removeImage2" class="btn-rmv2" type="button"><span class="flaticon-delete"></span></button>
                                        </div>
                                        <small>Maximum file size: 1000kb.</small>
                                    </li>
<%--                                    <li class="list-inline-item">--%>
<%--                                        <div class="portfolio_item">--%>
<%--                                            <img class="img-fluid" src="/images/listing/a1.jpg" alt="a1.jpg">--%>
<%--                                            <div class="edu_stats_list" data-toggle="tooltip" data-placement="top" title="Delete" data-original-title="Delete"><a href="#"><span class="flaticon-delete"></span></a></div>--%>
<%--                                        </div>--%>
<%--                                    </li>--%>
<%--                                    <li class="list-inline-item">--%>
<%--                                        <div class="portfolio_item">--%>
<%--                                            <img class="img-fluid" src="/images/listing/a2.jpg" alt="a2.jpg">--%>
<%--                                            <div class="edu_stats_list" data-toggle="tooltip" data-placement="top" title="Delete" data-original-title="Delete"><a href="#"><span class="flaticon-delete"></span></a></div>--%>
<%--                                        </div>--%>
<%--                                    </li>--%>
                                </ul>
                            </div>
                        </div>
                    </div>
                        <input type="submit" class="btn btn-thm listing_save_btn mt30" value="Submit">
                    </form>
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
<script src="/js/chart.min.js"></script>
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
<script src="/js/jquery.smartuploader.js"></script>
<script src="/js/dashboard-script.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAAz77U5XQuEME6TpftaMdX0bBelQxXRlM&callback=initMap"></script>
<script src="/js/googlemaps1.js"></script>
<!-- Custom script for all pages -->
<script src="/js/script.js"></script>
</body>
</html>