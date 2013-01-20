package SE2.Swimv2.Servlet.UserServlet;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Exceptions.FeedbackException;
import SE2.Swimv2.Session.GestoreFeedbackRemote;
import SE2.Swimv2.Session.GestoreUserRemote;
import SE2.Swimv2.Util.RemoteManager;

/**
 * Servlet implementation class FeedbackServlet
 */
public class FeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// nomi parametri
	private static final String USER_PROFILE = "userId";
	private static final String ERROR = "Errore";
	private static final String COMMENTO = "commento";
	private static final String STELLE = "stelle";
	private static final String DESTINATARIO = "destinatario";

	// nomi attributi
	private static final String USER = "user";

	// valori attributi
	private static final String LOGIN_ERROR = "logError";
	private static final String USER_ID = "userId";

	// nomi pagine
	private static final String HOME_PAGE = "index.jsp";
	private static final String USER_FEEDBACK = "User/creaFeedback.jsp";
	private static final String ERROR_PAGE = "error.jsp";

	// servlet
	private static final String PROFILO_SERVLET = "/Swimv2-Client/ProfiloServlet";

	private RemoteManager remoteManager = new RemoteManager();
	private GestoreFeedbackRemote gestoreFeedback;
	private GestoreUserRemote gestoreUser;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FeedbackServlet() {
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
		
		//forwarding
		request.getRequestDispatcher(USER_FEEDBACK).forward(request,response);
		
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

		String destinatario = request.getParameter(DESTINATARIO);
		String commento = request.getParameter(COMMENTO);
		String stelle_str = request.getParameter(STELLE);
		Integer stelle_int;

		try {
			gestoreFeedback = remoteManager.getGestoreFeedbackRemote();
		} catch (NamingException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}

		try {
			stelle_int = Integer.parseInt(stelle_str);
			long dest=Long.parseLong(destinatario);
			gestoreFeedback.creaFeedback(id, dest , stelle_int.intValue(), commento);
		} catch (NumberFormatException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		} catch (FeedbackException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}

		response.sendRedirect(PROFILO_SERVLET + "?" + USER_PROFILE + "="+ destinatario);
	}

}