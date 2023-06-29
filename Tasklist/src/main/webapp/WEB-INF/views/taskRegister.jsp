<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>New task</title>
	
	<style>
		body {
			display: flex;
			min-height: 100vh;
			overflow: hidden;
			justify-content: center;
			align-items: center;
			font-family: 'Poppins', sans-serif;
			background-color: #f9f8f4;
		}
		
		/* ---------- */
		
		.card {
			position: relative;
   	 		transform: translateY(-5%);
			width: 450px;
    		justify-content: center;
		    padding: 4rem;
		    border-radius: 10px;
		    background: rgba(233, 232, 231, 255);
		    box-shadow: 0 0 10px rgba(0, 0, 0, .1);
		}
		
		/* ---------- */
		
		.form,
		.form .form-item input,
		btn {
    		text-align: center;
		}
		
		/* ---------- */
		
		
		.form {
		    display: flex;
		    flex-direction: column;
		    gap: 3.5rem;
		}
		
		.form .form-item {
		    position: relative;
		}
		
		.form .form-item input {
			padding: 25px;
			background-color: transparent;
			border: none;
		 	border-bottom: 1px solid #A6A7AE;
		 	outline: none;
		 	font-family: 'Poppins';
		  	font-size: calc(2.5rem * .50);
		  	letter-spacing: 2px;
		  	color: #202129;
		  	opacity: .9;
		}
		
		.form .form-item input::placeholder {
		  color: #202129;
		  opacity: .75;
		}
		
		/* ---------- */
		
		button {
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
		    background: #EA4354;
		    color: #f9f8f4;
		    box-shadow: 0 2px 4px rgba(0, 0, 0, .1);
		    cursor: pointer;
		    transition: all .3s;
		}
		
		button:hover {
		    transform: scale(1.1);
		}
    </style>
    
</head>
<body>

	<div class="card">
		
		<form action="<%= request.getContextPath() %>/taskRegister" method="post" class="form">
			<div class="form-item">
				<input type="text" name="title" placeholder="Title" required/>
			</div>
			<div class="form-item">
				<input type="text" name="description" placeholder="Description"/>
			</div>
			<button type="submit">Save</button>
		</form>
		
	</div>
	
	<script>
		const inputs = document.querySelectorAll('.form .form-item input');

		inputs.forEach((input) => {
		    // Armazenar o valor original do placeholder
		    const originalPlaceholder = input.getAttribute('placeholder');

		    input.addEventListener('focus', () => {
		        input.dataset.placeholder = originalPlaceholder;
		        input.removeAttribute('placeholder');
		    });

		    input.addEventListener('blur', () => {
		        if(!input.value){
		            input.setAttribute('placeholder', originalPlaceholder);
		            input.dataset.placeholder = '';
		        }
		    });
		});
	</script>

</body>
</html>
