<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="p" tagdir="/WEB-INF/tags" %>

<p:project>
    <jsp:attribute name="title">
        <title>Bills</title>
    </jsp:attribute>
    <jsp:body>
        <main>
            <div class="content bg-bills">
                <c:if test="${not empty bills}">
                    <div class="form form-transfer">
                        <h3 class="header">Money transfer</h3>
                        <h4 class="header">Select your card and enter recipient card number to send</h4>
                        <form class="transfer" method="POST" action="${pageContext.request.contextPath}/bill">
                            <input type="hidden" name="action" value="send">
                            <label class="field-label" for="senderId1">From:</label>
                            <select class="select" name="senderId" id="senderId1">
                                <c:forEach var="bill" items="${bills}">
                                    <option value="${bill.id}">${bill.number} (${bill.balance})</option>
                                </c:forEach>
                            </select>
                            <label class="field-label" for="field-card">Recipient card number:</label>
                            <input class="field field-card" id="field-card" type="text" name="toId" value=""
                                   placeholder="16 numbers"/>
                            <label class="field-label" for="field-amount1">Amount of money:</label>
                            <input class="field field-amount" id="field-amount1" type="text" name="amount" value=""
                                   placeholder="UAH"/>
                            <button class="button" type="submit">Send</button>
                        </form>
                        <hr>
                        <h4 class="header">Or transfer between your cards</h4>
                        <form class="transfer" method="POST" action="${pageContext.request.contextPath}/bill">
                            <input type="hidden" name="action" value="transfer">
                            <label class="field-label" for="senderId2">From:</label>
                            <select class="select" name="senderId" id="senderId2">
                                <c:forEach var="bill" items="${bills}">
                                    <option value="${bill.id}">${bill.number} (${bill.balance})</option>
                                </c:forEach>
                            </select>
                            <label class="field-label" for="recipientId1">To:</label>
                            <select class="select" name="recipientId" id="recipientId1">
                                <c:forEach var="bill" items="${bills}">
                                    <option value="${bill.id}">${bill.number} (${bill.balance})</option>
                                </c:forEach>
                            </select>
                            <label class="field-label" for="field-amount2">Amount of money:</label>
                            <input class="field field-amount" id="field-amount2" type="text" name="amount" value=""
                                   placeholder="UAH"/>
                            <button class="button" type="submit">Send</button>
                        </form>
                    </div>
                    <hr>
                </c:if>
                <c:if test="${not empty errorMessage}">
                    <div class="content-header error">
                        <p>${errorMessage}</p>
                    </div>
                    <hr>
                </c:if>
                <form class="form form-bill" method="POST" action="${pageContext.request.contextPath}/bill">
                    <input type="hidden" name="action" value="new">
                    <h4 class="header">Add new bill</h4>
                    <button class="submit" type="submit"></button>
                </form>
                <c:if test="${not empty bills}">
                    <hr>
                    <h2 class="content-header">Here is your cards(bills)</h2>
                    <div class="cards">
                        <c:forEach var="card" items="${bills}">
                            <form class="card" method="POST" action="${pageContext.request.contextPath}/jsp/bill.jsp">
                                    <%--                            <input type="hidden" name="action" value="get">--%>
                                <input type="hidden" name="id" value="${card.id}">
                                <p class="bank">Bank Payments Project</p>
                                <img class="barcode" src="../img/card/barcode.png" alt="barcode"/>
                                <p class="id">${card.number}</p>
                                <p class="validity">${card.validity.getMonthValue()}/${card.validity.getYear()}</p>
                                <p class="owner">Roman Romanenko</p>
                                <img class="type" src="../img/card/mastercard.png" alt="mastercard"/>
                                <button class="submit" type="submit"></button>
                            </form>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </main>
    </jsp:body>
</p:project>
