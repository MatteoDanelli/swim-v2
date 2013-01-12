package SE2.Swimv2.Servlet.UserServlet;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SE2.Swimv2.Entity.RichiestaAmicizia;
import SE2.Swimv2.Exceptions.AmiciException;
import SE2.Swimv2.Exceptions.RichiestaAmiciziaException;
import SE2.Swimv2.Session.GestoreRichiesteAmiciziaRemote;
import SE2.Swimv2.Util.RemoteManager;

/**
 * Servlet implementation class RichiesteAmiciziaServlet
 */
public class RichiesteAmiciziaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//nomi attributi
	private static final String ERROR = "Errore";
	private static final String RIC_AMICIZIA= "richiesteAmicizia";
	private static final String USER_ID= "userId";
	
	//valori attributi
	private static final String LOGIN_ERROR= "logError";

	//nomi parametri
	private static final String ID_REQ="idRic";
	private static final String ACCETTA="accetta";
	
	//nomi pagine
	private static final String HOME_PAGE = "index.jsp";
	private static final String RICHIESTE_AMICIZIA_PAGE= "User/richiesteAmicizia.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	

	private RemoteManager remoteManager= new RemoteManager();
	private GestoreRichiesteAmiciziaRemote gestoreRichiesteAmicizia;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RichiesteAmiciziaServlet() {
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
		
		String idRichiesta= request.getParameter(ID_REQ);
		String accetta= request.getParameter(ACCETTA);
		
		//se i due parametri non sono nulli gestisco l' accettazione o il rifiuto della skill
		if(idRichiesta!=null && accetta!=null){
			try {
				this.gestisciRichiesta(id, gestoreRichiesteAmicizia, idRichiesta, accetta);
			} catch (AmiciException e) {
				response.sendRedirect(ERROR_PAGE);
				return;
			} catch (RichiestaAmiciziaException e) {
				response.sendRedirect(ERROR_PAGE);
				return;
			}
		}
		
		
		//Setto attributo numero nuove richieste amicizia
		List<RichiestaAmicizia> RichiesteAmicizia= gestoreRichiesteAmicizia.elencoRichiesteAmicizia(id);
		request.setAttribute(RIC_AMICIZIA, RichiesteAmicizia);

		request.getRequestDispatcher(RICHIESTE_AMICIZIA_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private void gestisciRichiesta(long id,GestoreRichiesteAmiciziaRemote gestoreRichiesteAmicizia,String idRichiesta,String accetta ) throws AmiciException,RichiestaAmiciziaException{
		
		long idRichiestaAmicizia= Long.parseLong(idRichiesta);
		
		if(accetta.equals("1")){
			try {
				gestoreRichiesteAmicizia.accettaRichiestaAmicizia(idRichiestaAmicizia, id);		
			} catch (AmiciException e) {
				throw e;
			} catch (RichiestaAmiciziaException e) {
				throw e;
			}
		}else if(accetta.equals("0")){
			try {
				gestoreRichiesteAmicizia.rifiutaRichiestaAmicizia(idRichiestaAmicizia, id);
			} catch (RichiestaAmiciziaException e) {
				throw e;
			}
	
		}
	}
}
