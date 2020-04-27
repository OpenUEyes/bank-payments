<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="p" tagdir="/WEB-INF/tags" %>

<p:project>
    <jsp:attribute name="title">
        <title>Success</title>
    </jsp:attribute>
    <jsp:body>
        <main>
            <div class="content bg-reg-success">
                <h3 class="content-header">Action was successful</h3>
                <form class="form form-bill" method="POST" action="${pageContext.request.contextPath}/bill">
                    <input type="hidden" name="action" value="get">
                    <input type="hidden" name="id" value="${billId}">
                    <h4 class="header">Continue</h4>
                    <button class="submit" type="submit"></button>
                </form>
            </div>
        </main>
    </jsp:body>
</p:project>