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
import SE2.Swimv2.Session.GestoreRichiesteAmiciziaRemote;
import SE2.Swimv2.Session.GestoreUserRemote;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//nomi attributi
	private static final String ERROR = "Errore";
	private static final String USER= "user";
	private static final String RIC_AMICIZIA= "richiesteAmicizia";
	
	//valori attributi
	private static final String LOGIN_ERROR= "logError";
	private static final String USER_ID= "userId";

	
	//nomi pagine
	private static final String HOME_PAGE = "index.jsp";
	private static final String USER_PAGE = "User/user.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	


	
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
		
			Long id= (Long) request.getSession().getAttribute(USER_ID);
			GestoreUserRemote gestoreUser;
			GestoreRichiesteAmiciziaRemote gestoreRichiesteAmicizia;
			
			//se non esiste una sessione richiamo l' home page
			if(id==null){
				request.setAttribute(ERROR, LOGIN_ERROR);
				request.getRequestDispatcher(HOME_PAGE).forward(request, response);
				return;
			}

			if(request.getAttribute(USER)==null){
			
				try {
					gestoreUser = this.getGestoreUserRemote();
					gestoreRichiesteAmicizia = this.getGestoreRichiesteAmiciziaRemote();
				} catch (NamingException e) {
					response.sendRedirect(ERROR_PAGE);
					return;
				}					
					
					User user= gestoreUser.getById(id.longValue());
					request.setAttribute(USER, user);
					Integer numRichiesteAmicizia= gestoreRichiesteAmicizia.numeroDiNuoveRichieste(id);
					request.setAttribute(RIC_AMICIZIA, numRichiesteAmicizia);
			}
			
			request.getRequestDispatcher(USER_PAGE).forward(request, response);
			
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
	
	private GestoreRichiesteAmiciziaRemote getGestoreRichiesteAmiciziaRemote() throws NamingException{
		Context jndiContext = new InitialContext();
		Object obj = jndiContext.lookup("GestoreRichiesteAmicizia/remote");
		GestoreRichiesteAmiciziaRemote manager = (GestoreRichiesteAmiciziaRemote) obj;
		return manager;
	}

}
