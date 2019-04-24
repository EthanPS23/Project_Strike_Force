<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">

<!-- Author: James Cockriell
Date: April 24, 2019
Description: Packages page for web app -->

<script src="jquery-3.3.1.js"></script>
<script>
$(document).ready(function(){
	$.get("/TravelExpertsWebApp/rest/packages/getallpackages",
		function(data){
			for (i=0; i<data.length; i++){
				$('#divtoappend').append("<div class=\"col-lg-6 col-md-8\">"
						+ "<div class=\"single-package\">" 
						+ "<div class=\"thumb\">"
						+ "<img class=\"img-fluid\" src=\"img/package/p1.jpg\" alt=\"\">"
						+ "</div>"
						+ "<p class=\"date\"><span>"
						+ data[i].pkgStartDate.toString().substring(0,7).replace(",","")
						+ "</span><br></p>"
						+ "<div class=\"meta-top d-flex\">"
						+ "<p>Start Date: "
						+ data[i].pkgStartDate.toString().substring(0,12).replace(",","") 
						+ "</p>"
						+ "</div>"
						+ "<p>End Date: "
						+ data[i].pkgEndDate.toString().substring(0,12).replace(",","")
						+ "</p>"
						+ "<h4>"
						+ data[i].pkgName
						+ "</h4>"
						+ "<p>"
						+ data[i].pkgDesc
						+ "</p>"
						+ "<p style='font-weight: bold;'>Price: " 
						+ "</p>"
						+ "<h3 style='color: blue;  font-weight: bold;'>$"
						+ data[i].pkgBasePrice
						+ "</h3>"
						+ "<a href=\"#\" class=\"primary-btn\">Book Now!</a>"
						+ "</div>"
						+ "</div>"
						);
				$('#divtoappend').niceSelect('update');						
			}
		},
		"json");
})			
		
</script>

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="icon" href="img/TravelLogoFavicon2.png" type="image/png">
<title>Packages</title>
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

<body>

	<!-- Header area Include statement -->
	<jsp:include page="header.jsp" />

	<!--================Home Banner Area =================-->
	<section class="banner_area ">
		<div class="banner_inner overlay d-flex align-items-center">
			<div class="container">
				<div class="banner_content">
					<div class="page_link">
						<a href="index.jsp">Home</a> <a href="packages.jsp">Packages</a>
					</div>
					<h2>Trip Packages</h2>
				</div>
			</div>
		</div>
	</section>
	<!--================End Home Banner Area =================-->

	<!--================ Start Trip Package Area =================-->
	<section class="package-area section_gap_top">
		<div class="container">
			<div class="row d-flex justify-content-center">
				<div class="ol-lg-12">
					<div class="main_title">
						<h1>Awesome World Adventures from TravelExperts!</h1>
						<p>We're Offering these Trip Packages</p>
						<span class="title-widget-bg"></span>
					</div>
				</div>
			</div>
			
			<!-- -------------------------generated card -->
			<div class="row" id="divtoappend">
			
			<!-- Card is generated here -->
			
			</div>
		</div>
	</section>
	<!--================ End Trip Package Area =================-->



	<!--================ Start CTA Area =================-->
	<div class="cta-area2 section_gap">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-5">
					<img class="cta-img2 img-fluid" src="img/cta-img2.png" alt="">
				</div>
				<div class="offset-lg-2 col-lg-5">
					<h1>
						Subscribe <br> for our Newsletter
					</h1>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
						sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
					</p>
					<div class="" id="mc_embed_signup2">
						<form target="_blank" novalidate="true"
							action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01"
							method="get" class="form-inline">
							<div class="d-flex flex-row">
								<input class="form-control" name="EMAIL"
									placeholder="Enter Email Address"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Enter Email Address '" required=""
									type="email">
								<button class="click-btn btn text-uppercase">subscribe</button>
								<div style="position: absolute; left: -5000px;">
									<input name="b_36c4fd991d266f23781ded980_aefe40901a"
										tabindex="-1" value="" type="text">
								</div>
							</div>
							<div class="info"></div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--================ End CTA Area =================-->

	<!-- Footer area Include statement -->
	<jsp:include page="footer.jsp" />

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/popper.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/stellar.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="vendors/nice-select/js/jquery.nice-select.min.js"></script>
	<script src="vendors/owl-carousel/owl.carousel.min.js"></script>
	<script src="js/owl-carousel-thumb.min.js"></script>
	<script src="js/jquery.ajaxchimp.min.js"></script>
	<script src="js/mail-script.js"></script>
	<!--gmaps Js-->
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
	<script src="js/gmaps.min.js"></script>
	<script src="js/theme.js"></script>
</body>

</html>