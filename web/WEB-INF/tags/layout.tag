<%@tag description="layout" pageEncoding="UTF-8" %>
<%@attribute name="css" fragment="true" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html>
<head>
    <jsp:invoke fragment="css"/>
    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png">
</head>
<body>
<jsp:invoke fragment="header"/>
<jsp:doBody/>
<jsp:invoke fragment="footer"/>
</body>
</html>