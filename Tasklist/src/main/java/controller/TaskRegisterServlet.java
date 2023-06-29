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

@WebServlet("/taskRegister")
public class TaskRegisterServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	TaskDao taskDao = new TaskDao();

    public TaskRegisterServlet(){ super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/taskRegister.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String idUser = (String) session.getAttribute("idUser");
		
		String title = request.getParameter("title");
        String description = request.getParameter("description");
        
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setIdUser(Integer.parseInt(idUser));

        try{
            taskDao.registerTask(task);
        }catch(Exception e){
            e.printStackTrace();
        }

        request.setAttribute("idUser", Integer.parseInt(idUser));
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/main");
		dispatcher.forward(request, response);
	}
}
