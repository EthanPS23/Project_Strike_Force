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

import model.Customer;

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
		Customer cust = new Customer(-1, -1, custAddress, custBusPhone, custCity, custCountry, custEmail, custFirstName, custHomePhone, custLastName, custPassword, custPostal, custProv, null);
		int x = 0;
		try{
		    Connection conn = DBConnect.getConnection();

		    String sql = "INSERT INTO customers (CustomerID, CustFirstName, CustLastName, CustAddress, CustCity, CustProv, CustCountry, CustPostal, CustHomePhone, CustBusPhone, CustEmail, CustPassword) "
		    		+ "values (last_insert_id(),?,?,?,?,?,?,?,?,?,?,?)";

		    PreparedStatement stmt = conn.prepareStatement(sql);
		    String valids = validator(cust);
		    if (valids.equals("true")){
		    	stmt.setString(1, cust.getCustFirstName());
				stmt.setString(2, cust.getCustLastName());
				stmt.setString(3, cust.getCustAddress());
				stmt.setString(4, cust.getCustCity());
				stmt.setString(5, cust.getCustProv());
				stmt.setString(6, cust.getCustCountry());
				stmt.setString(7, cust.getCustPostal());
				stmt.setString(8, cust.getCustHomePhone());
				stmt.setString(9, cust.getCustBusPhone());
				stmt.setString(10, cust.getCustEmail());
				stmt.setString(11, cust.getCustPassword());
		    }
		    else {
		    	return valids;
		    }


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
			// this is the JBcrypt class that is hashing the Customer Password
			// PasswordEncryption.hashedPassword(custPassword)
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

	private String validator(Customer cust) {
		String pattern = "(?=^.{8,32}$)((?=.*\\d)(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[^A-Za-z0-9])(?=.*[a-z])|(?=.*[^A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))^.*";
		String phone = "^\d{10}";
		if ((cust.getCustFirstName().length() > 25) || (cust.getCustLastName().length() > 25)) {
			return "Name is too long";
		}
		if ((cust.getCustFirstName().length() == 0) || (cust.getCustLastName().length() == 0)) {
			return "Name cannot be empty";
		}
		if (cust.getCustAddress().length() > 75) {
			return "Address is too long";
		}
		if (cust.getCustAddress().length() == 0) {
			return "Address cannot be empty";
		}
		if (cust.getCustCity().length() > 50) {
			return "City is too long";
		}
		if (cust.getCustCity().length() == 0) {
			return "City cannot be empty";
		}
		if (cust.getCustProv().length() > 2) {
			return "Province/state is too long";
		}
		if (cust.getCustProv().length() == 0) {
			return "Province/state cannot be empty";
		}
		if (cust.getCustCountry().length() > 2) {
			return "Country is too long";
		}
		if (cust.getCustCountry().length() == 0) {
			return "Country cannot be empty";
		}
		if (!cust.getCustPassword().matches(pattern)) {
			return "Passowrd must contain at 1 upper case, 1 lower case, 1 number, be between 8 and 32 characters and 1 special character";
		}
		if (!cust.getCustHomePhone().matches("\d{10}")) {

		}

		return "true";
	}
}
