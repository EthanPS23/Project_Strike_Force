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


/** Take the inputted registation data, validates it and the inputs it into the database
 * Based on the validation error a corresponding error message is passed,
 * the web service is made to work for the website and android application
 * Author: Ethan Shipley
 * Course CMPP 264
 * Date: April 23 2019
 */
@Path("/Register")
public class RegisterRESTService {

	/**Chris Potvin
	 * This register method is designed to enter the customers data into the database based on variables sent by the android application
	 * 
	 */
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String register(@FormParam("CustFirstName") String custFirstName, @FormParam("CustLastName") String custLastName, @FormParam("CustAddress") String custAddress,
			@FormParam("CustCity") String custCity, @FormParam("CustProv") String custProv, @FormParam("CustCountry") String custCountry, @FormParam("CustPostal") String custPostal,
			@FormParam("CustHomePhone") String custHomePhone, @FormParam("CustBusPhone") String custBusPhone, @FormParam("CustEmail") String custEmail, @FormParam("CustPassword") String custPassword){
		String result="false";
		Customer cust = new Customer(-1, -1, custAddress.trim(), custBusPhone.trim(), custCity.trim(), custCountry.trim(), custEmail.trim(), custFirstName.trim(), custHomePhone.trim(), custLastName.trim(), 
				custPassword.trim(), custPostal.trim(), custProv.trim(), null);
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
	
	/**Ethan Shipley
	 * This register method is designed to enter the customers data into the database based on variables sent by the web application
	 * This overloads the android register method
	 */
	public String register(Customer cust){
		String result="false";
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

	/**Ethan Shipley
	 * Validates the customer information before entering the info into the database
	 */
	public String validator(Customer cust) {
		String pattern = "(?=^.{8,32}$)((?=.*\\d)(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[^A-Za-z0-9])(?=.*[a-z])|(?=.*[^A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))^.*";
		String emailpat = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
		String postpat = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";
		if ((cust.getCustFirstName().length() > 25) || (cust.getCustLastName().length() > 25)) {
			return "Name is too long. Less then 25";
		}
		if ((cust.getCustFirstName().length() == 0) || (cust.getCustLastName().length() == 0)) {
			return "Name cannot be empty";
		}
		if (cust.getCustAddress().length() > 75) {
			return "Address is too long. Less then 75";
		}
		if (cust.getCustAddress().length() == 0) {
			return "Address cannot be empty";
		}
		if (cust.getCustCity().length() > 50) {
			return "City is too long. Less then 50";
		}
		if (cust.getCustCity().length() == 0) {
			return "City cannot be empty";
		}
		if (cust.getCustProv().length() > 2) {
			return "Province/state is too long. Two character code";
		}
		if (cust.getCustProv().length() == 0) {
			return "Province/state cannot be empty";
		}
		if (cust.getCustCountry().length() > 2) {
			return "Country is too long. Two character code";
		}
		if (cust.getCustCountry().length() == 0) {
			return "Country cannot be empty";
		}
		if (cust.getCustPassword().length() == 0) {
			return "Empty password";
		}
		if (!cust.getCustPassword().matches(pattern)) {
			return "Passowrd must contain at 1 upper case, 1 lower case, 1 number, be between 8 and 32 characters and 1 special character";
		}
		if ((cust.getCustBusPhone().length() == 0) || (cust.getCustHomePhone().length() == 0)) {
			return "Empty phone number";
		}
		if (!cust.getCustHomePhone().matches("\\d{10}") || !cust.getCustBusPhone().matches("\\d{10}")) {
			return "Phone format 1234567890";
		}
		if(cust.getCustEmail().length() == 0) {
			return "Empty email";
		}
		if (!cust.getCustEmail().matches(emailpat)) {
			return "Incorrect email format";
		}
		if(cust.getCustEmail().length() > 50) {
			return "Email must be less then 50 characters";
		}
		if (cust.getCustCountry().equals("CA")) {
			if(cust.getCustPostal().length() == 0) {
				return "Empty post code";
			}
			if(!cust.getCustPostal().matches(postpat)) {
				return "Please enter post code of format A1A 1A1";
			}
		}
		else if (cust.getCustCountry().equals("US")) {
			if(cust.getCustPostal().length() == 0) {
				return "Empty post code";
			}
			if(!cust.getCustPostal().matches("\\d{5}")) {
				return "Please enter post code of format 90210";
			}
		}
		

		return "true";
	}
}
