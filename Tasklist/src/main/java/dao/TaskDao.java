package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import model.Task;

public class TaskDao{
	public int registerTask(Task task) throws ClassNotFoundException{
        String INSERT_TASK_SQL = "INSERT INTO task" +
            " (title, description, id_user) VALUES " +
            " (?, ?, ?);";

        int result = 0;

        Class.forName("org.postgresql.Driver");

        try(Connection connection = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/tasklist", "postgres", "fmii");

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TASK_SQL)){
    	
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setInt(3, task.getIdUser());

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    
		return result;
    }
	
	public List<Task> getTasks(int idUser) throws ClassNotFoundException{
		String SELECT_TASKS_SQL = "SELECT id, title, description, dt_creation, dt_conclusion, status, id_user FROM task" +
				" WHERE id_user=?";
		
		List<Task> tasks = null;
		Task task = null;
		
		Class.forName("org.postgresql.Driver");
		 
		try(Connection connection = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/tasklist", "postgres", "fmii");
				 
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TASKS_SQL)){
			
			preparedStatement.setInt(1, idUser);
			 
			System.out.println(preparedStatement);
			 
			try(ResultSet resultSet = preparedStatement.executeQuery();){
				tasks = new ArrayList<>();
				while(resultSet.next()){
					task = new Task();
					task.setId(resultSet.getInt("id"));
					task.setTitle(resultSet.getString("title"));
					task.setDescription(resultSet.getString("description"));
					task.setDtCreation(resultSet.getDate("dt_creation"));
					task.setDtConclusion(resultSet.getDate("dt_conclusion"));
					task.setStatus(resultSet.getString("status"));
					task.setIdUser(resultSet.getInt("id_user"));
            		tasks.add(task);
            		}
			 }catch(SQLException e){
		            e.printStackTrace();
	        }
		 
		}catch(SQLException e){
	            e.printStackTrace();
		}
		
		return tasks;
	}
	
	public Task getTaskById(int idTask) throws ClassNotFoundException{
		String SELECT_TASK_SQL = "SELECT id, title, description, dt_creation, dt_conclusion, status, id_user FROM task" +
				" WHERE id=?";
		
		Task task = null;
		
		Class.forName("org.postgresql.Driver");
		 
		try(Connection connection = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/tasklist", "postgres", "fmii");
				 
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TASK_SQL)){
			
			preparedStatement.setInt(1, idTask);
			 
			System.out.println(preparedStatement);
			 
			try(ResultSet resultSet = preparedStatement.executeQuery();){
				if(resultSet.next()){
					task = new Task();
					task.setId(resultSet.getInt("id"));
					task.setTitle(resultSet.getString("title"));
					task.setDescription(resultSet.getString("description"));
					task.setDtCreation(resultSet.getDate("dt_creation"));
					task.setDtConclusion(resultSet.getDate("dt_conclusion"));
					task.setStatus(resultSet.getString("status"));
					task.setIdUser(resultSet.getInt("id_user"));
				}
			 }catch(SQLException e){
		            e.printStackTrace();
	        }
			
		 }catch(SQLException e){
	            e.printStackTrace();
		 }
		
		return task;
	}
	
	public boolean filterTask(String title, String filterInput){
		int currentIndex = 0;
		int filterLength = filterInput.length();

		for(char c : title.toCharArray()){
			if (c == filterInput.charAt(currentIndex)){
				currentIndex++;
				if(currentIndex == filterLength){
					return true;
				}
			}else{
				break;
		    }
		}

		return false;
	}
	
	public void markAsCompleted(int idTask) throws ClassNotFoundException{
	    String UPDATE_TASK_SQL = "UPDATE task SET status = 'Completed', dt_conclusion = CURRENT_TIMESTAMP WHERE id = ?";

	    Class.forName("org.postgresql.Driver");

	    try(Connection connection = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/tasklist", "postgres", "fmii");
	         
	    PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TASK_SQL)){
	    	
	        preparedStatement.setInt(1, idTask);

	        System.out.println(preparedStatement);
	        preparedStatement.executeUpdate();

	    }catch(SQLException e){
	        e.printStackTrace();
	    }
	}
	
	public void updateTask(int idTask, String newTitle, String newDescription) throws ClassNotFoundException{
	    String UPDATE_TASK_SQL = "UPDATE task SET title = ?, description = ? WHERE id = ?";

	    Class.forName("org.postgresql.Driver");

	    try(Connection connection = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/tasklist", "postgres", "fmii");
	        
	    PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TASK_SQL)){
	    	
	        preparedStatement.setString(1, newTitle);
	        preparedStatement.setString(2, newDescription);
	        preparedStatement.setInt(3, idTask);

	        System.out.println(preparedStatement);
	        preparedStatement.executeUpdate();
		        
	    }catch (SQLException e){
	        e.printStackTrace();
	    }
	}

	public int deleteTask(int idTask) throws ClassNotFoundException{
		String DELETE_TASK_SQL = "DELETE FROM task WHERE id = ?";

		int result = 0;

		Class.forName("org.postgresql.Driver");

		try(Connection connection = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/tasklist", "postgres", "fmii");
			
		PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TASK_SQL)){
			
			preparedStatement.setInt(1, idTask);

			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}

		return result;
	}
}
