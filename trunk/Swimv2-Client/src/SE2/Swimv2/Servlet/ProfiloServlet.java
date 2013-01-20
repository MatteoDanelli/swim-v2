package SE2.Swimv2.Servlet;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SE2.Swimv2.Entity.Feedback;
import SE2.Swimv2.Entity.Skill;
import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Session.GestoreAmiciRemote;
import SE2.Swimv2.Session.GestoreFeedbackRemote;
import SE2.Swimv2.Session.GestoreRichiesteAmiciziaRemote;
import SE2.Swimv2.Session.GestoreSkillRemote;
import SE2.Swimv2.Session.GestoreUserRemote;
import SE2.Swimv2.Util.RemoteManager;

/**
 * Servlet implementation class ProfiloServlet
 */
public class ProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//nomi parametri
	private static final String USER_PROFILE = "userId";
	
	//nomi attributi
	private static final String USER_ID= "userId";
	private static final String USER= "user";
	private static final String FEEDBACK= "feedback";
	private static final String FEEDBACK_AVG= "feedback_avg";
	private static final String PERSONAL_SKILL = "personalSkill";
	private static final String RIC_AMICI= "ricAmici";
	private static final String SEND_FEEDBACK= "sendFeedback";
	private static final String PULSANTI= "pulsanti";
	
	//nomi pagine
	private static final String USER_PROFILE_PAGE = "User/profiloUtenti.jsp";
	private static final String GUEST_PROFILE_PAGE = "Guest/profiloUtenti.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	//servlet

    
	private RemoteManager remoteManager= new RemoteManager();
	private GestoreUserRemote gestoreUser;
	private GestoreFeedbackRemote gestoreFeedback;
	private GestoreAmiciRemote gestoreAmici;
	private GestoreRichiesteAmiciziaRemote gestoreRichiesteAmicizia;
	private GestoreSkillRemote gestoreSkill;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfiloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//verifico da chi è stata fatta la richiesta di visualizzazione del profilo
		Long id= (Long) request.getSession().getAttribute(USER_ID);
		
		if(id==null){
			this.guestRequest(request, response);
		}else{
			this.userRequest(id,request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	//metodo che gestisce le ichieste fatte da un user autenticato
	private void userRequest(long id,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long userIdDaVisualizzare;
		
		try {
			gestoreUser = remoteManager.getGestoreUserRemote();
			gestoreFeedback= remoteManager.getGestoreFeedbackRemote();
			gestoreSkill = remoteManager.getGestoreSkillRemote();
			gestoreAmici = remoteManager.getGestoreAmiciRemote();
			gestoreRichiesteAmicizia = remoteManager.getGestoreRichiesteAmiciziaRemote();
		} catch (NamingException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}
		
		try{
			userIdDaVisualizzare= Long.parseLong(request.getParameter(USER_PROFILE));
		}catch(NumberFormatException e){
			response.sendRedirect(ERROR_PAGE);
			return;
		}
		
		boolean mostraPulsanti = (id!=userIdDaVisualizzare);
		boolean isAmici= gestoreAmici.verificaAmicizia(id, userIdDaVisualizzare);
		boolean esisteRichiestaAmicizia = gestoreRichiesteAmicizia.esisteRichiestaAmicizia(id, userIdDaVisualizzare);
		User user= gestoreUser.getById(userIdDaVisualizzare);
		List<Feedback> feedbacks = gestoreFeedback.elencoFeedback(userIdDaVisualizzare);
		List<Skill> skills = gestoreSkill.getPersonalSkill(userIdDaVisualizzare);
		double avg = gestoreFeedback.mediaVotiFeedback(userIdDaVisualizzare);

		
		request.setAttribute(USER, user);
		request.setAttribute(FEEDBACK, feedbacks);
		request.setAttribute(FEEDBACK_AVG, avg);
		request.setAttribute(PERSONAL_SKILL, skills);
		request.setAttribute(SEND_FEEDBACK, isAmici);
		boolean req_amicizia=(!isAmici && !esisteRichiestaAmicizia);
		request.setAttribute(RIC_AMICI, req_amicizia);
		request.setAttribute(PULSANTI, mostraPulsanti);
		
		request.getRequestDispatcher(USER_PROFILE_PAGE).forward(request, response);
	}
	
	//metodo che gestisce le richieste fatte da uu utente Guest
	private void guestRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long userIdDaVisualizzare;
		
		try {
			gestoreUser = remoteManager.getGestoreUserRemote();
			gestoreFeedback= remoteManager.getGestoreFeedbackRemote();
			gestoreSkill = remoteManager.getGestoreSkillRemote();
		} catch (NamingException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}
		
		try{
			userIdDaVisualizzare= Long.parseLong(request.getParameter(USER_PROFILE));
		}catch(NumberFormatException e){
			response.sendRedirect(ERROR_PAGE);
			return;
		}
		
		User user= gestoreUser.getById(userIdDaVisualizzare);
		List<Feedback> feedbacks = gestoreFeedback.elencoFeedback(userIdDaVisualizzare);
		List<Skill> skills = gestoreSkill.getPersonalSkill(userIdDaVisualizzare);
		double avg = gestoreFeedback.mediaVotiFeedback(userIdDaVisualizzare);

		
		request.setAttribute(USER, user);
		request.setAttribute(FEEDBACK, feedbacks);
		request.setAttribute(FEEDBACK_AVG, avg);
		request.setAttribute(PERSONAL_SKILL, skills);
		
		request.getRequestDispatcher(GUEST_PROFILE_PAGE).forward(request, response);
		
	}
}
