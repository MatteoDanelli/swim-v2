package SE2.Swimv2.Servlet.UserServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SE2.Swimv2.Session.GestoreRichiesteSkillRemote;
import SE2.Swimv2.Session.GestoreUserRemote;
import SE2.Swimv2.Util.RemoteManager;

/**
 * Servlet implementation class ProponiAbilitaServlet
 */
public class ProponiAbilitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//nomi parametri
	private static final String ERROR = "Errore";
	private static final String SKILL_PROPOSTA = "skill";
	
	//valori attributi
	private static final String LOGIN_ERROR= "logError";
	private static final String USER_ID= "userId";
	
	//nomi pagine
	private static final String HOME_PAGE = "index.jsp";
	private static final String USER_PROPONI_SKILL = "User/proponiSkill.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	

    
	private RemoteManager remoteManager= new RemoteManager();
	private GestoreRichiesteSkillRemote gestoreRichiesteSkill;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProponiAbilitaServlet() {
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
		
		request.getRequestDispatcher(USER_PROPONI_SKILL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
