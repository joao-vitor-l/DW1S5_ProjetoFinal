package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Task;
import dao.TaskDao;

@WebServlet("/taskEdit")
public class TaskEditServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	TaskDao taskDao = new TaskDao();
    
    public TaskEditServlet(){ super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/taskEdit.jsp");
		
		int idTask = Integer.parseInt(request.getParameter("idTask"));
		
		try{
        	Task task = taskDao.getTaskById(idTask);
        	request.setAttribute("task", task);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
		
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
    	String idUser = (String) session.getAttribute("idUser");
    	int idTask = Integer.parseInt(request.getParameter("idTask"));
    	
    	String newTitle = request.getParameter("title");
        String newDescription = request.getParameter("description");
        
        try{
			taskDao.updateTask(idTask, newTitle, newDescription);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
        request.setAttribute("idUser", Integer.parseInt(idUser));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/main");
		dispatcher.forward(request, response);
	}
}
