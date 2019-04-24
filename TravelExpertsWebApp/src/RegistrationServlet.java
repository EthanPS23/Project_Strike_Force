

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Customer;
//import model.Customer;
import servicerestjava.RegisterRESTService;
//import sun.security.validator.ValidatorException;

/**
 * Servlet implementation class RegistrationServlet
 */
/* User enters in their information in order to register
 * The servlet takes the inputted data and then sends it to the web services
 * where the services inputs the user into the database
 * Author: Ethan Shipley
 * Course CMPP 264
 * Date: April 23 2019
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doPost(request, response);
		out = response.getWriter();
		session = request.getSession();
		Customer cust = new Customer(-1, -1, request.getParameter("CustAddress").trim(), request.getParameter("CustBusPhone").trim(), request.getParameter("CustCity").trim(), 
				request.getParameter("CustCountry").trim(),	request.getParameter("CustEmail").trim(), request.getParameter("CustFirstName").trim(), request.getParameter("CustHomePhone").trim(), 
				request.getParameter("CustLastName").trim(), request.getParameter("CustPassword").trim(), request.getParameter("CustPostal").trim(), request.getParameter("CustProv").trim(), null);
		RegisterRESTService str = new RegisterRESTService();
		String valid = str.register(cust);
		
		//String valid= validate(customerArray);
		if (valid != "true") {
			session.setAttribute("message", valid);
			response.sendRedirect("Registration.jsp");
		}else {
			session.setAttribute("message", "**Congrats you are now registered!**");
			response.sendRedirect("Login.jsp");
		}
	}
	

}
