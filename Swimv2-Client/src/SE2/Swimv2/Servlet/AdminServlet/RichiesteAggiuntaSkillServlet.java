package SE2.Swimv2.Servlet.AdminServlet;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SE2.Swimv2.Entity.RichiestaSkill;
import SE2.Swimv2.Exceptions.RichiesteSkillException;
import SE2.Swimv2.Session.GestoreRichiesteSkillRemote;
import SE2.Swimv2.Util.RemoteManager;

/**
 * Servlet implementation class RichiesteAggiuntaSkillServlet
 * @author Matteo Danelli
 * Tale classe gestisce la pagina di richieste ampliamento skill set e consente di accettare/rifiutare richieste
 */
public class RichiesteAggiuntaSkillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//nomi attributi
	private static final String ERROR = "Errore";
	private static final String ADMIN= "admin";
	private static final String RICHIESTE= "richieste";
	private static final String ADMIN_ID= "adminId";
	private static final String CHOICE="scelta";
	private static final String ID_RICHIESTA="id";
	
	//valori attributi
	private static final String LOGIN_ERROR= "logError";
	
	//nomi pagine
	private static final String ADD_SKILL = "/Admin/gestisci_richieste.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	private static final String LOGIN_ADMIN = "/Admin/login_admin.jsp";
	private static final String AGGIUNTA_SERVLET = "/Swimv2-Client/RichiesteAggiuntaSkillServlet";

	private static final String RIFIUTA = "r";
	private static final String ACCETTA = "a";
	
	private RemoteManager remoteManager= new RemoteManager();
	private GestoreRichiesteSkillRemote gestoreRichieste;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RichiesteAggiuntaSkillServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//se non esiste una sessione richiamo l'home page
		Long id= (Long) request.getSession().getAttribute(ADMIN_ID);
		if(id==null){
			request.setAttribute(ERROR, LOGIN_ERROR);
			request.getRequestDispatcher(LOGIN_ADMIN).forward(request, response);
			return;
		}
		
		if(request.getAttribute(ADMIN)==null) {		
			try {
				gestoreRichieste = remoteManager.getGestoreRichiesteSkillRemote();
			} catch (NamingException e) {
				response.sendRedirect(ERROR_PAGE);
				return;
			}
			
			List<RichiestaSkill> richieste = gestoreRichieste.elencoRichieste();
			request.setAttribute(RICHIESTE, richieste);
		}				
		request.getRequestDispatcher(ADD_SKILL).forward(request, response);
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//se non esiste una sessione richiamo l'home page
		Long id= (Long) request.getSession().getAttribute(ADMIN_ID);
		if(id==null){
			request.setAttribute(ERROR, LOGIN_ERROR);
			request.getRequestDispatcher(LOGIN_ADMIN).forward(request, response);
			return;
		}
		
		String parameterChoice = request.getParameter(CHOICE);
		String parameterId = request.getParameter(ID_RICHIESTA);
		
		if(parameterChoice==ACCETTA) {
				try {
					Long idSkill = Long.parseLong(parameterId.substring(1));
					gestoreRichieste.accettaRichiesta(idSkill);
					response.sendRedirect(AGGIUNTA_SERVLET);
				} catch (RichiesteSkillException e) {
					response.sendRedirect(ERROR_PAGE);
					return;
				}
		
		}
		
		else if(parameterChoice==RIFIUTA) {
				try {
					Long idSkill = Long.parseLong(parameterId.substring(1));
					gestoreRichieste.rifiutaRichiesta(idSkill);
					response.sendRedirect(AGGIUNTA_SERVLET);
				} catch (RichiesteSkillException e) {
					response.sendRedirect(ERROR_PAGE);
					return;
				}
		}
		else {
			response.sendRedirect(ERROR_PAGE);
			return;
		}

	}

}
