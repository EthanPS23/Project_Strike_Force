<%@ page import="java.sql.*, java.io.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">

<!-- /* User selects a country of either USA or Canada,
 * Depending on the country selected either provinces or states are displayed for corresponding country.
 * Author: Ethan Shipley
 * Course CMPP 264
 * Date: April 7 2019
 */ -->
<script src="jquery-3.3.1.js"></script>
<script>
	//Ethan Shipley
	// Loads the country combo box based on information of available countries on the database
	$(document).ready(function(){
		$.get("/TravelExpertsWebApp/rest/countries/getallcountries",
		function(data){
			for (i=0; i<data.length; i++){
				$("#CustCountry").append("<option value='"
					+ data[i].countryId
					+ "'>"
					+ data[i].countryName
					+ "</option>"
					);
				$('#CustCountry').niceSelect('update');
			}
		},
		"json");
	})
	//Ethan Shipley
	// Based on the user selection of the country the available provinces or states are then displayed
	function getProvState(countryid)
	{			
		$.get("/TravelExpertsWebApp/rest/provstates/getprovstates/" + countryid,
			function(data){
				$("#CustProv").empty();
				$("#CustProv").append("<option value=''>Select One</option>");
				for (i=0; i<data.length; i++){
					$("#CustProv").append("<option value='"
						+ data[i].provStateId
						+ "'>"
						+ data[i].provStateName
						+ "</option>"
					);
					$('#CustProv').niceSelect('update');
				}
			},
			"json"
		);
	}
</script>

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="icon" href="img/TravelLogoFavicon2.png" type="image/png">
<title>Registration</title>
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
						<a href="index.jsp">Home</a> <a href="book-trip.jsp">Registration</a>
					</div>
					<h2>Registration</h2>
				</div>
			</div>
		</div>
	</section>
	<!--================End Home Banner Area =================-->

	<!--================ Start Registration Area =================-->
	<section class="trip-area section_gap">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-7 col-md-10">
					<div class="trip-form-section">
						<h1>
							Register <br> for your next Tour
						</h1>
						<!-- Ethan Shipley
							Checks the user seesion to see if already logged in and if so
							they are redirected to the home page -->
						<%
							String b = (String)session.getAttribute("loggedin");
							if (b =="true"){
								response.sendRedirect("index.jsp");
							}
							String message = (String)session.getAttribute("message");
							if ( message !=null && !message.equals("")){
								/* out.print("<h4 style='color:crimson'>" + message + "</h4>"); */
								out.print("<script>alert('" + message + "');</script>");
								session.setAttribute("message", "");
							}
							%>
						<!-- <form class="trip-form-area trip-page-form trip-form text-right"
							id="myForm" action="mail.jsp" method="get"> -->
						<Form class="trip-form-area trip-page-form trip-form text-right"
							id="myForm" action="RegistrationServlet" method="post">
						<!-- <Form class="trip-form-area trip-page-form trip-form text-right"
							id="myForm" action="rest/Register/register" method="post"> -->
							<div class="form-group col-md-12">
								<input type="text" class="form-control" id="CustFirstName" name="CustFirstName"
									placeholder="First Name" onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'First Name'" maxlength="25">
							</div>
							<div class="form-group col-md-12">
								<input type="text" class="form-control" id="CustLastName" name="CustLastName"
									placeholder="Last Name" onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Last Name'" maxlength="25">
							</div>
							<div class="form-group col-md-12">
								<input type="text" class="form-control" id="CustAddress"
									name="CustAddress" placeholder="Address"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Address'" maxlength="75">
							</div>
							<div class="form-group col-md-12">
								<input type="text" class="form-control" id="CustCity"
									name="CustCity" placeholder="City"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'City'" maxlength="50">
							</div>
							<div class="form-group col-md-12">
								<div class="form-select">
									<select name="CustCountry" id="CustCountry" onchange="getProvState(this.value)">
										<option value="">Country</option>
									</select>
								</div>
							</div>
							<div class="form-group col-md-12">
								<div class="form-select">
									<select name="CustProv" id="CustProv">
										<option value="">Province/State</option>
									</select>
								</div>
							</div>
							<div class="form-group col-md-12">
								<input type="text" class="form-control" id="CustPostal"
									name="CustPostal" placeholder="Postal Code"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Postal Code'" maxlength="7">
							</div>
							<div class="form-group col-md-12">
								<input type="text" class="form-control" id="CustHomePhone"
									name="CustHomePhone" placeholder="Home Phone"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Home Phone'" maxlength="20">
							</div>
							<div class="form-group col-md-12">
								<input type="text" class="form-control" id="CustBusPhone"
									name="CustBusPhone" placeholder="Business Phone"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Business Phone'" maxlength="20">
							</div>
							<div class="form-group col-md-12">
								<input type="text" class="form-control" id="CustEmail"
									name="CustEmail" placeholder="Email"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Email'" maxlength="50">
							</div>
							<div class="form-group col-md-12">
								<input type="password" class="form-control" id="CustPassword"
									name="CustPassword" placeholder="Password"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Password'" maxlength="255">
							</div>
							<!-- <div class="form-group col-md-12">
								<input type="text" class="form-control" id="AgentID"
									name="AgentID" placeholder="Agent ID"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Agent ID'" maxlength="11">
							</div> -->
							<div class="col-lg-12 text-center">
								<button class="primary-btn text-uppercase">Register</button>
							</div>
						</Form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--================ End Register Area =================-->
	
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
	<!-- <script src="js/mail-script.js"></script>-->
	<!--gmaps Js-->
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
	<script src="js/gmaps.min.js"></script>
	<script src="js/theme.js"></script>
</body>

</html>