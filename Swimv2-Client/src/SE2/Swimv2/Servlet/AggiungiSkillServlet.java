package SE2.Swimv2.Servlet;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SE2.Swimv2.Entity.Admin;
import SE2.Swimv2.Session.GestoreAdminRemote;
import SE2.Swimv2.Session.GestoreSkillRemote;
import SE2.Swimv2.Util.RemoteManager;

/**
 * Servlet implementation class AggiungiSkillServlet
 */
public class AggiungiSkillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//nomi attributi
	private static final String ERROR = "Errore";
	private static final String ADMIN= "admin";
	private static final String ADMIN_ID= "adminId";
	
	//valori attributi
	private static final String LOGIN_ERROR= "logError";
	
	//nomi pagine
	private static final String ADD_SKILL = "Admin/add_skill.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	private static final String HOME_ADMIN = "/Admin/login_admin.jsp";
	
	private RemoteManager remoteManager= new RemoteManager();
	private GestoreAdminRemote gestoreAdmin;
	private GestoreSkillRemote gestoreSkill;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiSkillServlet() {
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
					request.getRequestDispatcher(HOME_ADMIN).forward(request, response);
					return;
				}
				
				if(request.getAttribute(ADMIN)==null) {		
					try {
						gestoreAdmin = remoteManager.getGestoreAdminRemote();
						gestoreSkill = remoteManager.getGestoreSkillRemote();
					} catch (NamingException e) {
						response.sendRedirect(ERROR_PAGE);
						return;
					}
					
					//Imposto l'attributo Admin da passare alla homePage
					Admin admin = gestoreAdmin.getAdmin();
					request.setAttribute(ADMIN, admin);
				}				
				request.getRequestDispatcher(ADD_SKILL).forward(request, response);
		    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//TODO
		}

}
