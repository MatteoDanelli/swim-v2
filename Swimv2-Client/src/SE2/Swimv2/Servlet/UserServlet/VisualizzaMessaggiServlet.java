package SE2.Swimv2.Servlet.UserServlet;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SE2.Swimv2.Entity.Messaggio;
import SE2.Swimv2.Exceptions.MessaggiException;
import SE2.Swimv2.Session.GestoreMessaggiRemote;
import SE2.Swimv2.Util.RemoteManager;

/**
 * Servlet implementation class VisualizzaMessaggiServlet
 */
public class VisualizzaMessaggiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//nomi attributi
	private static final String ERROR = "Errore";
	private static final String MESSAGGI= "elencoMessaggi";
	private static final String USER_ID= "userId";
	
	//valori attributi
	private static final String LOGIN_ERROR= "logError";

	//nomi parametri
	private static final String ID_MEX="idMex";
	
	//nomi pagine
	private static final String HOME_PAGE = "index.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	private static final String ELENCO_MESSAGGI = "User/elencoMessaggi.jsp";
	

	private RemoteManager remoteManager= new RemoteManager();
	private GestoreMessaggiRemote gestoreMessaggi;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaMessaggiServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		
		try {
			gestoreMessaggi = remoteManager.getGestoreMessaggiRemote();
		} catch (NamingException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}	
		
		String idMessaggio= request.getParameter(ID_MEX);
	
		if(idMessaggio!=null){
			long idMex;
			try{
				idMex=Long.parseLong(idMessaggio);
			}catch (NumberFormatException e) {
				response.sendRedirect(ERROR_PAGE);
				return;
			}
			
			try {
				gestoreMessaggi.settaMessaggioLetto(idMex);
			} catch (MessaggiException e) {
				response.sendRedirect(ERROR_PAGE);
				return;
			}
			
			
		}else{
			//Setto lista messaggi
			List<Messaggio> Messaggi= gestoreMessaggi.elencoMessaggi(id);
			request.setAttribute(MESSAGGI, Messaggi);

			request.getRequestDispatcher(ELENCO_MESSAGGI).forward(request, response);			
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
