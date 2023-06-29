<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Sign up</title>
	
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
		
		.signup-card {
			position: relative;
			transform: translateY(-5%);
			width: 450px;
    		justify-content: center;
		    padding: 4rem;
		    border-radius: 10px;
		    background: rgba(233, 232, 231, 255);
		    box-shadow: 0 0 10px rgba(0, 0, 0, .3);
		}
		
		.signup-card::before {
		    content: '';
		    position: absolute;
		    z-index: -1;
		    inset: 0;
		    transform: rotate(-6deg);
		    border-radius: inherit;
		    background: rgba(84, 88, 113, .1);
		    box-shadow: inherit;
		}
		
		/* ---------- */
		
		.signup-card,
		.signin-card-warning,
		.signup-card-form,
		.signup-card-form .form-item input,
		.signup-btn {
    		text-align: center;
		}
		
		/* ---------- */
		
		.signin-card-warning {
		    margin-bottom: 2rem;
		}
		
		
		.signin-card-warning div {
		    font-size: calc(2.5rem * .45);
		    font-weight: 450;
		    text-decoration: underline;
  			opacity: .8;
		}
		
		/* ---------- */
		
		.signup-card-form {
		    display: flex;
		    flex-direction: column;
		    gap: 1.5rem;
		}
		
		.signup-card-form .form-item {
		    position: relative;
		}
		
		.signup-card-form .form-item input {
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
		
		.signup-card-form .form-item input::placeholder {
		  color: #202129;
		  opacity: .75;
		}
		
		/* ---------- */
		
		button {
			margin-left: auto;
		    margin-right: auto;
		    border: none;
		    font-family: 'Poppins';
		    font-weight: bold;
		    box-shadow: 0 2px 4px rgba(0, 0, 0, .3);
		    cursor: pointer;
		    transition: all .3s;
		}
		
		.signup-btn {
			width: 40%;
			margin-top: 2rem;
		    border-radius: 100px;
			margin-bottom: 1rem;
			padding: 1rem;
			font-size: 1rem;
			letter-spacing: 2px;
			text-transform: uppercase;
		    background: f9f8f4;
		    color: #202129;
		}
		
		.signup-btn:hover {
		    transform: scale(1.1);
		    
		}
		
		.return-btn {
			padding: .7rem;
			font-size: 1rem;
			letter-spacing: 5px;
			text-transform: lowercase;
			background: #EA4354;
		    color: #f9f8f4;
		}
		
		.return-btn:hover {
		    letter-spacing: 8px;
		}
    </style>
	
</head>
<body>

	<form action="<%= request.getContextPath() %>/signIn" method="get">
			<button type="submit" class="return-btn">Return</button>
	</form>

	<div class="signup-card">
	
		<div class="signin-card-warning">	
			<% if(request.getAttribute("error") != null) { %>
			<div>
				<%= request.getAttribute("error") %>
			</div>
			<% } %>
		</div>
		
		<form name="signup-form" action="<%= request.getContextPath() %>/signUp" method="post"
			onsubmit="return validateForm()" class="signup-card-form">
			<div class="form-item">
				<input type="text" name="username" placeholder="Username" required/>
			</div>
			<div class="form-item">
				<input type="text" name="name" placeholder="Name" required/>
			</div>
			<div class="form-item">
				<input type="email" name="email" placeholder="Email" required/>
			</div>
			<div class="form-item">
				<input type="password" name="password" placeholder="Password" required/>
			</div>
			<button type="submit" class="signup-btn">Sign Up</button>
		</form>
		
	</div>

	<script>
		function validateForm(){
			var username = document.forms["signup-form"]["username"].value;
			var password = document.forms["signup-form"]["password"].value;

			// Verifica se o username possui ao menos 2 caracteres, sendo um deles uma letra
			if(username.length < 2 || !/[a-zA-Z]/.test(username)){
				alert("Username must have at least 2 characters, including one letter.");
				return false;
			}else

			// Verifica se a senha possui ao menos 6 caracteres
			if(password.length < 6){
				alert("Password must have at least 6 characters.");
				return false;
			}
			
			return true;
		}
		
		const inputs = document.querySelectorAll('.signup-card-form .form-item input');

		inputs.forEach((input) => {
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
