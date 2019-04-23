package servicerestjava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import security.BCrypt;
import security.PasswordEncyption;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;


import model.Booking;

@Path("/Booking")
public class BookingRESTService {
	
	@POST
	@Path("/booking")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String booking(@FormParam("BookingDate") Date bookingDate, @FormParam("BookingNo") int bookingNo, @FormParam("TravelerCount") int TravelerCount,
			@FormParam("CustomerId") int customerId, @FormParam("TripTypeId")  String tripTypeId, @FormParam("PackageId") int packageId)
	{
		String result="false";
		Date d1 = new Date(); 
		Booking book = new Booking(-1, d1, "", customerId, packageId, -1, "");
		
		return "true";
	}
}
