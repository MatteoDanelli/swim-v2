package SE2.Swimv2.Servlet.UserServlet;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Exceptions.MessaggiException;
import SE2.Swimv2.Session.GestoreMessaggiRemote;
import SE2.Swimv2.Session.GestoreUserRemote;
import SE2.Swimv2.Util.RemoteManager;

/**
 * Servlet implementation class InviaMessaggiServlet
 */
public class MessaggiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//nomi parametri
	private static final String USER_PROFILE = "userId";
	private static final String ERROR = "Errore";
	private static final String MESSAGGIO = "messaggio";
	private static final String DESTINATARIO = "destinatario";
	private static final String RISPOSTA="risposta";
	
	//valori Parametri
	private static final String RISPOSTA_RICHIESTA="ric";
	private static final String RISPOSTA_MESSAGGIO="mex";
	
	//nomi attributi
	private static final String USER= "user";
	
	//valori attributi
	private static final String LOGIN_ERROR= "logError";
	private static final String USER_ID= "userId";
	
	//nomi pagine
	private static final String HOME_PAGE = "index.jsp";
	private static final String USER_INVIA_MESSAGGI = "User/inviaMessaggi.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	//servlet
	private static final String PROFILO_SERVLET = "/Swimv2-Client/ProfiloServlet"; 
	private static final String VISUALIZZA_MESSAGGI_SERVLET = "/Swimv2-Client/VisualizzaMessaggiServlet";
	private static final String VISUALIZZA_RICHIESTE_SERVLET = "/Swimv2-Client/VisualizzaRichiesteAiutoServlet";
	
	private RemoteManager remoteManager= new RemoteManager();
	private GestoreMessaggiRemote gestoreMessaggi;
	private GestoreUserRemote gestoreUser;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessaggiServlet() {
        super();
        // TODO Auto-generated constructor stub
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
			gestoreUser = remoteManager.getGestoreUserRemote();
		} catch (NamingException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}
		
		//SETTO ATTRIBUTO USER	
		String destinatario= request.getParameter(DESTINATARIO);
		if(destinatario==null){
			response.sendRedirect(ERROR_PAGE);
			return;
		}
		
		long dest;
		try{
			dest = Long.parseLong(destinatario);
		}catch(NumberFormatException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}
		
		User user= gestoreUser.getById(dest);
		request.setAttribute(USER, user);
		request.getRequestDispatcher(USER_INVIA_MESSAGGI).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//se non esiste una sessione richiamo l' home page
		Long id= (Long) request.getSession().getAttribute(USER_ID);
		if(id==null){
			request.setAttribute(ERROR, LOGIN_ERROR);
			request.getRequestDispatcher(HOME_PAGE).forward(request, response);
			return;
		}
		
		String mex= request.getParameter(MESSAGGIO);
		String destinatario= request.getParameter(DESTINATARIO);
		
		try {
			gestoreMessaggi = remoteManager.getGestoreMessaggiRemote();
		} catch (NamingException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}
		
		try {
			gestoreMessaggi.inviaMessaggio(id, Long.parseLong(destinatario),mex);
		} catch (NumberFormatException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		} catch (MessaggiException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}
		
		String risposta= request.getParameter(RISPOSTA);
		
		//decido dove reindirizzare la richiesta
		if(risposta==null){
			response.sendRedirect(PROFILO_SERVLET+"?"+USER_PROFILE+"="+destinatario);
		}else if(risposta.equals(RISPOSTA_RICHIESTA)){
			response.sendRedirect(VISUALIZZA_RICHIESTE_SERVLET);
		}else if(risposta.equals(RISPOSTA_MESSAGGIO)){
			response.sendRedirect(VISUALIZZA_MESSAGGI_SERVLET);
		}else{
			response.sendRedirect(ERROR_PAGE);
		}
			
	}

}
