package SE2.Swimv2.Servlet;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SE2.Swimv2.Session.GestoreLoginRemote;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id;
		try {
			Context jndiContext = new InitialContext();
					
			Object obj = jndiContext.lookup("GestoreLogin/remote");
			GestoreLoginRemote l = (GestoreLoginRemote) obj;
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			id=l.loginUser(email, password);
			
			
			RequestDispatcher disp;
			if(id == -1) {
				request.setAttribute("messaggio", "Accesso Negato");
				disp = request.getRequestDispatcher("index.jsp");
			} else {
				request.getSession().setAttribute("user_id", id);
				disp = request.getRequestDispatcher("User/user.jsp");
			}
			
			disp.forward(request, response);
			
			}catch (NamingException e) {
				e.printStackTrace();
			}
	}
}
