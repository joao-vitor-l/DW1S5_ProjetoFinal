<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Collections, java.util.Comparator, 
	java.text.SimpleDateFormat, com.google.gson.Gson, com.google.gson.reflect.TypeToken, 
	model.Task" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Tasklist</title>
	
	<style>
		body {
			display: flex;
			justify-content: center;
			align-items: center;
			font-family: 'Poppins', sans-serif;
			background-color: #f9f8f4;
		}
		
		/* ---------- */
		
		.tasklist {
		  	display: flex;
		  	flex-direction: column;
			text-align: center;
		}
		
		/* ---------- */
		
		.filter {
			margin-bottom: .4rem;
			text-align: center;
			padding: 20px;
			background-color: transparent;
			border: none;
		 	border-bottom: 1px solid #A6A7AE;
		 	outline: none;
		 	font-family: 'Poppins';
		  	font-size: 14px;
		  	letter-spacing: 2px;
		  	color: #202129;
		}
		
		.filter::placeholder {
			color: #202129;
		  	opacity: .75;
		}
		
		/* ---------- */
		
		.card {
			position: relative;
			width: 450px;
			margin-bottom: 30px;
		    border-radius: 10px;
		    background: rgba(233, 232, 231, .8);
		    box-shadow: 0 0 10px rgba(0, 0, 0, .32);
		}
		
		/* ---------- */
		
		.card-task {
		    display: flex;
		    flex-direction: column;
		    margin: 2rem;
		    text-align: left;
		    font-size: 1.8rem;
		    font-weight: 600;
		}
		
		.card-task .task-counter {
		    margin-top: 2rem;
		    margin-bottom: 0rem;
		    font-size: 1rem;
		    color: #63677d;
		}
		
		.card-task .task-status {
			margin-bottom: 1rem;
			text-align: center;
				
		}
		
		.status-todo {
			text-align: center;
		    color: #EA4354;
		}
		
		.status-completed {
		    text-align: center;
		    color: #6ACD97;
		}
		
		.card-task .task-title {
			margin-bottom: 1rem;
			color: #202129;
		}
		
		.card-task .task-description {
			margin-bottom: 1rem;
		    font-size: 1.2rem;
		    font-weight: 500;
			color: #202129;
		}
		
		.card-task .task-dt {
		    font-size: 1rem;
		    font-weight: 500;
			color: #696D87;
		}
		
		/* ---------- */
		
		button {
			margin-bottom: 30px;
			font-family: 'Poppins';
			cursor: pointer;
		}
		
		button:hover {
		    transform: scale(1.1);
		}
		
		.task-button {
			width: 70%;
			margin-top: 20px;
			margin-right: 20px;
			margin-left: 20px;
		    font-size: 1rem;
		    font-weight: 500;
		    letter-spacing: 2px;
		    text-transform: lowercase;
		    box-shadow: 0 2px 4px rgba(0, 0, 0, .1);
		    transition: all .2s;
		}
		
		.task-button-delete:hover {
		    border-radius: 3px;
		    border-color: #CF616C;
			background: #FA7582;
		}
		
		.task-button-complete:hover {
			border-radius: 3px;
			border-color: #5DB383;
			background: #6ACD97;
		}
		
		.addtask-button {
			width: 40%;
		    margin-top: 2rem;
		    margin-bottom: 1rem;
		    margin-left: auto;
		    margin-right: auto;
		    padding: 1rem;
		    border: none;
		    border-radius: 100px;
		    font-family: 'Poppins';
		    font-size: 1rem;
		    font-weight: bold;
		    letter-spacing: 2px;
		    text-transform: uppercase;
		    background: #f9f8f4;
		    color: #202129;
		    box-shadow: 0 2px 4px rgba(0, 0, 0, .2);
		    transition: all .3s;
		}
		
		.signout-button {
			width: 40%;
		    margin-top: 2rem;
		    margin-bottom: 1rem;
		    margin-left: auto;
		    margin-right: auto;
		    padding: 1rem;
		    border: none;
		    border-radius: 100px;
		    font-family: 'Poppins';
		    font-size: 1rem;
		    font-weight: bold;
		    letter-spacing: 2px;
		    text-transform: uppercase;
		    background: #202129;
		    color: #f9f8f4;
		    box-shadow: 0 2px 4px rgba(0, 0, 0, .2);
		    transition: all .3s;
		}
		
		.backup-button {
			width: 40%;
		    margin-top: 2rem;
		    margin-bottom: 1rem;
		    margin-left: auto;
		    margin-right: auto;
		    padding: 1rem;
		    border: none;
		    border-radius: 100px;
		    font-family: 'Poppins';
		    font-size: 1rem;
		    font-weight: bold;
		    letter-spacing: 2px;
		    text-transform: uppercase;
		    background: #6ACD97;
		    color: #f9f8f4;
		    box-shadow: 0 2px 4px rgba(0, 0, 0, .2);
		    transition: all .3s;
		}
	</style>
	
</head>
<body>

	<div id="tasklist" class="tasklist">
	
	  	<form>
			<input type="text" id="filterInput" placeholder="Filter by title" class="filter" 
				<% if(request.getAttribute("filterInput") != null){ %> 
				value="<%= request.getAttribute("filterInput") %>" <% } %>>
		</form>
		
		<%
		List<Task> tasks = (List<Task>) request.getAttribute("tasks");
		
		Collections.sort(tasks, Comparator.comparing(Task::getId)); // Ordena por id de forma crescente
		
		if(tasks != null && tasks.size() != 0){
			int counter = 1;
			for(Task task : tasks){
		%>
		
		<div>
		
			<div class="card">
				
				<div class="card-task">
					<div class="task-counter">
						<%= counter++ %>.
					</div>
					<% if(!task.getStatus().equals("To Do")){ %>
					<div class="task-status status-completed">
						<%= task.getStatus() %>
					</div>
					<% }else{ %>
					<div class="task-status status-todo">
						<%= task.getStatus() %>
					</div>
					<% } %>
					<div class="task-title">
						<%= task.getTitle() %>
					</div>
					<% if(!task.getDescription().equals("")){ %>
					<div class="task-description">
						<%= task.getDescription() %>
					</div>
					<% }
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String dtCreationFormatted = dateFormat.format(task.getDtCreation());
					if(!task.getStatus().equals("To Do")){
							String dtConclusionFormatted = dateFormat.format(task.getDtCreation());
					%>
					<div class="task-dt">
						<%= dtCreationFormatted %> - <%= dtConclusionFormatted %>
					</div>
					<% }else{ %>
					<div class="task-dt">
						<%= dtCreationFormatted %>
					</div>
					<% } %>
				</div>
				<% if(!task.getStatus().equals("To Do")){ %>
				<form style="display: inline-block;" action="taskEdit" method="get">
					<input type="hidden" name="idTask" value="<%= task.getId() %>">
					<button type="submit" class="task-button">Edit</button>
				</form>
				<form style="display: inline-block;" action="deleteTask" method="post">
					<input type="hidden" name="idTask" value="<%= task.getId() %>">
					<button type="submit" class="task-button task-button-delete">Delete</button>
				</form>
				</td>
				<% }else{ %>
				<form style="display: inline-block;" action="taskEdit" method="get">
					<input type="hidden" name="idTask" value="<%= task.getId() %>">
					<button type="submit" class="task-button">Edit</button>
				</form>
				
				<form style="display: inline-block;" action="markAsCompleted" method="post">
				    <input type="hidden" name="idTask" value="<%= task.getId() %>">
				    <button type="submit" class="task-button task-button-complete">Complete</button>
				</form>
				
				<form style="display: inline-block;" action="deleteTask" method="post">
					<input type="hidden" name="idTask" value="<%= task.getId() %>">
					<button type="submit" class="task-button task-button-delete">Delete</button>
				</form>
				<%}%>
			
			</div>
		
		<%
			}
		}else{
		%>
		
		<h1>No tasks.</h1>
		
		<%
		}
		%>
		
		<form action="<%= request.getContextPath() %>/taskRegister" method="get">
				<button type="submit" class="addtask-button">Add Task</button>
		</form>
			
		<%
		if(tasks.size() != 0){
		%>
			
		<form action="<%= request.getContextPath() %>/tasklistBackup" method="post">
			<input type="hidden" name="tasks" value='<%= new Gson().toJson(tasks) %>'>
			<button type="submit" class="backup-button">Download Current Tasklist</button>
		</form>
			
		<%
		}
		%>
			
		<form action="<%= request.getContextPath() %>/signOut" method="post">
			<button type="submit" class="signout-button">Sign Out</button>
		</form>
		
		</div>
		
	</div>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
  	$(document).ready(function(){
    	var typingTimer;
    	var doneTypingInterval = 500; // Tempo de espera após a digitação (em milissegundos)

    	$('#filterInput').on('input', function(){
      		clearTimeout(typingTimer); //  Limpa o temporizador se houver uma digitação em andamento
      		typingTimer = setTimeout(filterTasks, doneTypingInterval);
    	});

    	// Função para filtrar as tarefas
    	function filterTasks(){
      		var filterInput = $('#filterInput').val();

      		$.ajax({
        	url: '<%= request.getContextPath() %>/tasklistFilter',
        	method: 'POST',
        	data: { filterInput: filterInput }, // Dados a serem enviados (texto do filtro)
        	dataType: 'html',
        	success: function(data){
          		$('#tasklist').html(data); // Atualiza o conteúdo da div "tasklist" com os dados recebidos
        		}
      		});
    	}

    	var filterInput = $('#filterInput');

    	// Função para focar no filtro enquanto o usuário digita
    	if(filterInput.val().length > 0){
      		filterInput.focus();
      		filterInput[0].setSelectionRange(filterInput.val().length, filterInput.val().length);
    	}
	});
  	
  	window.addEventListener('DOMContentLoaded', function() {
		var filterInput = document.getElementById('filterInput');
  	  	var placeholderText = filterInput.placeholder;

  	  	filterInput.addEventListener('focus', function(){
  	    filterInput.placeholder = '';
  	  });

  	  filterInput.addEventListener('blur', function() {
  	    if(filterInput.value === ''){
  	      filterInput.placeholder = placeholderText;
  	    }
  	  });
  	});
	</script>

</body>
</html>
