<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Sign in</title>
	
	<style>
		body {
			display: flex;
			min-height: 100vh; /* vh como medida relativa ao viewport */
			overflow: hidden;
			justify-content: center;
			align-items: center;
			font-family: 'Poppins', sans-serif;
			background-color: #f9f8f4;
		}
		
		/* ---------- */
		
		.signin-card {
			position: relative;
   	 		transform: translateY(-5%);
			width: 450px;
    		justify-content: center;
		    padding: 4rem;
		    border-radius: 10px;
		    background: rgba(84, 88, 113, .85);
		    box-shadow: 0 /* Horizontal offset */
		    			0 /* Vertical offset */
		    			10px /* Blur radius */
		    			rgba(0, 0, 0, .3); /* Color and transparency */
		}
		
		.signin-card::before {
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
		
		.signin-card,
		.signin-card-header,
		.signin-card-form,
		.signin-card-form .form-item input,
		.signin-btn, .signup-btn {
    		text-align: center;
		}
		
		/* ---------- */
		
		.signin-card-header {
		    margin-bottom: 2rem;
		}
		
		.signin-card-header h1 {
			margin-bottom: .8rem;
		    font-size: 2.5rem;
		    font-weight: 600;
		    color: #f9f8f4;
		}
		
		.signin-card-header h1+div {
		    font-size: calc(2.5rem * .45);
		    font-weight: 450;
		    text-decoration: underline;
  			opacity: .8;
		}
		
		/* ---------- */
		
		.signin-card-form {
		    display: flex;
		    flex-direction: column;
		    gap: 1.5rem;
		}
		
		.signin-card-form .form-item {
		    position: relative;
		}
		
		.signin-card-form .form-item input {
			padding: 25px;
			background-color: transparent;
			border: none;
		 	border-bottom: 1px solid #A6A7AE;
		 	outline: none;
		 	font-family: 'Poppins';
		  	font-size: calc(2.5rem * .50);
		  	letter-spacing: 2px;
		  	color: #F2F1EC;
		  	opacity: .9;
		}
		
		.signin-card-form .form-item input::placeholder {
		  color: #F2F1EC;
		  opacity: .75;
		}
		
		/* ---------- */
		
		.signin-card button {
		    margin-top: 2rem;
		    margin-left: auto;
		    margin-right: auto;
		    border: none;
		    border-radius: 100px;
		    font-family: 'Poppins';
		    font-size: 1rem;
		    font-weight: bold;
		    letter-spacing: 2px;
		    text-transform: uppercase;
		    box-shadow: 0 2px 4px rgba(0, 0, 0, .1);
		    cursor: pointer;
		    transition: all .3s;
		}
		
		.signin-card button:hover {
		    transform: scale(1.1);
		}
		
		.signin-btn {
			width: 40%;
			margin-bottom: 1rem;
			padding: 1rem;
		    background: f9f8f4;
		    color: #202129;
		}
		
		.signup-btn {
			width: 30%;
			padding: .7rem;
		    background: #343641;
		    color: #f9f8f4;
		}
    </style>
    
</head>
<body>

	<div class="signin-card">
	
		<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/6/63/Bookmark_icon_white.svg/1200px-Bookmark_icon_white.svg.png" 
           	width="15%" alt="logo">
	
		<div class="signin-card-header">
			<h1>Tasklist</h1>
			
			<% if(request.getAttribute("error") != null) { %>
			<div>
				<%= request.getAttribute("error") %>
			</div>
			<% } %>
		</div>

		<form action="<%= request.getContextPath() %>/signIn" method="post" class="signin-card-form">
			<div class="form-item">
				<input type="text" name="username" placeholder="Username" required/>
			</div>
			<div class="form-item">
				<input type="password" name="password" placeholder="Password" required/>
			</div>
			<button type="submit" class="signin-btn">Sign In</button>
		</form>
		
		<form action="<%= request.getContextPath() %>/signUp" method="get">
			<button type="submit" class="signup-btn">Sign Up</button>
		</form>
		
	</div>
	
	<script>
		const inputs = document.querySelectorAll('.signin-card-form .form-item input');

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
