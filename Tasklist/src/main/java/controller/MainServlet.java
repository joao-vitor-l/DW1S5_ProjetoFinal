package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Task;
import dao.TaskDao;

@WebServlet("/main")
public class MainServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	TaskDao taskDao = new TaskDao();
       
    public MainServlet(){ super(); }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	HttpSession session = request.getSession();
        session.removeAttribute("idUser");
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/main.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int idUser = (int) request.getAttribute("idUser");
		HttpSession session = request.getSession();
		session.setAttribute("idUser", Integer.toString(idUser));
		
		String filterInput = request.getParameter("filterInput");
	    session.setAttribute("filterInput", filterInput);
		
		List<Task> tasks = null;
		
		try{
			tasks = taskDao.getTasks(idUser);
		}catch(Exception e){
            e.printStackTrace();
        }
		
		request.setAttribute("tasks", tasks);
		request.setAttribute("idUser", idUser);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/main.jsp");
        dispatcher.forward(request, response);
	}
}
