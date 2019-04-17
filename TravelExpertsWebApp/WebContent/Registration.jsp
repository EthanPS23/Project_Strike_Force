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
<link rel="icon" href="img/favicon.png" type="image/png">
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

	<!--================ Start Header Menu Area =================-->
	<header class="header_area">
		<div class="main_menu">
			<nav class="navbar navbar-expand-lg navbar-light">
				<div class="container">
					<!-- Brand and toggle get grouped for better mobile display -->
					<a class="navbar-brand logo_h" href="index.jsp"><img
						src="img/logo.png" alt=""></a>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse offset"
						id="navbarSupportedContent">
						<ul class="nav navbar-nav menu_nav ml-auto">
							<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
							<li class="nav-item"><a class="nav-link"
								href="packages.jsp">Packages</a></li>
							<li class="nav-item submenu dropdown active"><a href="#"
								class="nav-link dropdown-toggle" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expanded="false">Pages</a>
								<ul class="dropdown-menu">
									<li class="nav-item"><a class="nav-link"
										href="about-us.jsp">About</a></li>
									<li class="nav-item"><a class="nav-link"
										href="book-trip.jsp">Book trip</a></li>
									<li class="nav-item"><a class="nav-link"
										href="Registration.jsp">Registration</a></li>
								</ul></li>
							<li class="nav-item submenu dropdown"><a href="#"
								class="nav-link dropdown-toggle" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expanded="false">Blog</a>
								<ul class="dropdown-menu">
									<li class="nav-item"><a class="nav-link" href="blog.jsp">Blog</a></li>
									<li class="nav-item"><a class="nav-link"
										href="single-blog.jsp">Blog Details</a></li>
								</ul></li>
							<li class="nav-item"><a class="nav-link" href="contact.jsp">Contact</a></li>
						</ul>
						<ul class="nav navbar-nav ml-auto">
							<li class="nav-item"><a href="#" class="primary-btn">Book
									a trip</a></li>
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
						<input type="text" class="form-control" id="search_input"
							placeholder="Search Here">
						<button type="submit" class="btn"></button>
						<span class="lnr lnr-cross" id="close_search" title="Close Search"></span>
					</form>
				</div>
			</div>
		</div>
	</header>
	<!--================ End Header Menu Area =================-->

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
						<!-- <form class="trip-form-area trip-page-form trip-form text-right"
							id="myForm" action="mail.jsp" method="get"> -->
						<Form class="trip-form-area trip-page-form trip-form text-right"
							id="myForm" action="RegistrationServlet" method="post">
							<div class="form-group col-md-12">
								<input type="text" class="form-control" id="CustFirstName" name="CustFirstName"
									placeholder="First Name" onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'First Name'">
							</div>
							<div class="form-group col-md-12">
								<input type="text" class="form-control" id="CustLastName" name="CustLastName"
									placeholder="Last Name" onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Last Name'">
							</div>
							<div class="form-group col-md-12">
								<input type="text" class="form-control" id="CustAddress"
									name="CustAddress" placeholder="Address"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Address'">
							</div>
							<div class="form-group col-md-12">
								<input type="text" class="form-control" id="Custcity"
									name="Custcity" placeholder="City"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'City'">
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
									onblur="this.placeholder = 'Postal Code'">
							</div>
							<div class="form-group col-md-12">
								<input type="text" class="form-control" id="CustHomePhone"
									name="CustHomePhone" placeholder="Home Phone"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Home Phone'">
							</div>
							<div class="form-group col-md-12">
								<input type="text" class="form-control" id="CustBusPhone"
									name="CustBusPhone" placeholder="Business Phone"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Business Phone'">
							</div>
							<div class="form-group col-md-12">
								<input type="text" class="form-control" id="CustEmail"
									name="CustEmail" placeholder="Email"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Email'">
							</div>
							<div class="form-group col-md-12">
								<input type="password" class="form-control" id="CustPassword"
									name="CustPassword" placeholder="Password"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Password'">
							</div>
							<div class="form-group col-md-12">
								<input type="text" class="form-control" id="AgentID"
									name="AgentID" placeholder="Agent ID"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Agent ID'">
							</div>
							<div class="col-lg-12 text-center">
								<button class="primary-btn text-uppercase">Register</button>
								<%
									if (request.getParameter("submit") != null)
									{
										String [] customer = new String[11];
										customer[0] = (String)request.getParameter("CustFirstName");
										customer[1] = (String)request.getParameter("CustLastName");
										customer[2]= (String)request.getParameter("CustAddress");
										customer[3]= (String)request.getParameter("CustCity");
										customer[4] = (String)request.getParameter("CustProv");
										customer[5] = (String)request.getParameter("CustCountry");
										customer[6] = (String)request.getParameter("CustPostal");
										customer[7] = (String)request.getParameter("CustHomePhone");
										customer[8] = (String)request.getParameter("CustBusPhone");
										customer[9] = (String)request.getParameter("CustEmail");
										customer[10] = (String)request.getParameter("AgentID");
								
										//pass data to validation function
										String message = validate(customer);
										if (message.equals(""))
										{
											//if not valid set error message and redisplay form
											out.print("<h4 style='color:crimson'>" + createCustomer(customer) + "</h4>");
											
											//after writing customer data empty the array so form will be empty
											
												customer[0] = "";
												customer[1] = "";
												customer[2] = "";
												customer[3] = "";
												customer[4] = "";
												customer[5] = "";
												customer[6] = "";
												customer[7] = "";
												customer[8] = "";
												customer[9] = "";
												customer[10] = "";
										
											
											/* displayForm(customer, out); */
										} else
										{
										   out.print("<h4 style='color:crimson'>" + message + "</h4>");
										   /* displayForm(customer, out); */
									    }
									}
									else
									{
										//display form
										String [] customer = {"","","","","","","","","","",""};
										/* displayForm(customer, out); */
									}
								%>
							</div>
						</Form>
						<%!
							public String validate(String [] customerData)
							{
								for (int i=0; i<customerData.length; i++)
								{
									if (customerData[i].equals(""))
									{
										switch(i)
										{
											case 0:
												return "**First name must have a value!**";
											
											case 1:
												return "**Last name must have a value!**";
											
											case 2:
												break;						
											
											case 3:
												return "**You must include your city name!**";
												
											case 4:
												return "**You must include your province or state name!**";
												
											case 5:
												return "**You must include your country name!**";
												
											case 6:
												return "**You must include your postal or zip code!**";
												
											case 7:
												return "**Please provide your home phone number!**";
												
											case 8:
												break;
												
											case 9:
	
												break;
											
											default:
											return "field must have a value.";
										}
									}
								}
								return "";
							}
						
							public String createCustomer(String [] customerArray)
							{
								String sql = "INSERT INTO customers ("
									+ "CustomerID, CustFirstName, CustLastName, CustAddress, CustCity, CustProv, CustCountry, CustPostal, CustHomePhone, CustBusPhone, CustEmail, AgentID"
								    + ") values (S_101_1_CUSTOMERS.NEXTVAL, "
									+ "'" + customerArray[0] + "'," 
									+ " '" + customerArray[1] + "',"
									+ " '" + customerArray[2] + "',"
									+ " '" + customerArray[3] + "',"
									+ " '" + customerArray[4] + "',"
									+ " '" + customerArray[5] + "',"
									+ " '" + customerArray[6] + "',"
									+ " '" + customerArray[7] + "',"
									+ " '" + customerArray[8] + "',"
									+ " '" + customerArray[9] + "',"
									+ " " + customerArray[10] + ")";
	
	
							    try
							    {
							        //Class.forName("com.mysql.jdbc.Driver");
									Class.forName("oracle.jdbc.driver.OracleDriver");
							        //Connection dbConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","root","password");
							        Connection dbConn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orant11g", "ictoosd", "ictoosd");
	
							        Statement stmt = dbConn.createStatement();
	
							        int rows = stmt.executeUpdate(sql);
	
							        // Cleanup
							        dbConn.close();  // close the connection
							        
									if (rows == 1)
									{
										return "Your registration was a success. Thank you for joining The Travel Experts.";
									}
									else
									{
										return "Insert was NOT successful";
									}
							    }
							    catch (Exception excp)
							    {
							        excp.printStackTrace();
							    }
								return "";
							}
						%>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--================ End Register Area =================-->

	<!--================  start footer Area =================-->
	<footer class="footer-area">
		<div class="footer_top section_gap_top">
			<div class="container">
				<div class="row">
					<div class="col-lg-3 col-md-6 col-sm-6">
						<div class="single-footer-widget">
							<h5 class="footer_title">About Agency</h5>
							<p class="about-text">The world has become so fast paced that
								people don’t want to stand by reading a page of information,
								they would much rather look at a presentation and understand the
								message. It has come to a point where images and videos are used
								more to</p>
						</div>
					</div>
					<div class="col-lg-2 col-md-6 col-sm-6">
						<div class="single-footer-widget">
							<h5 class="footer_title">Navigation Links</h5>
							<div class="row">
								<div class="col-5">
									<ul class="list">
										<li><a href="#">Home</a></li>
										<li><a href="#">Features</a></li>
										<li><a href="#">Services</a></li>
										<li><a href="#">Portfolio</a></li>
										<li><a href="#">Registration</a>
									</ul>
								</div>
								<div class="col-5">
									<ul class="list">
										<li><a href="#">Team</a></li>
										<li><a href="#">Pricing</a></li>
										<li><a href="#">Blog</a></li>
										<li><a href="#">contact</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6 col-sm-6">
						<div class="single-footer-widget">
							<h5>Newsletter</h5>
							<p>For business professionals caught between high OEM price
								and mediocre print and graphic output,</p>
							<div class="footer-newsletter" id="mc_embed_signup">
								<form target="_blank" novalidate="true"
									action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01"
									method="get" class="form-inline">
									<div class="d-flex flex-row">
										<input class="form-control" name="EMAIL"
											placeholder="Enter Email" onfocus="this.placeholder = ''"
											onblur="this.placeholder = 'Enter Email '" required=""
											type="email">
										<button class="click-btn btn btn-default">
											<span class="lnr lnr-location" aria-hidden="true"></span>
										</button>
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
					<div class="col-lg-3  col-md-6 col-sm-6">
						<div class="single-footer-widget mail-chimp">
							<h5 class="mb-20">Instragram Feed</h5>
							<ul class="instafeed d-flex flex-wrap">
								<li><img src="img/i1.jpg" alt=""></li>
								<li><img src="img/i2.jpg" alt=""></li>
								<li><img src="img/i3.jpg" alt=""></li>
								<li><img src="img/i4.jpg" alt=""></li>
								<li><img src="img/i5.jpg" alt=""></li>
								<li><img src="img/i6.jpg" alt=""></li>
								<li><img src="img/i7.jpg" alt=""></li>
								<li><img src="img/i8.jpg" alt=""></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="copyright">
			<div class="container">
				<div class="row">
					<div class="col-lg-6 col-md-12">
						<p>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							Copyright &copy;
							<script>
								document.write(new Date().getFullYear());
							</script>
							All rights reserved | This template is made with <i
								class="fa fa-heart-o" aria-hidden="true"></i> by <a
								href="https://colorlib.com" target="_blank">Colorlib</a>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						</p>
					</div>
					<div class="col-lg-6 col-md-12 text-right">
						<div class="social-icons">
							<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
								class="fa fa-twitter"></i></a> <a href="#"><i
								class="fa fa-dribbble"></i></a> <a href="#"><i
								class="fa fa-behance"></i></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!--================ End footer Area =================-->

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