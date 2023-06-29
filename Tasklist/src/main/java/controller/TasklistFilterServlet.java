package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import model.Task;
import dao.TaskDao;

@WebServlet("/tasklistFilter")
public class TasklistFilterServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
  
	TaskDao taskDao = new TaskDao();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int idUser = Integer.parseInt((String) request.getSession().getAttribute("idUser"));
		String filterInput = request.getParameter("filterInput");
    
		List<Task> tasks = null;
		
		try{
		    tasks = taskDao.getTasks(idUser);
		    if(filterInput != null && !filterInput.isEmpty()){
		    	tasks.removeIf(task -> !taskDao.filterTask(task.getTitle().toLowerCase(), filterInput.toLowerCase()));
		    }
		}catch(Exception e){
		    e.printStackTrace();
		}

		request.setAttribute("tasks", tasks);
		request.setAttribute("idUser", idUser);
		request.setAttribute("filterInput", filterInput);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/main.jsp");
		dispatcher.forward(request, response);
	}
}
