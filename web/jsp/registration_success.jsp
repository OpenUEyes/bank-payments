<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>

<p:project>
    <jsp:attribute name="title">
        <title>Success</title>
    </jsp:attribute>
    <jsp:body>
        <main>
            <div class="content bg-reg-success">
                <div class="form form-reg-success">
                    <h4 class="header">Registration was successful</h4>
                    <div class="pagination">
                        <a href="${pageContext.request.contextPath}/jsp/index.jsp"
                           class="link" role="button">To Authentication</a>
                    </div>
                </div>
            </div>
        </main>
    </jsp:body>
</p:project>