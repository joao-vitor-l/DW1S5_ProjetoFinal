package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.Task;
import model.User;
import dao.UserDao;
import controller.utils.CustomDateDeserializer;

@WebServlet("/tasklistBackup")
public class TasklistBackupServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	UserDao userDao = new UserDao();
	CustomDateDeserializer deserializer = new CustomDateDeserializer();
       
    public TasklistBackupServlet(){ super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String idUser = (String) session.getAttribute("idUser");
		String tasksJson = request.getParameter("tasks");
		
		User user = null;
		try{
			user = userDao.getUserById(Integer.parseInt(idUser));
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new CustomDateDeserializer());
        Gson gson = gsonBuilder.create();

        Type taskListType = new TypeToken<List<Task>>() {}.getType();
        List<Task> tasks = gson.fromJson(tasksJson, taskListType);

        StringBuilder backupContent = new StringBuilder();
        backupContent.append(user.getName()).append("'s Tasklist");
        int counter = 1;
        for(Task task : tasks){
            backupContent.append("\n\nTask ").append(counter++).append(".\n");
            backupContent.append("- Title: ").append(task.getTitle()).append("\n");
            backupContent.append("- Description: ").append(task.getDescription()).append("\n");
            backupContent.append("- Status: ").append(task.getStatus());
        }

        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename=\"tasklist_backup.txt\"");
        response.getWriter().write(backupContent.toString());
    }
}
