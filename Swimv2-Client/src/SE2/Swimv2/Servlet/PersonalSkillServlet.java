package SE2.Swimv2.Servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SE2.Swimv2.Entity.Skill;
import SE2.Swimv2.Exceptions.UserException;
import SE2.Swimv2.Session.GestoreSkillRemote;
import SE2.Swimv2.Session.GestoreUserRemote;
import SE2.Swimv2.Util.RemoteManager;

/**
 * Servlet implementation class PersonalSkillServlet
 */
public class PersonalSkillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//nomi parametri
	private static final String ERROR = "Errore";
	private static final String SKILL_SET= "skillSet";
	private static final String PERSONAL_SKILL= "personalSkill";
	
	//valori attributi
	private static final String LOGIN_ERROR= "logError";
	private static final String USER_ID= "userId";
	
	//nomi pagine
	private static final String HOME_PAGE = "index.jsp";
	private static final String PERSONAL_SKILL_PAGE = "User/personalSkill.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	//servlet
	private static final String USER_SERVLET = "/Swimv2-Client/UserServlet";
	
	private RemoteManager remoteManager= new RemoteManager();
	private GestoreSkillRemote gestoreSkill;
	private GestoreUserRemote gestoreUser;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalSkillServlet() {
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
			gestoreSkill = remoteManager.getGestoreSkillRemote();
			List<Skill> skillSet= gestoreSkill.getSkillSet();
			List<Skill> personalSkill= gestoreSkill.getPersonalSkill(id);
			request.setAttribute(SKILL_SET, skillSet);
			request.setAttribute(PERSONAL_SKILL, personalSkill);
		} catch (NamingException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}
		
		request.getRequestDispatcher(PERSONAL_SKILL_PAGE).forward(request, response);
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
		
		//carico lo skillset
		List<Skill> skillSet;
		Set<Skill> personalSkill= new HashSet<Skill>();
		String skillPassataString;
		Skill skillAggiunta;
		
		try {
			gestoreSkill = remoteManager.getGestoreSkillRemote();
			gestoreUser = remoteManager.getGestoreUserRemote();
			skillSet= gestoreSkill.getSkillSet();

		} catch (NamingException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}
		

		for(Skill skill: skillSet){
			skillPassataString= request.getParameter(String.valueOf(skill.getId()));
			if(skillPassataString!=null){
				skillAggiunta = gestoreSkill.getById(Long.parseLong(skillPassataString));
				personalSkill.add(skillAggiunta);
			}
		}
		
		try {
			gestoreUser.modificaPersonalSkill(id, personalSkill);
		} catch (UserException e) {
			response.sendRedirect(ERROR_PAGE);
		}
		
		response.sendRedirect(USER_SERVLET);
		
	}

}
