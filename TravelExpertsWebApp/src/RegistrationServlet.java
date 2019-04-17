

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter out;
       
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
						break;						
					
					case 3:
						return "**You must include your city name!**";
						
					case 4:
						return "**You must include your province or state name!**";
						
					case 5:
						return "**You must include your country name!**";
						
					case 6:
						return "**You must include your postal or zip code!**";
						
					case 7:
						return "**Please provide your home phone number!**";
						
					case 8:
						break;
						
					case 9:

						break;
					
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
		out.print("<!doctype html>");
		out.print("<html>");
		out.print("<head><title>U.S Miles per gellon </title></head>");
		out.print("version 2<body>");
		out.print("" + customerArray[0] + "");
		out.print("<h1>" + customerArray[1] + "</h1>");
		out.print("<h1>" + customerArray[2] + "</h1>");
		out.print("<h1>" + customerArray[3] + "</h1>");
		out.print("<h1>" + customerArray[4] + "</h1>");
		out.print("<h1>" + customerArray[5] + "</h1>");
		out.print("<h1>" + customerArray[6] + "</h1>");
		out.print("<h1>" + customerArray[7] + "</h1>");
		out.print("<h1>" + customerArray[8] + "</h1>");
		out.print("<h1>" + customerArray[9] + "</h1>");
		out.print("<h1>" + customerArray[10] + "</h1>");
		out.print("<h1>" + customerArray[11] + "</h1>");
		out.print("<a href='converter.html'>Convert ANother on</a>");
		out.print("</body>");
		out.print("</html>");
		
		validate(customerArray);
	}

}
