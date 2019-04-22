import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/LoginRESTService")
public class LoginRESTService {

	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String login (@FormParam("CustEmail") String email,@FormParam("CustPassword") String password)
	{
	  String result="false";
	  
	  try {
          Class.forName("org.mariadb.jdbc.Driver");

		  Connection conn = DriverManager.getConnection("jdbc:mysql://10.163.37.7:3306/travelexperts", "harv", "password");
		  
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
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String register(@FormParam("CustEmail") String email, @FormParam("CustPassword") String password){
		String result="false";
		int x = 0;
		
		try{
		    Connection conn = DBConnect.getConnection();
		
			PreparedStatement stmt = conn.prepareStatement("insert into customers(CustEmail, CustPassword) values(?,?)");
			stmt.setString(1, email);
			stmt.setString(2, password);
			
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
