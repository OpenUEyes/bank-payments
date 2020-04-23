<%@taglib prefix="l" tagdir="/WEB-INF/tags" %>
<%@attribute name="title" fragment="true" %>

<l:layout>
    <jsp:attribute name="css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
    </jsp:attribute>
    <jsp:attribute name="title">
        <jsp:invoke fragment="title"/>
    </jsp:attribute>

    <jsp:attribute name="header">
        <jsp:include page="/jsp/fragment/header.jsp"/>
    </jsp:attribute>

    <jsp:attribute name="footer">
        <jsp:include page="/jsp/fragment/footer.jsp"/>
    </jsp:attribute>

    <jsp:body>
        <jsp:doBody/>
    </jsp:body>
</l:layout>