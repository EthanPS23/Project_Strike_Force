package servicerestjava;
import java.sql.Connection;
import security.PasswordEncyption;
import security.BCrypt;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
// Author: Christopher Potvin
@Path("/Login")
public class LoginRESTService {
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String login (@FormParam("CustEmail") String email,@FormParam("CustPassword") String password)
	{
	
	  String result="false";
	  
	  try {
		  Connection conn = DBConnect.getConnection();
		  
		  String sql = "select * from customers where CustEmail=? and CustPassword=?";
		  
		  PreparedStatement stmt = conn.prepareStatement(sql);
		  stmt.setString(1, email);
		  stmt.setString(2, password);
		  
		  ResultSet rs = stmt.executeQuery();
		  
		  if(rs.next()) {
			  result = "true";
		  }
		  
		  conn.close();
	  }
	  catch (Exception e)
	  {
		  e.printStackTrace();
	  }
	  return result;
	}
}
