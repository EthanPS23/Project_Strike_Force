

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Booking;
import model.Customer;
import model.Package;

import servicerestjava.BookingRESTService;
import servicerestjava.packageRESTservice;


/**
 * Servlet implementation class BookingServlet
 */

 
@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter out;
	HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doPost(request, response);
		out = response.getWriter();
		session = request.getSession();
		int customerId =Integer.parseInt((String)session.getAttribute("custId")) ;
		String pkgId = (String) session.getAttribute("packageId");
		int packageId = Integer.parseInt(pkgId);
		//int packageId =Integer.parseInt(request.getParameter("packageId"));
		//Date d1 = new Date();
		//Booking book = new Booking(-1, d1, "", Integer.parseInt(request.getParameter("customerId")), Integer.parseInt(request.getParameter("packageId")), -1, "");
		BookingRESTService brs = new BookingRESTService();
		brs.booking(customerId, packageId);
		if(brs.booking(customerId, packageId).equals("true")) {
			session.setAttribute("message", "Package booked!");
			response.sendRedirect("index.jsp");	
		}
		else {
			session.setAttribute("message", "Error booking, are you logged in.");
			response.sendRedirect("packages.jsp");	
		}
		//brs.booking(customerId, packageId)
		//brs.booking(book.getCustomerId(), book.getPackageId());
		
		
		
		
	}

}
