package SE2.Swimv2.Servlet.AdminServlet;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SE2.Swimv2.Entity.Admin;
import SE2.Swimv2.Session.GestoreAdminRemote;
import SE2.Swimv2.Util.RemoteManager;

/**
 * Servlet implementation class ModificaPasswordAdminServlet
 */
public class ModificaPasswordAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	//nomi attributi
	private static final String ERROR = "Errore";
	private static final String ADMIN= "admin";
	private static final String ADMIN_ID= "adminId";
	private static final String PASSWORD="password";
	
	//valori attributi
	private static final String LOGIN_ERROR= "logError";
	
	//nomi pagine
	private static final String ERROR_PAGE = "error.jsp";
	private static final String HOME_ADMIN = "/Admin/login_admin.jsp";

	private static final String ADMIN_MODIFICA_PASSWORD = "/Admin/modificaPassword.jsp";
	private static final String ADMIN_SERVLET = "/Swimv2-Client/AdminServlet";

	private RemoteManager remoteManager = new RemoteManager();
	private GestoreAdminRemote gestoreAdmin;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaPasswordAdminServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Se non esiste la sessione richiamo l'homepage
		Long id= (Long) request.getSession().getAttribute(ADMIN_ID);
		if(id==null){
			request.setAttribute(ERROR, LOGIN_ERROR);
			request.getRequestDispatcher(HOME_ADMIN).forward(request, response);
			return;
		}
		
		//Inserisco nella request l'Admin come parametro
		try {
			gestoreAdmin = remoteManager.getGestoreAdminRemote();
			Admin admin = gestoreAdmin.getAdmin();
			request.setAttribute(ADMIN, admin);
		} catch (NamingException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}
		
		request.getRequestDispatcher(ADMIN_MODIFICA_PASSWORD).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		// Se non esiste la sessione richiamo l'homepage
		Long id = (Long) request.getSession().getAttribute(ADMIN_ID);
		if (id == null) {
			request.setAttribute(ERROR, LOGIN_ERROR);
			request.getRequestDispatcher(HOME_ADMIN).forward(request, response);
			return;
		}
		String nuovaPassword = request.getParameter(PASSWORD);
		try {
			gestoreAdmin = remoteManager.getGestoreAdminRemote();
		} catch (NamingException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}

		try {
			gestoreAdmin.modificaPassword(gestoreAdmin.getAdmin().getEmail(),nuovaPassword);
		} catch (Exception e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}

		response.sendRedirect(ADMIN_SERVLET);

	}
}
