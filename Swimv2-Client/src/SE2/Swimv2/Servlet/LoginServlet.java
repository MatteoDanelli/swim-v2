package SE2.Swimv2.Servlet;

import java.io.IOException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SE2.Swimv2.Exceptions.LoginException;
import SE2.Swimv2.Session.GestoreLoginRemote;
import SE2.Swimv2.Util.RemoteManager;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//nomi attributi
	private static final String ERROR = "Errore";
	private static final String MESSAGE = "Messaggio";
	
	//valori attributi
	private static final String LOGIN_ERROR= "logError";
	private static final String USER_ID= "userId";
	private static final String ADMIN_ID= "adminId";
	private static final String EMAIL= "email";
	private static final String PSW= "password";
	
	//nomi Paramentri
	private static final String LOG_TYPE= "type";
	
	//valori Paramentri
	private static final String ADMIN= "admin";
	private static final String USER= "user";
	
	//nomi pagine
	private static final String HOME_PAGE = "index.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	//servlet
	private static final String USER_SERVLET = "UserServlet";

	
	private RemoteManager remoteManager = new RemoteManager();
	private GestoreLoginRemote gestoreLogin;
	
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
		response.sendRedirect(HOME_PAGE);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			gestoreLogin = remoteManager.getGestoreLoginRemote();
		}
		catch (NamingException e) {
				response.sendRedirect(ERROR_PAGE);
				return;
		}
		
		String type= request.getParameter(LOG_TYPE);
		if(type.equals(USER)){
			loginUser(request, response);
		}else if(type.equals(ADMIN)){
			loginAdmin(request, response);
		}else{
			response.sendRedirect(HOME_PAGE);
		}

	}
	
	private void loginUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		long id;
		String email = request.getParameter(EMAIL);
		String password = request.getParameter(PSW);
		
			try{
				id=gestoreLogin.loginUser(email, password);
				request.getSession().setAttribute(USER_ID, id);
				response.sendRedirect(USER_SERVLET);
			}
			catch (LoginException e) {
				request.setAttribute(ERROR, LOGIN_ERROR);
				request.getRequestDispatcher(HOME_PAGE).forward(request, response);
			}
	}
	
	private void loginAdmin(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

	}

}
