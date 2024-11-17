<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <a class="navbar-brand" href="/">QuizHub</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                        aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="home">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="quizzes">Quiz</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="leaderboard">Leaderboard</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                    aria-expanded="false">
                                    Other
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#">Blog</a></li>
                                    <li><a class="dropdown-item" href="#">About</a></li>
                                    <li>
                                        <hr class="dropdown-divider">
                                    </li>
                                    <li><a class="dropdown-item" href="#">Downloads</a></li>
                                </ul>
                            </li>
                        </ul>
                        <%--<form class="d-flex mb-2 mb-lg-0" role="search">
                            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                            <button class="btn btn-outline-success" type="submit">Search</button>
                            <a href="./login.html" class="btn btn-primary ms-2">Login</a>
                        </form>--%>
                        <div class="d-flex mb-2 mb-lg-0">
                             <a href="login" class="btn btn-primary ms-2">Sign In</a>
                        </div>
                    </div>
                </div>
            </nav>
        </header>

	</body>
</html>
