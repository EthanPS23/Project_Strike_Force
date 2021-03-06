<!-- 
	Author: Brandon Ezekiel
	Date: April 23, 2019
	Bookings JSP page that displays selected package and inserts booking into database.
 -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!doctype html>
<html lang="en">

<script src="jquery-3.3.1.js"></script>
<script>

//Ethan Shipley
	
	function getUrlParam(parameter, defaultvalue){
	    var urlparameter = defaultvalue;
	    if(window.location.href.indexOf(parameter) > -1){
	        urlparameter = getUrlVars()[parameter];
	        }
	    return urlparameter;
	}
	function getUrlVars() {
	    var vars = {};
	    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
	        vars[key] = value;
	    });
	    return vars;
	}
	
	
	// Brandon Ezekiel
	
	$(document).ready(function(){
		var pkgId = getUrlParam('param','1');
		//String b = (String)session.getAttribute("custId");
		//session.setAttribute("pkgId", pkgId);
		$.get("/TravelExpertsWebApp/rest/packages/getpackageidweb/" + pkgId,
		function(data){
			
			for (i=0; i<data.length; i++){
				$('#divtoappend').append("<form action=\"BookingServlet\" method=\"post\">" 
						+ "<div class=\"col-lg-12 col-md-10\">"
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
						+ data[i].pkgEndDate.toString().substring(0,12).replace(",","").replace(",","")
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
						+ "<div class=\"col-lg-12 text-center\">"
						+ "<button class=\"primary-btn text-uppercase\">COnfirm NOW!</button>"
						+ "</div>"
						//+ "<a href=\"#\" class=\"primary-btn\" onClick=\"postBook\">Confirm Now!</a>"
						+ "</div>"
						+ "</div>"
						+ "</form>"
						);
				$('#divtoappend').niceSelect('update');						
			}
		},
		"json");
})			
</script>
<%
	session.setAttribute("pkgId", request.getParameter("param"));
%>

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

<%
	String packId = request.getParameter("param");
	session.setAttribute("packageId", packId);
	//String packId = (String)request.getSession().getAttribute("packageId");
%>

	<!-- Header area Include statement -->
	<jsp:include page="header.jsp" />

	<!--================Home Banner Area =================-->
	<section class="banner_area ">
		<div class="banner_inner overlay d-flex align-items-center">
			<div class="container">
				<div class="banner_content">
					<div class="page_link">
						<a href="index.jsp">Home</a> <a href="book-trip.jsp">Confirmation</a>
					</div>
					<h2>Confirm your Trip Details</h2>
				</div>
			</div>
		</div>
	</section>
	<!--================End Home Banner Area =================-->

	<!--================ Start Book a Trip Area =================-->
	<section class="package-area section_gap_top">
		<div class="container">
			<div class="row justify-content-center">
				<div id="divtoappend"></div>
					<%
						String b = (String)session.getAttribute("loggedin");
						if (b !="true"){
							response.sendRedirect("Login.jsp");
						}						
					%>
				
			
			
					<%-- <div class="col-lg-4 col-md-6">
						<h3>Confirm your Trip Details!</h3>
						<br>
						<div class="single-package" style="border: 0.1em soild black;" id="myCard" action="BookServlet" method="post">
							<div class="thumb">
								<img class="img-fluid" src="img/package/p1.jpg">
							</div>
							<p class="date">
								<span>18</span>
								<br>
								December
							</p>
							<div class="meta-top d-flex">
								<p class="ml-20">
									<span class="fa fa-calendar">
										
									</span>
									<%
									String pkgSDate = (String) session.getAttribute("pkgId");
									if(pkgSDate!=null) { 
								         request.getSession().setAttribute("pkgStartDate",pkgSDate);
								   	}
									%>
									Start Date
								</p>
								
							</div>
							<p class="ml-20">
								<span class="fa fa-calendar">
										
									</span>
									<%
									String pkgEDate = (String) session.getAttribute("pkgId");
									if(pkgEDate!=null) { 
								         request.getSession().setAttribute("pkgEndDate",pkgEDate);
								   	}
									%>
									End Date
								</p>
							<h4 class="ml-20">
							<%
							String pkgName = (String) session.getAttribute("pkgId");
							if(pkgName!=null) { 
						         request.getSession().setAttribute("pkgName",pkgName);
						   	}
							%>
							Package Name
							</h4>
							<p class="ml-20">
							<%
							String pkgDesc = (String) session.getAttribute("pkgId");
							if(pkgDesc!=null) { 
						         request.getSession().setAttribute("pkgDesc",pkgDesc);
						   	}
							%>
								Description
							</p>
							<p class="ml-20">
							<%
							String pkgBasePrice = (String) session.getAttribute("pkgId");
							if(pkgBasePrice!=null) { 
						         request.getSession().setAttribute("pkgBasePrice",pkgDesc);
						   	}
							%>
								Price
							</p>
							<button class="primary-btn text-uppercase" onClick="postbook()">Click here to confirm booking!</button>
						</div>
					</div> --%>
				
				
				
				
				<!--  
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
					-->
				
				
				
				
				
				
				
				
				
				</div>
			</div>
		<!--  </div> -->
	</section>
	<!--================ End Book a Trip Area =================-->

	<!-- Footer area Include statement -->
	<jsp:include page="footer.jsp" />

	 <!-- Optional JavaScript -->
	<!--jQuery first, then Popper.js, then Bootstrap JS-->
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
	<!--gmaps Js -->
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
	<script src="js/gmaps.min.js"></script>
	<script src="js/theme.js"></script> 
</body>

</html>