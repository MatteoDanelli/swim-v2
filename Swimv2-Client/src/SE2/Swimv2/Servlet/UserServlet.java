package SE2.Swimv2.Servlet;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Session.GestoreRichiesteAmiciziaRemote;
import SE2.Swimv2.Session.GestoreUserRemote;
import SE2.Swimv2.Util.RemoteManager;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//nomi attributi
	private static final String ERROR = "Errore";
	private static final String USER= "user";
	private static final String RIC_AMICIZIA= "richiesteAmicizia";
	private static final String USER_ID= "userId";
	
	//valori attributi
	private static final String LOGIN_ERROR= "logError";
	
	//nomi pagine
	private static final String HOME_PAGE = "index.jsp";
	private static final String USER_PAGE = "User/user.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	

	private RemoteManager remoteManager= new RemoteManager();
	private GestoreUserRemote gestoreUser;
	private GestoreRichiesteAmiciziaRemote gestoreRichiesteAmicizia;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//se non esiste una sessione richiamo l'home page
		Long id= (Long) request.getSession().getAttribute(USER_ID);
		if(id==null){
			request.setAttribute(ERROR, LOGIN_ERROR);
			request.getRequestDispatcher(HOME_PAGE).forward(request, response);
			return;
		}

		//SE NON È PRESENTE L'ATTRIBUTO USER, LO CREO
		//imposto gli attributi messaggi/richieste aiuto/richieste messaggi
		if(request.getAttribute(USER)==null){
		
			try {
				gestoreUser = remoteManager.getGestoreUserRemote();
				gestoreRichiesteAmicizia = remoteManager.getGestoreRichiesteAmiciziaRemote();
			} catch (NamingException e) {
				response.sendRedirect(ERROR_PAGE);
				return;
			}					
			//SETTO ATTRIBUTO USER	
			User user= gestoreUser.getById(id.longValue());
			request.setAttribute(USER, user);
			
			//Setto attributo numero nuove richieste amicizia
			Integer numRichiesteAmicizia= gestoreRichiesteAmicizia.numeroDiNuoveRichieste(id);
			request.setAttribute(RIC_AMICIZIA, numRichiesteAmicizia);
		}
		
		request.getRequestDispatcher(USER_PAGE).forward(request, response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
}
