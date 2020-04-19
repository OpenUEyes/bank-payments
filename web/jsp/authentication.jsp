<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>

<p:project>
    <jsp:body>
        <main>
            <div class="container my-5">
                <div class="row justify-content-center text-center">
                    <div class="col-5">
                        <h3 data-aos="fade-left" data-aos-delay="100" data-aos-once="true" data-aos-duration="1800">
                            Authentication</h3>
                        <form method="POST" action="${pageContext.request.contextPath}/authentication">
                            <div class="form-group text-left" data-aos="fade-right" data-aos-delay="200"
                                 data-aos-once="true"
                                 data-aos-duration="1700">
                                <label for="loginID">Login :</label>
                                <input id="loginID" type="text" class="form-control" name="login" value=""
                                       placeholder="Enter login"/>
                            </div>
                            <div class="form-group text-left" data-aos="fade-right" data-aos-delay="500"
                                 data-aos-once="true"
                                 data-aos-duration="1400">
                                <label for="passwordID">Password :</label>
                                <input id="passwordID" type="password" class="form-control" name="password" value=""
                                       placeholder="Enter password"/>
                            </div>
                            <div class="error-block" data-aos="fade-left" data-aos-delay="100" data-aos-once="true"
                                 data-aos-duration="1400">
                                <p style="font-weight: bold; color: indianred;">${errorMessage}</p>
                            </div>
                            <div class="text-right" data-aos="fade-left" data-aos-delay="500" data-aos-once="true"
                                 data-aos-duration="1400">
                                <button type="submit" class="btn btn-success btn-md button-block">Log in</button>
                            </div>
                        </form>
                        <div class="text-right" data-aos="fade-left" data-aos-delay="900" data-aos-once="true"
                             data-aos-duration="1000">
                            <a href="${pageContext.request.contextPath}/jsp/registration.jsp"
                               class="btn btn-success button-block" role="button">To Registration</a>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </jsp:body>
</p:project>