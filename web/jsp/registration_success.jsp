<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>

<p:project>
    <jsp:body>
        <div class="container my-5 justify-content-center d-flex align-items-center" style="height: 80vh">
            <div class="row text-center">
                <div>
                    <h3>Registration was successful</h3>
                    <div style="margin-top: 50px">
                        <a href="${pageContext.request.contextPath}/jsp/authentication.jsp"
                           class="btn btn-info btn-block"
                           role="button">To Authentication</a>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</p:project>