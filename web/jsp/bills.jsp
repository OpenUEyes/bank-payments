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
                <div class="form form-transfer">
                    <h3 class="header">Money transfer</h3>
                    <h4 class="header">Select your card and enter recipient card number to send</h4>
                    <form class="transfer" method="POST" action="${pageContext.request.contextPath}/transfer">
                        <c:if test="${not empty errorMessage}">
                            <div class="error">
                                <p>${errorMessage}</p>
                            </div>
                        </c:if>
                        <select class="select">
                            <option selected>Press here to select card</option>
                            <option value="cardID">1234 5678 9012 3456 (150 000 000.00)</option>
                            <option value="cardID">1234 5678 9012 3456 (150 000 000.00)</option>
                            <option value="cardID">1234 5678 9012 3456 (0.00)</option>
                        </select>
                        <label class="field-label" for="field-card">Recipient card number:</label>
                        <input class="field field-card" id="field-card" type="text" name="cardNumber" value=""
                               placeholder="16 numbers"/>
                        <label class="field-label" for="field-amount">Amount of money:</label>
                        <input class="field field-amount" id="field-amount" type="text" name="cardNumber" value=""
                               placeholder="USD"/>
                        <button class="button" type="submit">Send</button>
                    </form>
                    <h4 class="header">Or transfer between your cards</h4>
                    <form class="transfer" method="POST" action="${pageContext.request.contextPath}/transfer">
                        <c:if test="${not empty errorMessage}">
                            <div class="error">
                                <p>${errorMessage}</p>
                            </div>
                        </c:if>
                        <select class="select">
                            <option selected>Press here to select card from</option>
                            <option value="cardID">1234 5678 9012 3456 (150 000 000.00)</option>
                            <option value="cardID">1234 5678 9012 3456 (150 000 000.00)</option>
                            <option value="cardID">1234 5678 9012 3456 (0.00)</option>
                        </select>
                        <select class="select">
                            <option selected>Press here to select card to</option>
                            <option value="cardID">1234 5678 9012 3456 (150 000 000.00)</option>
                            <option value="cardID">1234 5678 9012 3456 (150 000 000.00)</option>
                            <option value="cardID">1234 5678 9012 3456 (0.00)</option>
                        </select>
                        <label class="field-label" for="field-amount">Amount of money:</label>
                        <input class="field field-amount" id="field-amount" type="text" name="cardNumber" value=""
                               placeholder="USD"/>
                        <button class="button" type="submit">Send</button>
                    </form>
                </div>
                <hr>
                <form class="form form-bill" method="POST" action="${pageContext.request.contextPath}/bill/new">
                    <h4 class="header">Add new bill</h4>
                    <button class="submit" type="submit"></button>
                </form>
                <hr>
                <h2 class="content-header">Here is your cards(bills)</h2>
                <div class="cards">
                    <form class="card" method="POST" action="${pageContext.request.contextPath}/bill/get">
                        <p class="bank">Bank Payments Project</p>
                        <img class="barcode" src="../img/card/barcode.png" alt="barcode"/>
                        <p class="id">1234 5678 9012 3456</p>
                        <p class="validity">07/25</p>
                        <p class="owner">Roman Romanenko</p>
                        <img class="type" src="../img/card/mastercard.png" alt="mastercard"/>
                        <button class="submit" type="submit"></button>
                    </form>
                    <form class="card" method="POST" action="${pageContext.request.contextPath}/bill/get">
                        <p class="bank">Bank Payments Project</p>
                        <img class="barcode" src="../img/card/barcode.png" alt="barcode"/>
                        <p class="id">1234 5678 9012 3456</p>
                        <p class="validity">07/25</p>
                        <p class="owner">Roman Romanenko</p>
                        <img class="type" src="../img/card/mastercard.png" alt="mastercard"/>
                        <button class="submit" type="submit"></button>
                    </form>
                    <form class="card" method="POST" action="${pageContext.request.contextPath}/bill/get">
                        <p class="bank">Bank Payments Project</p>
                        <img class="barcode" src="../img/card/barcode.png" alt="barcode"/>
                        <p class="id">1234 5678 9012 3456</p>
                        <p class="validity">07/25</p>
                        <p class="owner">Roman Romanenko</p>
                        <img class="type" src="../img/card/mastercard.png" alt="mastercard"/>
                        <button class="submit" type="submit"></button>
                    </form>
                    <form class="card" method="POST" action="${pageContext.request.contextPath}/bill/get">
                        <p class="bank">Bank Payments Project</p>
                        <img class="barcode" src="../img/card/barcode.png" alt="barcode"/>
                        <p class="id">1234 5678 9012 3456</p>
                        <p class="validity">07/25</p>
                        <p class="owner">Roman Romanenko</p>
                        <img class="type" src="../img/card/mastercard.png" alt="mastercard"/>
                        <button class="submit" type="submit"></button>
                    </form>
                    <form class="card" method="POST" action="${pageContext.request.contextPath}/bill/get">
                        <p class="bank">Bank Payments Project</p>
                        <img class="barcode" src="../img/card/barcode.png" alt="barcode"/>
                        <p class="id">1234 5678 9012 3456</p>
                        <p class="validity">07/25</p>
                        <p class="owner">Roman Romanenko</p>
                        <img class="type" src="../img/card/mastercard.png" alt="mastercard"/>
                        <button class="submit" type="submit"></button>
                    </form>

                    <form class="card" method="POST" action="${pageContext.request.contextPath}/bill/get">
                        <p class="bank">Bank Payments Project</p>
                        <img class="barcode" src="../img/card/barcode.png" alt="barcode"/>
                        <p class="id">1234 5678 9012 3456</p>
                        <p class="validity">07/25</p>
                        <p class="owner">Roman Romanenko</p>
                        <img class="type" src="../img/card/mastercard.png" alt="mastercard"/>
                        <button class="submit" type="submit"></button>
                    </form>
                    <form class="card" method="POST" action="${pageContext.request.contextPath}/bill/get">
                        <p class="bank">Bank Payments Project</p>
                        <img class="barcode" src="../img/card/barcode.png" alt="barcode"/>
                        <p class="id">1234 5678 9012 3456</p>
                        <p class="validity">07/25</p>
                        <p class="owner">Roman Romanenko</p>
                        <img class="type" src="../img/card/mastercard.png" alt="mastercard"/>
                        <button class="submit" type="submit"></button>
                    </form>
                    <form class="card" method="POST" action="${pageContext.request.contextPath}/bill/get">
                        <p class="bank">Bank Payments Project</p>
                        <img class="barcode" src="../img/card/barcode.png" alt="barcode"/>
                        <p class="id">1234 5678 9012 3456</p>
                        <p class="validity">07/25</p>
                        <p class="owner">Roman Romanenko</p>
                        <img class="type" src="../img/card/mastercard.png" alt="mastercard"/>
                        <button class="submit" type="submit"></button>
                    </form>
                    <form class="card" method="POST" action="${pageContext.request.contextPath}/bill/get">
                        <p class="bank">Bank Payments Project</p>
                        <img class="barcode" src="../img/card/barcode.png" alt="barcode"/>
                        <p class="id">1234 5678 9012 3456</p>
                        <p class="validity">07/25</p>
                        <p class="owner">Roman Romanenko</p>
                        <img class="type" src="../img/card/mastercard.png" alt="mastercard"/>
                        <button class="submit" type="submit"></button>
                    </form>
                </div>
            </div>
        </main>
    </jsp:body>
</p:project>
