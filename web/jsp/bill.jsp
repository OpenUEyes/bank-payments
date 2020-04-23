<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="p" tagdir="/WEB-INF/tags" %>

<p:project>
    <jsp:attribute name="title">
        <title>Bill</title>
    </jsp:attribute>
    <jsp:body>
        <main>
            <div class="content bg-bill">
                <div class="card-info">
                    <div class="card">
                        <p class="bank">Bank Payments Project</p>
                        <img class="barcode" src="../img/card/barcode.png" alt="barcode"/>
                        <p class="id">1234 5678 9012 3456</p>
                        <p class="validity">07/25</p>
                        <p class="owner">Roman Romanenko</p>
                        <img class="type" src="../img/card/mastercard.png" alt="mastercard"/>
                    </div>
                    <div class="content-wrapper">
                        <h3 class="content-header">Here is your card balance and validity:</h3>
                        <p class="content-text">Balance: 10000 USD</p>
                        <p class="content-text">Card valid to date: 04/April/2025</p>
                    </div>
                </div>
                <hr>
                <div class="money-wrapper">
                    <h2 class="content-header">You can take a credit or put a deposit</h2>
                    <div class="money-service">
                        <div class="form form-credit">
                            <h3 class="header">Take credit is simple:</h3>
                            <p class="secret">just 23% per month</p>
                            <form class="body" method="POST" action="${pageContext.request.contextPath}/credit/new">
                                <c:if test="${not empty errorMessage}">
                                    <div class="error">
                                        <p>${errorMessage}</p>
                                    </div>
                                </c:if>
                                <div class="input">
                                    <label for="creditAmount">Amount:</label>
                                    <input class="currency" id="creditAmount" type="text" name="amount" value=""
                                           placeholder="USD"/>
                                </div>
                                <button class="button" type="submit">Take it</button>
                            </form>
                        </div>
                        <div class="form form-deposit">
                            <h3 class="header">Put deposit you can here:</h3>
                            <h4 class="header">We save and multiply your money for you</h4>
                            <form class="body" method="POST" action="${pageContext.request.contextPath}/deposit/new">
                                <c:if test="${not empty errorMessage}">
                                    <div class="error">
                                        <p>${errorMessage}</p>
                                    </div>
                                </c:if>
                                <div class="input">
                                    <label for="depositAmount">Amount:</label>
                                    <input class="currency" id="depositAmount" type="text" name="amount" value=""
                                           placeholder="USD"/>
                                </div>
                                <button class="button" type="submit">Put it</button>
                            </form>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="content-wrapper">
                    <h3 class="content-header">Information about your credit:</h3>
                    <p class="content-text">Debt: 5375.25 USD</p>
                    <p class="content-text">Limit: 10 000</p>
                    <p class="content-text">Percentage: 23% per month</p>
                    <p class="content-text">Start: 04/April/2025</p>
                    <p class="content-text">Deadline: 04/April/2026</p>
                </div>
                <hr>
                <div class="content-wrapper">
                    <h3 class="content-header">Information about your deposit:</h3>
                    <p class="content-text">Amount: 10 000 USD</p>
                    <p class="content-text">Rate: 1.2</p>
                    <p class="content-text">Start: 04/April/2025</p>
                    <p class="content-text">Finish: 04/April/2026</p>
                </div>
            </div>
        </main>
    </jsp:body>
</p:project>