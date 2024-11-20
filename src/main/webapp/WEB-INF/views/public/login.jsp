<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>


<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sign In</title>
        <link rel="stylesheet" href="<c:url value='/stylesheet/form.css'/>">
    </head>

    <%@ include file="navbar.jsp" %>

    <body class="d-flex flex-column min-vh-100">
        <main class="container d-flex align-items-center justify-content-center">
            <div class="form-container my-5" style="width: 28rem; max-width: 28rem;">
                <h1 class="display-4 text-center my-2">Login</h1>

                <section class="row row-cols-sm-1 row-cols-lg-3 mt-4">
                    <!-- Main login form -->
                    <article class="col-12">
                        <form action="login" method="post" class="needs-validation" novalidate>
                            <div class="mb-3">
                                <label for="username" class="form-label visually-hidden">Username</label>
                                <div class="input-group">
                                    <span class="input-group-text"><img src="<c:url value='/images/user.svg'/>" alt="user-icon"></span>
                                    <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>
                                    <div class="invalid-feedback">Invalid username</div>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="password" class="form-label visually-hidden">Password</label>
                                <div class="input-group">
                                    <span class="input-group-text"><img src="<c:url value='/images/lock.svg'/>" alt="lock-icon"></span>
                                    <input type="password" class="form-control" id="password" name="password" placeholder="Password" required
                                        pattern="^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$">
                                    <div class="invalid-feedback">
                                        Password must be at least 8 characters long, contain an uppercase letter, a number, and a special character.
                                    </div>
                                </div>
                            </div>

                            <div class="mb-3 form-check">
                                <input type="checkbox" class="form-check-input" style="cursor: pointer;" id="rememberMe">
                                <label class="form-check-label" for="rememberMe" >Remember me</label>
                            </div>


                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-block btn-primary mb-2">Login</button>
                            </div>

                             <c:if test="${param.error != null}">
                                <div class="form-error">
                                     <span>Invalid username or password</span>
                                 </div>
                            </c:if>


                            <div class="d-flex align-items-center">
                                <hr class="flex-grow-1 my-4">
                                <span class="px-3">OR</span>
                                <hr class="flex-grow-1 my-4">
                            </div>


							<div class="d-grid gap-2">
	                            <!-- Google Sign-In Button -->
	                            <button class="btn btn-block mb-2 btn-google" type="button">
                                    <div class="d-flex justify-content-center align-items-center gap-2">
                                        <img src="<c:url value='/images/google.svg'/>" class="google-icon" alt="google-icon">
                                        Sign in with Google
                                    </div>
                                </button>

	                            <!-- Facebook Sign-In Button -->
	                            <button class="btn btn-block mb-2 btn-facebook" type="button">
                                    <div class="d-flex justify-content-center align-items-center gap-2">
                                        <img src="<c:url value='/images/facebook.svg'/>" class="facebook-icon" alt="facebook-icon">
                                        Sign in with Facebook
                                    </div>
                                </button>

                            </div>

                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        </form>
                        <div class="d-flex align-item-center gap2">
                            <p class="my-4 d-flex align-item-center gap-2">Don't have an account? 
                                <a href="register" class="btn btn-outline-secondary btn-sm">Register Here</a>
                            </p>
                        </div>
                    </article>
                </section>
            </div>
        </main>

        <%@ include file="footer.jsp" %>

    </body>
</html>
