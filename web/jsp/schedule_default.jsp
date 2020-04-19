<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>

<p:project>
    <jsp:body>
        <div class="container my-5">
            <div class="row justify-content-center text-center">
                <h3 style="padding: 25px">Filter</h3>
            </div>
            <div class="row justify-content-center">
                <div class="col-4">
                    <form method="GET" action="${pageContext.request.contextPath}/schedule/default">
                        <input type="hidden" name="search" value="month"/>
                        <div class="row justify-content-center">
                            <div class="form-group" style="min-width: 100px; ">
                                <label for="sel3"> Select month : </label>
                                <select id="sel3" name="month" class="form-control" style="max-width: 150px">
                                        <%--suppress ELValidationInJSP --%>
                                    <c:forEach var="month" items="${months}">
                                        <option value=${month}>${month}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div data-aos="fade-left" data-aos-delay="500" data-aos-once="true"
                                 data-aos-duration="1400" style="padding-top: 30px; padding-left: 30px">
                                <button type="submit" class="btn btn-success btn-md button-block">Search</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-4">
                    <form method="GET" action="${pageContext.request.contextPath}/schedule/default">
                        <input type="hidden" name="search" value="surname"/>
                        <div class="row justify-content-center">
                            <div class="form-group" style="min-width: 150px; ">
                                <label for="sel4"> Select master surname : </label>
                                <select id="sel4" name="masterSurname" class="form-control" style="max-width: 190px">
                                        <%--suppress ELValidationInJSP --%>
                                    <c:forEach var="surname" items="${surnames}">
                                        <option value=${surname}>${surname}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div data-aos="fade-left" data-aos-delay="500" data-aos-once="true"
                                 data-aos-duration="1400" style="padding-top: 30px; padding-left: 30px">
                                <button type="submit" class="btn btn-success btn-md button-block">Search</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-4">
                    <form method="GET" action="${pageContext.request.contextPath}/schedule/default">
                        <input type="hidden" name="search" value="day"/>
                        <div class="row justify-content-center">
                            <div class="form-group" style="min-width: 150px">
                                <label for="sel2">Select day : </label>
                                <select id="sel2" name="day" class="form-control" style="max-width: 100px">
                                        <%--suppress ELValidationInJSP --%>
                                    <c:forEach var="day" items="${days}">
                                        <option value=${day}>${day}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div data-aos="fade-left" data-aos-delay="500" data-aos-once="true"
                                 data-aos-duration="1400" style="padding-top: 30px; margin-left: -20px">
                                <button type="submit" class="btn btn-success btn-md button-block">Search</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row justify-content-center text-center">
                <a href="${pageContext.request.contextPath}/schedule/default" class="btn btn-danger btn-md button-block"
                   style="width: 200px; background: #26b978" role="button">Reset</a>
            </div>
        </div>
        <div class="container my-5 text-center">
            <h3 style="padding: 25px">Schedule</h3>
            <div class="row justify-content-center">
                <div class="col-10">
                    <table class="table table-hover text-center">
                        <thead>
                        <tr>
                            <th>Day</th>
                            <th>Month</th>
                            <th>Time</th>
                            <th>Master name</th>
                            <th>Master surname</th>
                            <th>Status</th>
                            <th style="border: none"></th>
                        </tr>
                        </thead>
                        <tbody>
                            <%--suppress ELValidationInJSP --%>
                        <c:forEach var="record" items="${records}">
                            <tr>
                                <td>${record.day}</td>
                                <td>${record.month}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${record.minute==0}">${record.hour}:00</c:when>
                                        <c:otherwise>${record.hour}:${record.minute}</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${record.masterName}</td>
                                <td>${record.masterSurname}</td>
                                <td>${record.status}</td>
                                <td style="border: none">
                                    <c:choose>
                                        <c:when test="${record.status=='CLOSED'}">
                                            <%--do nothing --%>
                                            <button type="button" disabled class="btn btn-danger btn-md">Register
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <form style="margin: 5px" method="POST"
                                                  action="${pageContext.request.contextPath}/record/save">
                                                <input type="hidden" name="day" value=${record.day}/>
                                                <input type="hidden" name="month" value=${record.month}/>
                                                <input type="hidden" name="year" value=${record.year}/>
                                                <input type="hidden" name="hour" value=${record.hour}/>
                                                <input type="hidden" name="minute" value=${record.minute}/>
                                                <input type="hidden" name="masterId" value=${record.masterId}/>
                                                <input type="hidden" name="masterName" value=${record.masterName}/>
                                                <input type="hidden" name="masterSurname"
                                                       value=${record.masterSurname}/>
                                                <input type="hidden" name="clientId" value=${record.clientId}/>
                                                <input type="hidden" name="status" value=${record.status}/>
                                                <button type="submit" class="btn btn-success btn-md button-block">
                                                    Register
                                                </button>
                                            </form>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <div class="error-block" data-aos="fade-left" data-aos-delay="100" data-aos-once="true"
                         data-aos-duration="1400">
                        <p style="font-weight: bold; color: indianred;">${errorMessage}</p>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</p:project>