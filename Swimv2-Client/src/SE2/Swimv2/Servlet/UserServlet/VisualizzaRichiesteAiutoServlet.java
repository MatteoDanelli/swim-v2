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
import SE2.Swimv2.Session.GestoreRichiesteAiutoRemote;
import SE2.Swimv2.Util.RemoteManager;

/**
 * Servlet implementation class VisualizzaRichiesteAiutoServlet
 */
public class VisualizzaRichiesteAiutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//nomi attributi
		private static final String ERROR = "Errore";
		private static final String RICHIESTE= "elencoRichieste";
		private static final String RICHIESTA= "singolaRichiesta";
		private static final String USER_ID= "userId";
		
		//valori attributi
		private static final String LOGIN_ERROR= "logError";

		//nomi parametri
		private static final String ID_REQ="idRichiesta";
		
		//nomi pagine
		private static final String HOME_PAGE = "index.jsp";
		private static final String ERROR_PAGE = "error.jsp";
		private static final String ELENCO_RICHIESTE_PAGE = "User/elencoRichieste.jsp";
		private static final String DETTAGIO_RICHIESTA_PAGE = "User/dettaglioRichiesta.jsp";
		

		private RemoteManager remoteManager= new RemoteManager();
		private GestoreRichiesteAiutoRemote gestoreRichieste;
		
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public VisualizzaRichiesteAiutoServlet() {
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
				gestoreRichieste = remoteManager.getGestoreRichiesteAiutoRemote();
			} catch (NamingException e) {
				response.sendRedirect(ERROR_PAGE);
				return;
			}	
			
			String idRichiesta= request.getParameter(ID_REQ);
		
			//se idRichiesta!=null visualizzo il singolo messaggio, altrimenti
			//visualizzo l' elenco dei messaggi
			if(idRichiesta!=null){
				long idReq;
				try{
					idReq=Long.parseLong(idRichiesta);
				}catch (NumberFormatException e) {
					response.sendRedirect(ERROR_PAGE);
					return;
				}
				
				try {
					gestoreRichieste.settaRichiestaLetta(idReq);
				} catch (MessaggiException e) {
					response.sendRedirect(ERROR_PAGE);
					return;
				}
				
				Messaggio richiesta;
				try {
					richiesta= gestoreRichieste.getSingleRequest(id, idReq);
				} catch (MessaggiException e) {
					response.sendRedirect(ERROR_PAGE);
					return;
				}
				
				request.setAttribute(RICHIESTA, richiesta);
				request.getRequestDispatcher(DETTAGIO_RICHIESTA_PAGE).forward(request, response);
				
			}else{
				//Setto lista messaggi
				List<Messaggio> richiste= gestoreRichieste.elencoRichiesteAiuto(id);
				request.setAttribute(RICHIESTE, richiste);

				request.getRequestDispatcher(ELENCO_RICHIESTE_PAGE).forward(request, response);			
			}

		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
		}

	}
