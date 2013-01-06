package SE2.Swimv2.Servlet;

import java.io.IOException;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Exceptions.UserException;
import SE2.Swimv2.Session.GestoreUserRemote;

/**
 * Servlet implementation class ModificaDatiServlet
 */
public class ModificaDatiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//nomi attributi
	private static final String ERROR = "Errore";
	private static final String USER= "user";
	private static final String EMAIL="email";
	private static final String PASSWORD="password";
	private static final String NOME="nome";
	private static final String COGNOME="cognome";
	private static final String PROVINCIA="provincia";
	private static final String SESSO="sesso";
	private static final String DATA="data";
	
	//valori attributi
	private static final String LOGIN_ERROR= "logError";
	private static final String USER_ID= "userId";
	
	//nomi pagine
	private static final String HOME_PAGE = "index.jsp";
	private static final String USER_MODIFICA_DATI_PAGE = "User/modifica_dati.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	//servlet
	private static final String USER_SERVLET = "/Swimv2-Client/UserServlet";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaDatiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long id= (Long) request.getSession().getAttribute(USER_ID);
		//se non esiste una sessione richiamo l' home page
		if(id==null){
			request.setAttribute(ERROR, LOGIN_ERROR);
			request.getRequestDispatcher(HOME_PAGE).forward(request, response);
			return;
		}

		if(request.getAttribute(USER)==null){
		
			try {
				GestoreUserRemote gestoreUser = getGestoreUserRemote();
				User user= gestoreUser.getById(id.longValue());
				request.setAttribute(USER, user);
			} catch (NamingException e) {

			}
			request.getRequestDispatcher(USER_MODIFICA_DATI_PAGE).forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email= request.getParameter(EMAIL);
		String psw= request.getParameter(PASSWORD);
		String nome= request.getParameter(NOME);
		String cognome= request.getParameter(COGNOME);
		String provincia= request.getParameter(PROVINCIA);
		String sessoString= request.getParameter(SESSO);
		char sesso = sessoString.charAt(0);
		String dataString= request.getParameter(DATA);
		Date data = null;
		
		Long id= (Long) request.getSession().getAttribute(USER_ID);
		
		//se non esiste una sessione richiamo l' home page
		if(id==null){
			request.setAttribute(ERROR, LOGIN_ERROR);
			request.getRequestDispatcher(HOME_PAGE).forward(request, response);
			return;
		}
		
		try {
			GestoreUserRemote gestoreUser = getGestoreUserRemote();
			gestoreUser.modificaAnagrafica(id, nome, cognome, provincia, sesso, data);
			gestoreUser.modificaEmail(id, email);
			if(psw!=""){
				gestoreUser.modificaPassword(id, psw);
			}

		} catch (NamingException e) {

		}catch (UserException e){
			
		}
		
		response.sendRedirect(USER_SERVLET);

	}

	private GestoreUserRemote getGestoreUserRemote() throws NamingException{
		Context jndiContext = new InitialContext();
		Object obj = jndiContext.lookup("GestoreUser/remote");
		GestoreUserRemote manager = (GestoreUserRemote) obj;
		return manager;
	}
}
