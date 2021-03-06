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
                        <p class="id">${bill.number}</p>
                        <p class="validity">${bill.validity.getMonthValue()}/${bill.validity.getYear()}</p>
                        <p class="owner">Roman Romanenko</p>
                        <img class="type" src="../img/card/mastercard.png" alt="mastercard"/>
                    </div>
                    <div class="content-wrapper">
                        <h3 class="content-header">Here is your card balance and validity:</h3>
                        <p class="content-text">Balance: ${bill.balance} UAH</p>
                        <p class="content-text">Card valid to date: ${bill.validity}</p>
                    </div>
                </div>
                <c:if test="${bill.type eq 'UNSIGNED'}">
                    <hr>
                    <div class="money-wrapper">
                        <h2 class="content-header">You can take a credit or put a deposit</h2>
                        <c:if test="${not empty errorMessage}">
                            <div class="content-header error">
                                <p>${errorMessage}</p>
                            </div>
                        </c:if>
                        <div class="money-service">
                            <div class="form form-credit">
                                <h3 class="header">Take credit is simple:</h3>
                                <p class="secret">just 23% per month</p>
                                <form class="body" method="POST" action="${pageContext.request.contextPath}/credit">
                                    <input type="hidden" name="action" value="new">
                                    <input type="hidden" name="id" value="${bill.id}">
                                    <div class="input">
                                        <label for="creditAmount">Amount:</label>
                                        <input class="currency" id="creditAmount" type="text" name="amount" value=""
                                               placeholder="UAH"/>
                                    </div>
                                    <button class="button" type="submit">Take it</button>
                                </form>
                            </div>
                            <div class="form form-deposit">
                                <h3 class="header">Put deposit you can here:</h3>
                                <h4 class="header">We save and multiply your money for you</h4>
                                <form class="body" method="POST" action="${pageContext.request.contextPath}/deposit">
                                    <input type="hidden" name="action" value="new">
                                    <input type="hidden" name="id" value="${bill.id}">
                                    <div class="input">
                                        <label for="depositAmount">Amount:</label>
                                        <input class="currency" id="depositAmount" type="text" name="amount" value=""
                                               placeholder="UAH"/>
                                    </div>
                                    <button class="button" type="submit">Put it</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${bill.type eq 'CREDIT'}">
                    <hr>
                    <div class="content-wrapper">
                        <h3 class="content-header">Information about your credit:</h3>
                        <p class="content-text">Debt: ${credit.debt} UAH</p>
                        <p class="content-text">Limit: ${credit.limit}</p>
                        <p class="content-text">Percentage: ${credit.percentage}% per month</p>
                        <p class="content-text">Start: ${credit.start}</p>
                        <p class="content-text">Deadline: ${credit.deadline}</p>
                    </div>
                </c:if>
                <c:if test="${bill.type eq 'DEPOSIT'}">
                    <hr>
                    <div class="content-wrapper">
                        <h3 class="content-header">Information about your deposit:</h3>
                        <p class="content-text">Amount: ${deposit.amount} UAH</p>
                        <p class="content-text">Rate: ${deposit.rate}</p>
                        <p class="content-text">Start: ${deposit.start}</p>
                        <p class="content-text">Finish: ${deposit.finish}</p>
                    </div>
                </c:if>
            </div>
        </main>
    </jsp:body>
</p:project>