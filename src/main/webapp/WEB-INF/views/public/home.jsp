<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>Home</title>
	</head>

	<%@ include file="navbar.jsp" %>

	<body>

		<main>
	        <header>
	            <div class="container text-center my-5">
	                <h1 class="display-5 fw-bold my-2 text-white">Welcome</h1>
	                <p class="fs-3 text-white">Challenge your mind, conquer the leaderboard — <strong class="brand-text">QuizHub</strong> is your arena!</p>
	                <div class="d-inline-flex gap-2 mb-5">
	                    <a href="all-quiz" class="d-inline-flex align-items-center btn btn-outline-light btn-lg px-4">
	                        Take a quiz
	                    </a>
	                    <a href="login" class="btn btn-outline-light btn-lg px-4" type="button">
	                        Sign Up
	                    </a>
	                </div>
	            </div>
	        </header>

            <section class="container text-center">
                <h2 class="display-4 text-center my-5">Leaderboard</h2>

                <div class="row row-cols-sm-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 mx-3">
                    <c:forEach var="i" begin="1" end="3">
                        <article class="col-lg-4 position-relative mb-4">
                            <img src="<c:url value='/images/circle-user.svg'/>" class="img-fluid" width="150" height="150" alt="user-profile">
                            <span class="position-absolute top-0 start-90 translate-middle badge rounded-pill bg-success">1</span>
                            <h3 class="fw-normal">@aman</h3>
                            <p>Some representative placeholder content for the three columns of text below the.</p>
                            <p><a class="btn btn-secondary" href="./profile.html">View Profile »</a></p>
                        </article>
                    </c:forEach>
                </div>
            </section>
        </main>

        <%@ include file="footer.jsp" %>

    </body>

</html>
