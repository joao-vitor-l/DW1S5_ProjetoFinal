package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.TaskDao;

@WebServlet("/markAsCompleted")
public class TaskCompletedServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    
    TaskDao taskDao = new TaskDao();

    public TaskCompletedServlet(){ super(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	HttpSession session = request.getSession();
    	String idUser = (String) session.getAttribute("idUser");
    	int idTask = Integer.parseInt(request.getParameter("idTask"));

        try{
            taskDao.markAsCompleted(idTask);
        }catch(Exception e){
            e.printStackTrace();
        }

        request.setAttribute("idUser", Integer.parseInt(idUser));
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/main");
		dispatcher.forward(request, response);
    }
}
