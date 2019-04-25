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

/* Posts the booking when customer confirms package.
 * Author: Brandon Ezekiel / Ethan Shipley
 * Date: April 23 2019
 */

@Path("/Booking")
public class BookingRESTService {
	
	@POST
	@Path("/booking")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	/*public String booking(@FormParam("BookingDate") Date bookingDate, @FormParam("BookingNo") int bookingNo, @FormParam("TravelerCount") int TravelerCount,
			@FormParam("CustomerId") int customerId, @FormParam("TripTypeId")  String tripTypeId, @FormParam("PackageId") int packageId)*/
	public String booking(@FormParam("CustomerId") int customerId, @FormParam("PackageId") int packageId)
	{
		String result="false";
		Date d1 = new Date(); 
		Booking book = new Booking(-1, d1, "", customerId, packageId, -1, "");
		
		int x = 0;
		
		try
		{
			Connection conn = DBConnect.getConnection();
			
			String sql = "INSERT INTO bookings (bookingId, customerId, tripTypeId, packageId) "
					+ "values (last_insert_id(),?,?,?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, book.getCustomerId());
			stmt.setString(2, "B");
			stmt.setInt(3, book.getPackageId());
			
			x = stmt.executeUpdate();

			if(x==1){
				result = "true";
			}

			conn.close();
			
			return result;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		return result;
	}
}
