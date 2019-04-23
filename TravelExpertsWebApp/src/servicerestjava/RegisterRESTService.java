package servicerestjava;
import java.sql.Connection;
import java.sql.PreparedStatement;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Register")
public class RegisterRESTService {
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String register(@FormParam("CustFirstName") String custFirstName, @FormParam("CustLastName") String custLastName, @FormParam("CustAddress") String custAddress,
			@FormParam("CustCity") String custCity, @FormParam("CustProv") String custProv, @FormParam("CustCountry") String custCountry, @FormParam("CustPostal") String custPostal,
			@FormParam("CustHomePhone") String custHomePhone, @FormParam("CustBusPhone") String custBusPhone, @FormParam("CustEmail") String custEmail, @FormParam("CustPassword") String custPassword){
		String result="false";
		int x = 0;
		
		try{
		    Connection conn = DBConnect.getConnection();
		
		    String sql = "INSERT INTO customers (CustomerID, CustFirstName, CustLastName, CustAddress, CustCity, CustProv, CustCountry, CustPostal, CustHomePhone, CustBusPhone, CustEmail, CustPassword) "
		    		+ "values (last_insert_id(),?,?,?,?,?,?,?,?,?,?,?)";
		    
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    
			stmt.setString(1, custFirstName);
			stmt.setString(2, custLastName);
			stmt.setString(3, custAddress);
			stmt.setString(4, custCity);
			stmt.setString(5, custProv);
			stmt.setString(6, custCountry);
			stmt.setString(7, custPostal);
			stmt.setString(8, custHomePhone);
			stmt.setString(9, custBusPhone);
			stmt.setString(10, custEmail);
			stmt.setString(11, custPassword);
			
			x = stmt.executeUpdate();
			
			if(x==1){
				result = "true";
			}
			
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
}
