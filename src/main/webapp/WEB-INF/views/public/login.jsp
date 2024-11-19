<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>


<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sign In</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>

    <%@ include file="navbar.jsp" %>

    <body class="d-flex flex-column min-vh-100">
        <main class="container d-flex align-items-center justify-content-center">
            <div class="login-container my-5" style="width: 28rem; max-width: 28rem;">
                <h1 class="display-4 text-center my-2">Login</h1>

                <section class="row row-cols-sm-1 row-cols-lg-3 my-3">
                    <!-- Main login form -->
                    <article class="col-12">
                        <form action="login" method="post" class="needs-validation" novalidate>
                            <div class="mb-3">
                                <label for="username" class="form-label visually-hidden">Username</label>
                                <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>
                                <div class="invalid-feedback">
                                    Invalid username
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label visually-hidden">Password</label>
                                <input type="password" class="form-control" id="password" name="password" placeholder="Password" required
                                    pattern="^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" >
                                <div class="invalid-feedback">
                                    Password must be at least 8 characters long, contain an uppercase letter, a number, and a special character.
                                </div>
                            </div>
                            <div class="mb-3 form-check">
                                <input type="checkbox" class="form-check-input" id="rememberMe">
                                <label class="form-check-label" for="rememberMe">Remember me</label>
                            </div>
                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-block btn-primary mb-2">Login</button>
                            </div>
                            <hr class="my-4">

							<div class="d-grid gap-2">
	                            <!-- Google Sign-In Button -->
	                            <button class="btn btn-block btn-primary mb-2" style="background-color: #dd4b39; color: white;" type="button">
	                                <img src="<c:url value='/images/google.svg'/>" width="20" alt="google-icon">
	                                Sign in with Google
	                            </button>

	                            <!-- Facebook Sign-In Button -->
	                            <button class="btn btn-block btn-primary mb-2" style="background-color: #3b5998; color: white;" type="button">
	                                <img src="<c:url value='/images/facebook.svg'/>" width="20" alt="facebook-icon">
	                                Sign in with Facebook
	                            </button>
                            </div>

                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        </form>
                        <p class="my-4">Don't have an account? <a href="register">Register Here</a></p>
                    </article>
                </section>
            </div>
        </main>

        <%@ include file="footer.jsp" %>

    </body>
</html>
