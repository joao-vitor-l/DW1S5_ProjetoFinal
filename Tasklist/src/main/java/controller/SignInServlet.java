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

@WebServlet(urlPatterns={"/signIn", "/"})
public class SignInServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	UserDao userDao = new UserDao();
       
    public SignInServlet(){ super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/signIn.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        String passwordEncrypted = PasswordEncryptor.encryptPassword(password);

        User user = null;
        
        try{
            user = userDao.signInUser(username, passwordEncrypted);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        if(user != null){
        	request.setAttribute("idUser", user.getId());
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/main");
    		dispatcher.forward(request, response);
        }else{
        	try{
				user = userDao.validateUsername(username);
				if(user != null){
					request.setAttribute("error", "Incorrect password.");
		        	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/signIn.jsp");
		    		dispatcher.forward(request, response);
				}else{
					request.setAttribute("error", "Could not find your account.");
		        	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/signIn.jsp");
		    		dispatcher.forward(request, response);
				}
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
        }
	}
}
