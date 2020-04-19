<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="p" tagdir="/WEB-INF/tags" %>

<p:project>
    <jsp:body>
        <main>
            <div class="content hero">
                <div class="form form-index">
                    <h3 class="header">Authentication</h3>
                    <form class="body" method="POST" action="${pageContext.request.contextPath}/authentication">
                        <c:if test="${not empty errorMessage}">
                            <div class="error">
                                <p>${errorMessage}</p>
                            </div>
                        </c:if>
                        <div class="item">
                            <label for="loginID">Login:</label>
                            <input id="loginID" type="text" name="login" value=""
                                   placeholder="Enter login"/>
                        </div>
                        <div class="item">
                            <label for="passwordID">Password:</label>
                            <input id="passwordID" type="password" name="password" value=""
                                   placeholder="Enter password"/>
                        </div>
                        <button class="button" type="submit">Log in</button>
                    </form>
                    <div class="pagination">
                        <a href="${pageContext.request.contextPath}/jsp/registration.jsp"
                           class="button" role="button">To Registration</a>
                    </div>
                </div>
            </div>
        </main>
    </jsp:body>
</p:project>