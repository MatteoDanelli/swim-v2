package SE2.Swimv2.Servlet.UserServlet;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SE2.Swimv2.Exceptions.RichiestaAmiciziaException;
import SE2.Swimv2.Session.GestoreRichiesteAmiciziaRemote;
import SE2.Swimv2.Util.RemoteManager;

/**
 * Servlet implementation class RichiediAmiciziaServlet
 */
public class RichiediAmiciziaServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

		//nomi attributi
		private static final String ERROR = "Errore";
		private static final String USER_ID= "userId";
		private static final String USER_PROFILE = "userId";
		
		//valori attributi
		private static final String LOGIN_ERROR= "logError";

		//nomi parametri
		private static final String ID_DEST="destinatario";
		
		//nomi pagine
		private static final String HOME_PAGE = "index.jsp";
		private static final String ERROR_PAGE = "error.jsp";
		
		// servlet
		private static final String PROFILO_SERVLET = "/Swimv2-Client/ProfiloServlet";

		private RemoteManager remoteManager= new RemoteManager();
		private GestoreRichiesteAmiciziaRemote gestoreRichiesteAmicizia;
		
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public RichiediAmiciziaServlet() {
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
				gestoreRichiesteAmicizia = remoteManager.getGestoreRichiesteAmiciziaRemote();
			} catch (NamingException e) {
				response.sendRedirect(ERROR_PAGE);
				return;
			}	
			
			String destinatarioStr= request.getParameter(ID_DEST);
			
			//se destinatario != null invio richiesta altrimenti errore
			if(destinatarioStr!=null){
				
				try {
					long dest=Long.parseLong(destinatarioStr);
					gestoreRichiesteAmicizia.inviaRichiestaAmicizia(id, dest);
				} catch (NumberFormatException e) {
					response.sendRedirect(ERROR_PAGE);
					return;
				} catch (RichiestaAmiciziaException e) {
					response.sendRedirect(ERROR_PAGE);
					return;
				}
				response.sendRedirect(PROFILO_SERVLET + "?" + USER_PROFILE + "="+ destinatarioStr);
				
			}else{
				response.sendRedirect(ERROR_PAGE);
			}
			
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		}

	}
