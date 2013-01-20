package SE2.Swimv2.Servlet.UserServlet;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SE2.Swimv2.Entity.Skill;
import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Exceptions.MessaggiException;
import SE2.Swimv2.Session.GestoreRichiesteAiutoRemote;
import SE2.Swimv2.Session.GestoreSkillRemote;
import SE2.Swimv2.Session.GestoreUserRemote;
import SE2.Swimv2.Util.RemoteManager;

/**
 * Servlet implementation class InviaRichiesteAiutoServlet
 */
public class RichiesteAiutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// nomi parametri
	private static final String USER_PROFILE = "userId";
	private static final String ERROR = "Errore";
	private static final String MESSAGGIO = "messaggio";
	private static final String SKILL = "skill";
	private static final String DESTINATARIO = "destinatario";

	// nomi attributi
	private static final String USER = "user";
	private static final String SKILL_SET= "skillSet";

	// valori attributi
	private static final String LOGIN_ERROR = "logError";
	private static final String USER_ID = "userId";

	// nomi pagine
	private static final String HOME_PAGE = "index.jsp";
	private static final String USER_INVIA_MESSAGGI = "User/inviaRichiestaAiuto.jsp";
	private static final String ERROR_PAGE = "error.jsp";

	// servlet
	private static final String PROFILO_SERVLET = "/Swimv2-Client/ProfiloServlet";

	private RemoteManager remoteManager = new RemoteManager();
	private GestoreRichiesteAiutoRemote gestoreRichiesteAiuto;
	private GestoreUserRemote gestoreUser;
	private GestoreSkillRemote gestoreSkill;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RichiesteAiutoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// se non esiste una sessione richiamo l' home page
		Long id = (Long) request.getSession().getAttribute(USER_ID);
		if (id == null) {
			request.setAttribute(ERROR, LOGIN_ERROR);
			request.getRequestDispatcher(HOME_PAGE).forward(request, response);
			return;
		}

		try {
			gestoreUser = remoteManager.getGestoreUserRemote();
			gestoreSkill = remoteManager.getGestoreSkillRemote();
		} catch (NamingException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}

		// SETTO ATTRIBUTO USER
		String destinatario = request.getParameter(DESTINATARIO);
		if (destinatario == null) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}

		long dest;
		try {
			dest = Long.parseLong(destinatario);
		} catch (NumberFormatException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}

		User user = gestoreUser.getById(dest);
		request.setAttribute(USER, user);
		
		//Setto la lista delle skill
		List<Skill> skillSet= gestoreSkill.getSkillSet();
		request.setAttribute(SKILL_SET, skillSet);
		
		//forwarding
		request.getRequestDispatcher(USER_INVIA_MESSAGGI).forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// se non esiste una sessione richiamo l' home page
		Long id = (Long) request.getSession().getAttribute(USER_ID);
		if (id == null) {
			request.setAttribute(ERROR, LOGIN_ERROR);
			request.getRequestDispatcher(HOME_PAGE).forward(request, response);
			return;
		}

		String mex = request.getParameter(MESSAGGIO);
		String destinatario = request.getParameter(DESTINATARIO);
		String skill_str = request.getParameter(SKILL);
		long skill_long;
		
		try {
			skill_long = Long.parseLong(skill_str);
		} catch (NumberFormatException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}

		try {
			gestoreRichiesteAiuto = remoteManager.getGestoreRichiesteAiutoRemote();
			gestoreSkill = remoteManager.getGestoreSkillRemote();
		} catch (NamingException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}

		Skill skill = gestoreSkill.getById(skill_long);
		
		try {
			gestoreRichiesteAiuto.inviaRichiestaAiuto(id,Long.parseLong(destinatario), skill, mex);
		} catch (NumberFormatException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		} catch (MessaggiException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}

		response.sendRedirect(PROFILO_SERVLET + "?" + USER_PROFILE + "="+ destinatario);
	}

}
