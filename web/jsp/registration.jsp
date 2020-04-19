<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="p" tagdir="/WEB-INF/tags" %>

<p:project>
    <jsp:body>
        <main>
            <div class="content bg">
                <div class="form form-reg">
                    <h3 class="header">Registration</h3>
                    <form class="body" method="POST" action="${pageContext.request.contextPath}/registration">
                        <c:if test="${not empty errorMessage}">
                            <div class="error">
                                <p>${errorMessage}</p>
                            </div>
                        </c:if>
                        <div class="item">
                            <label for="loginID">Login :</label>
                            <input id="loginID" type="text" class="form-control" name="login" value=""
                                   placeholder="Enter login"/>
                        </div>
                        <div class="item">
                            <label for="passwordID">Password :</label>
                            <input id="passwordID" type="password" class="form-control" name="password" value=""
                                   placeholder="Enter password"/>
                        </div>
                        <div class="item">
                            <label for="emailID">Email :</label>
                            <input id="emailID" type="email" class="form-control" name="email" value=""
                                   placeholder="Enter email"/>
                        </div>
                        <div class="item">
                            <label for="nameID">Name :</label>
                            <input id="nameID" type="text" class="form-control" name="name" value=""
                                   placeholder="Enter name"/>
                        </div>
                        <div class="item">
                            <label for="surnameID">Surname :</label>
                            <input id="surnameID" type="text" class="form-control" name="surname" value=""
                                   placeholder="Enter surname"/>
                        </div>
                        <div class="item">
                            <label for="phoneNumberID">Phone number :</label>
                            <input id="phoneNumberID" type="text" class="form-control" name="phoneNumber" value=""
                                   placeholder="Enter phone number"/>
                        </div>
                        <button class="button" type="submit">Register</button>
                    </form>
                    <div class="pagination">
                        <a class="button" href="${pageContext.request.contextPath}/jsp/index.jsp"
                           role="button">To Authentication</a>
                    </div>
                </div>
            </div>
        </main>
    </jsp:body>
</p:project>