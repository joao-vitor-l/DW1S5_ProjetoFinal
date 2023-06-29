package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDao{
	public int signUpUser(User user) throws ClassNotFoundException{
        String INSERT_USER_SQL = "INSERT INTO user_acc " +
            "(username, password, name, email) VALUES " +
            "(?, ?, ?, ?);";

        int result = 0;

        Class.forName("org.postgresql.Driver");

        try(Connection connection = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/tasklist", "postgres", "fmii");

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)){
    	
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getEmail());

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    
		return result;
    }
 
	 public User signInUser(String username, String password) throws ClassNotFoundException{
        String SELECT_USER_SQL = "SELECT id, username, password, name, email FROM user_acc " +
            "WHERE username=? AND password=?";

        User user = null;

        Class.forName("org.postgresql.Driver");

        try(Connection connection = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/tasklist", "postgres", "fmii");

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL)){
        	
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
            	if(resultSet.next()){
            		user = new User();
            		user.setId(resultSet.getInt("id"));
            		user.setUsername(resultSet.getString("username"));
            		user.setPassword(resultSet.getString("password"));
            		user.setName(resultSet.getString("name"));
            		user.setEmail(resultSet.getString("email"));
            	}
            }catch(SQLException e){
	            e.printStackTrace();
	        }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    
        return user;
	 }
	 
	 public User getUserById(int idUser) throws ClassNotFoundException{
			String SELECT_USER_SQL = "SELECT id, username, password, name, email FROM user_acc" +
					" WHERE id=?";
			
		User user = null;
		
		Class.forName("org.postgresql.Driver");
		 
		try(Connection connection = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/tasklist", "postgres", "fmii");
				 
	    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL)){
			
			preparedStatement.setInt(1, idUser);
			 
			System.out.println(preparedStatement);
			 
			try(ResultSet resultSet = preparedStatement.executeQuery();){
				if(resultSet.next()){
					user = new User();
					user.setId(resultSet.getInt("id"));
	        		user.setUsername(resultSet.getString("username"));
	        		user.setPassword(resultSet.getString("password"));
	        		user.setName(resultSet.getString("name"));
	        		user.setEmail(resultSet.getString("email"));
				}
			 }catch(SQLException e){
		            e.printStackTrace();
	        }
			
		 }catch(SQLException e){
	            e.printStackTrace();
		 }
		
		return user;
	}
	 
	 public User validateUsername(String username) throws ClassNotFoundException{
			String SELECT_USER_SQL = "SELECT id FROM user_acc" +
					" WHERE username=?";
			
		User user = null;
		
		Class.forName("org.postgresql.Driver");
		 
		try(Connection connection = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/tasklist", "postgres", "fmii");
				 
	    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL)){
			
			preparedStatement.setString(1, username);
			 
			try(ResultSet resultSet = preparedStatement.executeQuery();){
				if(resultSet.next()){
					user = new User();
					user.setId(resultSet.getInt("id"));
				}
			 }catch(SQLException e){
		            e.printStackTrace();
	        }
			
		 }catch(SQLException e){
	            e.printStackTrace();
		 }
		
		return user;
	}
	 
	 public User validateEmail(String email) throws ClassNotFoundException{
			String SELECT_USER_SQL = "SELECT id FROM user_acc" +
					" WHERE email=?";
			
		User user = null;
		
		Class.forName("org.postgresql.Driver");
		 
		try(Connection connection = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/tasklist", "postgres", "fmii");
				 
	    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL)){
			
			preparedStatement.setString(1, email);
			 
			try(ResultSet resultSet = preparedStatement.executeQuery();){
				if(resultSet.next()){
					user = new User();
					user.setId(resultSet.getInt("id"));
				}
			 }catch(SQLException e){
		            e.printStackTrace();
	        }
			
		 }catch(SQLException e){
	            e.printStackTrace();
		 }
		
		return user;
	}
}
