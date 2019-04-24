import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Customer;
import servicerestjava.LoginRESTService;
import servicerestjava.RegisterRESTService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	PrintWriter out;
	HttpSession session;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		out = response.getWriter();
		session = request.getSession();
//		String userid = request.getParameter("CustEmail");
//		String password = request.getParameter("CustPassword");
		
		Customer cust = new Customer(-1, -1, "", "", "", "", request.getParameter("CustEmail").trim(), "", "", "", request.getParameter("CustPassword").trim(), "", "", null);
		LoginRESTService lrs = new LoginRESTService();
		if (lrs.login(cust)) {
			session.setAttribute("loggedin", "true");
			response.sendRedirect("index.jsp");
		}else {
			session.setAttribute("message", "Incorrect Login");
			log("user: " + cust.getCustEmail() + " password: " + cust.getCustPassword());
			response.sendRedirect("Login.jsp");
		}
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
//	private boolean verify(String userid, String password) {
//		try {
//			Connection conn = DBConnect.getConnection();
//			String sql = "select CustPassword from customers where CustEmail=?";
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			stmt.setString(1,userid);
//			ResultSet rs = stmt.executeQuery();
//			
//			if(rs.next()) {
//				String pwd = rs.getString(1);
//				if(pwd.equals(password)) {
//					//login is okay
//					return true;
//				}
//			}else {
//				return false;
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	
//	}
	
	
	
	

}
