package SE2.Swimv2.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//nomi attributi
	private static final String USER_ID = "userId";
	
	//valori attributi
	private static final String LOGOUT_OK= "logoutOk";
	private static final String ADMIN_ID = "adminId";
	private static final String MESSAGE = "Messaggio";
	
	//nomi pagine
	private static final String HOME_PAGE = "index.jsp";
	//nomi pagine
	private static final String HOME_PAGE_ADMIN = "Admin/login_admin.jsp";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sessione = request.getSession();
		
        if (sessione.getAttribute(USER_ID) != null) {
            sessione.setAttribute(USER_ID, null);		
            request.setAttribute(MESSAGE, LOGOUT_OK);
    		request.getRequestDispatcher(HOME_PAGE).forward(request, response);
        }
        else if (sessione.getAttribute(ADMIN_ID) != null) {
            sessione.setAttribute(ADMIN_ID, null);
    		request.setAttribute(MESSAGE, LOGOUT_OK);
    		request.getRequestDispatcher(HOME_PAGE_ADMIN).forward(request, response);
        }else{
        	response.sendRedirect(HOME_PAGE);
        }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
