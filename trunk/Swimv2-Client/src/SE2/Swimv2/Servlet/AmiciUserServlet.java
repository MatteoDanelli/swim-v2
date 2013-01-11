package SE2.Swimv2.Servlet;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Session.GestoreAmiciRemote;
import SE2.Swimv2.Util.RemoteManager;

/**
 * Servlet implementation class AmiciUserServlet
 */
public class AmiciUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//nomi attributi
	private static final String ERROR = "Errore";
	private static final String MESSAGE = "Messaggio";
	private static final String RISULTATI_RICERCA= "RisultatiRicerca";
	
	//valori attributi
	private static final String LOGIN_ERROR= "logError";
	private static final String USER_ID= "userId";
	private static final String NESSUN_AMICO= "Non hai nessun amico";
	
	//nomi pagine
	private static final String ERROR_PAGE = "error.jsp";
	private static final String HOME_PAGE = "index.jsp";
	private static final String USER_AMICI = "User/amici.jsp";
	
	private RemoteManager remoteManager= new RemoteManager();
	private GestoreAmiciRemote gestoreAmici;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmiciUserServlet() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//se non esiste una sessione richiamo l' home page
		Long id= (Long) request.getSession().getAttribute(USER_ID);
		if(id==null){
			request.setAttribute(ERROR, LOGIN_ERROR);
			request.getRequestDispatcher(HOME_PAGE).forward(request, response);
			return;
		}
		
		try {
			gestoreAmici= remoteManager.getGestoreAmiciRemote();
		} catch (NamingException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}
		
		List<User> amici= gestoreAmici.elencoAmici(id);
		
		request.setAttribute(RISULTATI_RICERCA, amici);
		if(amici.size()==0){
			request.setAttribute(MESSAGE, NESSUN_AMICO);
		}
		
		request.getRequestDispatcher(USER_AMICI).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
