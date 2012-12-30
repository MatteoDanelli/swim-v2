package SE2.Swimv2.Servlet;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SE2.Swimv2.Exceptions.LoginException;
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
		long id = 0;
		try {
			Context jndiContext = new InitialContext();
					
			Object obj = jndiContext.lookup("GestoreLogin/remote");
			GestoreLoginRemote l = (GestoreLoginRemote) obj;
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			try {
				id=l.loginUser(email, password);
			} catch (LoginException e) {
				e.printStackTrace();
			}
			
			if(id == -1) {
				request.setAttribute("messaggio", "Accesso Negato");
				response.sendRedirect("index.jsp?loginError=1");
			} else {
				request.getSession().setAttribute("user_id", id);
				response.sendRedirect("User/user.jsp");
			}
		
			}catch (NamingException e) {
				e.printStackTrace();
			}
	}
}
