<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	</head>
	<!-- Example: WEB-INF/views/home.jsp navbar navbar-expand-lg navbar-light bg-light-->
    <%@ include file="header.jsp" %>
	<body>
	    <header class="header">
	        <nav class="navbar navbar-expand-lg bg-body-tertiary navbar-light">
                <div class="container">
                    <a class="navbar-brand" href="home">
                        <img src="<c:url value='/images/brand-logo2.svg'/>" alt="logo" width="30" class="d-inline-block align-text-top mr-4">
                        <strong class="brand-text" hidden>QuizHub</strong>
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                        aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="home"><strong>Home</strong></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="quizzes"><strong>Quiz</strong></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="leaderboard"><strong>Leaderboard</strong></a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                    aria-expanded="false">
                                    Other
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#">Blog</a></li>
                                    <li><a class="dropdown-item" href="#">About</a></li>

                                    <!-- Display logout only if user is logged in -->
                                    <sec:authorize access="isAuthenticated()">
	                                    <li>
	                                        <hr class="dropdown-divider">
	                                    </li>
                                        <li><a class="dropdown-item" href="logout">Logout</a></li>
                                    </sec:authorize>
                                </ul>
                            </li>
                        </ul>

                        <%--<div class="d-flex mb-2 mb-lg-0">
                             <a href="login" class="btn ms-2 btn-nav-login text-white">Sign In</a>
                        </div>--%>

                        <sec:authorize access="isAuthenticated()">
                            <a class="profile-icon" href="#" >
                                <img src="<c:url value='/images/user-light.svg'/>" alt="user-profile" width="30" class="d-inline-block align-text-top mr-4">
                            </a>
                        </sec:authorize>

                    </div>
                </div>
            </nav>
        </header>

	</body>
</html>
