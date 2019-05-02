/** 
 * Author: Chris Potvin
 * About: This is the customer login page where the REST service corresponds to the details
 * to the Android application. Only worked on the Login page and the encryption 
 * that was not used because I could not get it to properly check against the DB. 
 *
 * Date: May 1st, 2019
 */

package servicerestjava;
import java.lang.reflect.Type;
import java.sql.Connection;
import security.PasswordEncyption;
import security.BCrypt;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
// Author: Christopher Potvin

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Customer;
import model.Package;


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

	@POST
	@Path("/getcustomerid")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	//@Produces(MediaType.APPLICATION_JSON)
	public String custId(@FormParam("CustEmail") String email,@FormParam("CustPassword") String password)
	{
		String result = "false";
		System.out.println(email + password);

		try {
			Connection conn = DBConnect.getConnection();

			String sql = "select customerId from customers where CustEmail=? and CustPassword=?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, password);
			//stmt.setString(2, PasswordEncyption.hashPassword(password));

			ResultSet rs = stmt.executeQuery();

			if(rs.next()) {
				result = rs.getString("customerId");
			}

			conn.close();
		  }
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean login (Customer cust)
	{

	  boolean result = false;
	  System.out.println(cust.getCustEmail());

	  try {
		  Connection conn = DBConnect.getConnection();

		  String sql = "select * from customers where CustEmail=? and CustPassword=?";

		  PreparedStatement stmt = conn.prepareStatement(sql);
		  stmt.setString(1, cust.getCustEmail());
		  stmt.setString(2, cust.getCustPassword());
		  //stmt.setString(2, PasswordEncyption.hashPassword(password));

		  ResultSet rs = stmt.executeQuery();

		  if(rs.next()) {
			  result = true;
		  }

		  conn.close();
	  }
	  catch (Exception e)
	  {
		  e.printStackTrace();
	  }
	  return result;
	}

	// Ethan Shipley
	public String custId(Customer cust)
	{
		String result = "false";
		System.out.println(cust.getCustEmail());

		try {
			Connection conn = DBConnect.getConnection();

			String sql = "select customerId from customers where CustEmail=? and CustPassword=?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, cust.getCustEmail());
			stmt.setString(2, cust.getCustPassword());
			//stmt.setString(2, PasswordEncyption.hashPassword(password));

			ResultSet rs = stmt.executeQuery();

			if(rs.next()) {
				result = rs.getString("customerId");
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
