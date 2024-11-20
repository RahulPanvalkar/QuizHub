<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	</head>
	
	<body>

	    <footer class="py-3 my-4">
	        <ul class="nav justify-content-center border-bottom pb-3 mb-3">
                <li class="nav-item"><a href="#" class="custom-nav-link">Home</a></li>
                <li class="nav-item"><a href="#" class="custom-nav-link">Features</a></li>
                <li class="nav-item"><a href="#" class="custom-nav-link">Contact</a></li>
                <li class="nav-item"><a href="#" class="custom-nav-link">FAQs</a></li>
                <li class="nav-item"><a href="#" class="custom-nav-link">About</a></li>
                <li class="nav-item"><a href="#" class="custom-nav-link">Feedback</a></li>
            </ul>
	        <%--<p class="text-center text-body-secondary custom-nav-link">© 2024 <span class="brand-text">QuizHub</span></p>--%>
	        <p class="text-center text-body-secondary">
	            <img src="<c:url value='/images/brand-logo2.svg'/>" alt="user-profile" width="30" class="footer-brand-logo">
	        </p>

	    </footer>

		<script src="<c:url value='/script/form-validation.js'/>" ></script>
	</body>
</html>

