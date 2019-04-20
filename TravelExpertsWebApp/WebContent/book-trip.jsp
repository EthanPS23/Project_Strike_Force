<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">

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

<body>

	<!-- Header area Include statement -->
	<jsp:include page="header.jsp" />

	<!--================Home Banner Area =================-->
	<section class="banner_area ">
		<div class="banner_inner overlay d-flex align-items-center">
			<div class="container">
				<div class="banner_content">
					<div class="page_link">
						<a href="index.jsp">Home</a> <a href="book-trip.jsp">Book a
							Trip</a>
					</div>
					<h2>Book a Trip</h2>
				</div>
			</div>
		</div>
	</section>
	<!--================End Home Banner Area =================-->

	<!--================ Start Book a Trip Area =================-->
	<section class="trip-area section_gap">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-7 col-md-10">
					<div class="trip-form-section">
						<h1>
							Book a Trip <br> for your next Tour
						</h1>
						<form class="trip-form-area trip-page-form trip-form text-right"
							id="myForm" action="mail.jsp" method="post">
							<div class="form-group col-md-12">
								<div class="form-select">
									<select>
										<option value="1">Select Packages</option>
										<option value="1">Package 01</option>
										<option value="1">Package 02</option>
										<option value="1">Package 03</option>
										<option value="1">Package 04</option>
									</select>
								</div>
							</div>
							<div class="form-group col-md-12">
								<input type="text" class="form-control" id="name" name="name"
									placeholder="To" onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'To'">
							</div>
							<div class="form-group col-md-12">
								<input type="email" class="form-control" id="email" name="email"
									placeholder="From" onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'From'">
							</div>
							<div class="form-group col-md-12">
								<input type="text" class="form-control" id="subject"
									name="subject" placeholder="Return"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Return'">
							</div>
							<div class="form-group col-md-12">
								<input type="text" class="form-control" id="subject"
									name="subject" placeholder="Adults"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Adults'">
							</div>
							<div class="form-group col-md-12">
								<input type="text" class="form-control" id="subject"
									name="subject" placeholder="Child"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Child'">
							</div>
							<div class="col-lg-12 text-center">
								<button class="primary-btn text-uppercase">Search
									Flights</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--================ End Book a Trip Area =================-->

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