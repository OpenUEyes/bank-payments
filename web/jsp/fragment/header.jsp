<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<header>
    <nav class="navbar">
        <p class="logo">Bank Payments Project</p>
        <ul class="menu">
            <li><a href="${pageContext.request.contextPath}/jsp/index.jsp" class="input">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/jsp/bills.jsp" class="input">Bills</a></li>
            <li><a href="#" class="input">Contact</a></li>
            <c:if test="${not empty activeUser}">
                <li><a href="${pageContext.request.contextPath}/logout" class="logout input">Logout</a></li>
            </c:if>
        </ul>
    </nav>
</header>