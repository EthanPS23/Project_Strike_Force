

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter out;
	HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		out = response.getWriter();
//		String [] customerArray = new String[12];
//		customerArray[0] = request.getParameter("CustFirstName");
//		customerArray[1] = request.getParameter("CustLastName");
//		customerArray[2] = request.getParameter("CustAddress");
//		customerArray[3] = request.getParameter("Custcity");
//		customerArray[4] = request.getParameter("CustCountry");
//		customerArray[5] = request.getParameter("CustProv");
//		customerArray[6] = request.getParameter("CustPostal");
//		customerArray[7] = request.getParameter("CustHomePhone");
//		customerArray[8] = request.getParameter("CustBusPhone");
//		customerArray[9] = request.getParameter("CustEmail");
//		customerArray[10] = request.getParameter("CustPassword");
//		customerArray[11] = request.getParameter("AgentID");
//		validate(customerArray);
//	}
	
	private String validate(String [] customerData)
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
						return "**Address must have a value!**";						
					
					case 3:
						return "**You must include your city name!**";
						
					case 4:
						return "**You must include your country name!**";
						
					case 5:
						return "**You must include your province or state name!**";
						
					case 6:
						return "**You must include your postal or zip code!**";
						
					case 7:
						return "**Please provide your home phone number!**";
						
					case 8:
						return "**Please provide your business phone number!**";
						
					case 9:
						return "**Please provide your email!**";
						
					case 10:
						return "**Please provide your password!**";
					
					default:
					return "field must have a value.";
				}
			}
		}
		return "";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doPost(request, response);
		out = response.getWriter();
		session = request.getSession();
		String [] customerArray = new String[12];
		customerArray[0] = request.getParameter("CustFirstName");
		customerArray[1] = request.getParameter("CustLastName");
		customerArray[2] = request.getParameter("CustAddress");
		customerArray[3] = request.getParameter("Custcity");
		customerArray[4] = request.getParameter("CustCountry");
		customerArray[5] = request.getParameter("CustProv");
		customerArray[6] = request.getParameter("CustPostal");
		customerArray[7] = request.getParameter("CustHomePhone");
		customerArray[8] = request.getParameter("CustBusPhone");
		customerArray[9] = request.getParameter("CustEmail");
		customerArray[10] = request.getParameter("CustPassword");
		customerArray[11] = request.getParameter("AgentID");
		
		String valid= validate(customerArray);
		if (valid != "") {
			session.setAttribute("message", valid);
			response.sendRedirect("Registration.jsp");
		}else {
			//String msg = createCustomer(customerArray);
			if (!createCustomer(customerArray)) {
				session.setAttribute("message", "I dont know what happened");
				response.sendRedirect("Registration.jsp");
			}else {
				session.setAttribute("message", "**Congrats you are now registered!**");
				response.sendRedirect("Login.jsp");
			}
		}
	}
	public boolean createCustomer(String [] customerArray)
	{
		String sql = "INSERT INTO customers ("
			+ "CustomerID, CustFirstName, CustLastName, CustAddress, CustCity, CustProv, CustCountry, CustPostal, CustHomePhone, CustBusPhone, CustEmail, CustPassword, AgentID"
			+ ") values (last_insert_id(), "
			+ "'" + customerArray[0] + "'," 
			+ " '" + customerArray[1] + "',"
			+ " '" + customerArray[2] + "',"
			+ " '" + customerArray[3] + "',"
			+ " '" + customerArray[5] + "',"
			+ " '" + customerArray[4] + "',"
			+ " '" + customerArray[6] + "',"
			+ " '" + customerArray[7] + "',"
			+ " '" + customerArray[8] + "',"
			+ " '" + customerArray[9] + "',"
			+ " '" + customerArray[10] + "',"
			+ " " + customerArray[11] + ")";
	    try
	    {
			/*
			 * //Class.forName("com.mysql.jdbc.Driver");
			 * //Class.forName("oracle.jdbc.driver.OracleDriver");
			 * Class.forName("org.mariadb.jdbc.Driver"); //Connection dbConn =
			 * DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts",
			 * "root","password"); Connection conn =
			 * DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts",
			 * "harv","password"); //Connection dbConn =
			 * DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orant11g",
			 * "ictoosd", "ictoosd");
			 */
	    	Connection conn = DBConnect.getConnection();
	        Statement stmt = conn.createStatement();

	        int rows = stmt.executeUpdate(sql);

	        // Cleanup
	        conn.close();  // close the connection
		        
			if (rows == 1)
			{
				//return "Your registration was a success. Thank you for joining The Travel Experts.";
				return true;
			}
			else
			{
				//return "You were unsuccessful";
				return false;
			}
	    }
	    catch (Exception excp)
	    {
	        excp.printStackTrace();
	    }
		return false;
	}

}
