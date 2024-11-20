<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>


<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Register</title>
        <link rel="stylesheet" href="<c:url value='/stylesheet/form.css'/>">
    </head>

    <%@ include file="navbar.jsp" %>

    <body class="d-flex flex-column min-vh-100">
        <main class="container d-flex align-items-center justify-content-center">
            <div class="form-container my-5" style="width: 28rem; max-width: 28rem;">
                <h1 class="display-4 text-center my-2">Register</h1>

                <section class="row row-cols-sm-1 row-cols-lg-3 mt-4">
                    <article class="col-12">
                        <form action="register" method="post" class="needs-validation" novalidate>
                            <div class="row">
                                <div class="col-md-6 mb-4">
                                    <div class="input-group">
                                            <span class="input-group-text"><img src="<c:url value='/images/user.svg'/>" alt="user-icon"></span>
                                            <input type="text" id="firstName" name="firstName" class="form-control" placeholder="First Name" required/>
                                            <div class="invalid-feedback">Invalid first name</div>
                                    </div>
                                </div>
                                <div class="col-md-6 mb-4">
                                    <div class="input-group">
                                        <span class="input-group-text"><img src="<c:url value='/images/user.svg'/>" alt="user-icon"></span>
                                        <input type="text" id="lastName" name="lastName" class="form-control" placeholder="Last Name" required/>
                                        <div class="invalid-feedback">Invalid last name</div>
                                    </div>
                                </div>
                            </div>

                            <div class="mb-3">
                                <div class="input-group">
                                    <span class="input-group-text"><img src="<c:url value='/images/circle-user.svg'/>" alt="user-icon"></span>
                                    <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>
                                    <div class="invalid-feedback">Invalid username</div>
                                </div>
                            </div>
                            <div class="mb-3">
                                <div class="input-group">
                                    <span class="input-group-text"><img src="<c:url value='/images/envelope.svg'/>" alt="user-icon"></span>
                                    <input type="email" class="form-control" id="emailId" name="emailId" placeholder="Email ID" required>
                                    <div class="invalid-feedback">Invalid Email Id</div>
                                </div>
                            </div>
	                            <div class="mb-3">
                                    <div class="input-group">
                                        <span class="input-group-text"><img src="<c:url value='/images/phone.svg'/>" alt="user-icon"></span>
                                        <input type="text" class="form-control" id="phone" name="phone" placeholder="Mobile Number" required
                                        maxlength="10" pattern="\d{10}">
                                        <div class="invalid-feedback">Mobile number must be of 10 digits.</div>
                                    </div>
	                            </div>
                            <div class="mb-3">
                                <div class="input-group">
                                    <span class="input-group-text"><img src="<c:url value='/images/lock.svg'/>" alt="user-icon"></span>
                                    <input type="password" class="form-control" id="password" name="password" placeholder="Password" required
                                        pattern="^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" >
                                    <div class="invalid-feedback">
                                        Password must be at least 8 characters long, contain an uppercase letter, a number and a special character.
                                    </div>
                                </div>
                            </div>
                            <div class="mb-3 form-check">
                                <input type="checkbox" class="form-check-input" style="cursor: pointer;" id="terms" required>
                                <label class="form-check-label" for="terms" >Agree to Terms and Conditions</label>
                                <div class="invalid-feedback">You must agree to the terms and conditions.</div>
                            </div>

                            <div class="d-flex justify-content-end pt-3 gap-2">
                              <button type="reset" class="btn btn-dark">Reset all</button>
                              <button type="submit" class="btn btn-warning">Submit form</button>
                            </div>

                            <c:if test="${param.error != null}">
                                <div class="form-error">
                                     <span>Invalid username or password</span>
                                 </div>
                            </c:if>

                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </article>
                </section>
            </div>
        </main>

        <%@ include file="footer.jsp" %>

    </body>
</html>
