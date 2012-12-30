package SE2.Swimv2.Servlet;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Session.GestoreLoginRemote;
import SE2.Swimv2.Session.GestoreUserRemote;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String USER_PAGE = "User/user.jsp";
	private static final String USER_ID= "userId";
	private static final String USER= "user";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

			if(request.getAttribute(USER)==null){

				try {
					GestoreUserRemote gestoreUser = getGestoreUserRemote();
					Long id= (Long)request.getSession().getAttribute(USER_ID);
					User user= gestoreUser.getById(id.longValue());
					request.setAttribute(USER, user);
				} catch (NamingException e) {

				}
				
				request.getRequestDispatcher(USER_PAGE).forward(request, response);
				
			}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	
	private GestoreUserRemote getGestoreUserRemote() throws NamingException{
		Context jndiContext = new InitialContext();
		Object obj = jndiContext.lookup("GestoreUser/remote");
		GestoreUserRemote manager = (GestoreUserRemote) obj;
		return manager;
	}
}