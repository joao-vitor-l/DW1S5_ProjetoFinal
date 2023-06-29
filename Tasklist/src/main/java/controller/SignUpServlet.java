package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.User;
import dao.UserDao;
import controller.utils.PasswordEncryptor;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	UserDao userDao = new UserDao();

    public SignUpServlet(){ super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/signUp.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        
        String passwordEncrypted = PasswordEncryptor.encryptPassword(password);
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncrypted);
        user.setName(name);
        user.setEmail(email);
        
        try{
			User existingUser = userDao.validateUsername(username);
			if(existingUser != null){
				request.setAttribute("error", "Username already taken.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/signUp.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}
        
        try{
			User existingUser = userDao.validateEmail(email);
			if(existingUser != null){
				request.setAttribute("error", "Email already registered.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/signUp.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}

        try{
            userDao.signUpUser(user);
        }catch(Exception e){
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/signIn.jsp");
		dispatcher.forward(request, response);
	}
}
