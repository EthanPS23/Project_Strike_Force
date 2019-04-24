<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>


<script>
$(document).on('click','.navbar-collapse.in',function(e) {
    if( $(e.target).is('a:not(".dropdown-toggle")') ) {
        $(this).collapse('hide');
    }
});
</script>

<script src="js/jquery-3.2.1.min.js"></script>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="icon" href="img/TravelLogoFavicon2.png" type="image/png">
<title>Book Trip</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="vendors/linericon/style.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/magnific-popup.css">
<link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
<link rel="stylesheet" href="vendors/nice-select/css/nice-select.css">
<!-- main css -->
<link rel="stylesheet" href="css/style.css">
</head>	
	<!--================ Start Header Menu Area =================-->
	<header class="header_area">
		<div class="main_menu">
			<nav class="navbar navbar-expand-lg navbar-light">
				<div class="container">
					<!-- Brand and toggle get grouped for better mobile display -->
					<!-- following line of code is logo image on header -->
					<a class="navbar-brand logo_h" href="index.jsp"><img src="img/newLogo12.png" alt=""></a>
					<button class="navbar-toggler" type="button" onClick="e" data-toggle="collapse" data-target="#navbarSupportedContent"
					 aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse offset" id="navbarSupportedContent">
						<ul class="nav navbar-nav menu_nav ml-auto">
							<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
							<li class="nav-item"><a class="nav-link" href="packages.jsp">Packages</a></li>
							<li class="nav-item submenu dropdown">
								<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
								 aria-expanded="false">Pages</a>
								<ul class="dropdown-menu">
									<li class="nav-item"><a class="nav-link" href="about-us.jsp">About</a></li>
									<li class="nav-item"><a class="nav-link" href="book-trip.jsp">Book trip</a></li>
									<li class="nav-item"><a class="nav-link" href="Login.jsp">Login</a></li>
								</ul>
							</li>
							<li class="nav-item submenu dropdown">
								<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
								 aria-expanded="false">Blog</a>
								<ul class="dropdown-menu">
									<li class="nav-item"><a class="nav-link" href="blog.jsp">Blog</a></li>
									<li class="nav-item"><a class="nav-link" href="single-blog.jsp">Blog Details</a></li>
								</ul>
							</li>
							<li class="nav-item"><a class="nav-link" href="contact.jsp">Contact</a></li>
							<li class="nav-item"><a class="nav-link" href="Login.jsp">Login</a></li>
							<li class="nav-item"><a class="nav-link" href="logout.jsp">LogOut</a></li>
						</ul>
						<ul class="nav navbar-nav ml-auto">
							<li class="nav-item">
								<a href="packages.jsp" class="primary-btn">Book a trip</a>
							</li>
							<li class="nav-item">
								<button type="button" class="search nav-link">
									<i class="lnr lnr-magnifier" id="search"></i>
								</button>
							</li>
						</ul>
					</div>
				</div>
			</nav>
			
			<div class="search_input" id="search_input_box">
				<div class="container">
					<form class="d-flex justify-content-between">
						<input type="text" class="form-control" id="search_input" placeholder="Search Here">
						<button type="submit" class="btn"></button>
						<span class="lnr lnr-cross" id="close_search" title="Close Search"></span>
					</form>
				</div>
			</div>
		</div>
	</header>
	<!--================ End Header Menu Area =================-->
	
	
</html>