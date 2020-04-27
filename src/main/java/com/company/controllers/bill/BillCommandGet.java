package com.company.controllers.bill;

import com.company.model.Bill;
import com.company.model.Credit;
import com.company.model.Deposit;
import com.company.model.Type;
import com.company.services.BillService;
import com.company.services.CreditService;
import com.company.services.DepositService;
import lombok.extern.log4j.Log4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@Log4j
class BillCommandGet implements CommandAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        final BillService billService = new BillService();
        final CreditService creditService = new CreditService();
        final DepositService depositService = new DepositService();
        Long billId = Long.parseLong(request.getParameter("id"));
        try {
            Optional<Bill> optionalBill = billService.findById(billId);
            if (optionalBill.isPresent()) {
                Bill bill = optionalBill.get();
                request.setAttribute("bill", bill);

                Type type = bill.getType();
                if (Type.CREDIT == type) {
                    Optional<Credit> optionalCredit = creditService.findById(billId);
                    if (optionalCredit.isPresent()) {
                        Credit credit = optionalCredit.get();
                        request.setAttribute("credit", credit);
                    } else {
                        log.error("Bill id:" + billId + "have type:CREDIT but can't find CREDIT with same id as bill id!");
                        request.setAttribute("errorMessage", "Service is temporarily unavailable!");
                    }

                } else if (Type.DEPOSIT == type) {
                    Optional<Deposit> optionalDeposit = depositService.findById(billId);
                    if (optionalDeposit.isPresent()) {
                        Deposit deposit = optionalDeposit.get();
                        request.setAttribute("deposit", deposit);
                    } else {
                        log.error("Bill id:" + billId + "have type:DEPOSIT but can't find attached DEPOSIT with same id as bill id!");
                        request.setAttribute("errorMessage", "Service is temporarily unavailable!");
                    }
                }

                dispatcher = request.getServletContext().getRequestDispatcher("/jsp/bill.jsp");
                dispatcher.forward(request, response);
            } else {
                log.warn("Expected id:" + billId + " POST method:bill-get. Should use when bill exist in database");
                request.setAttribute("errorMessage", "Service is temporarily unavailable!");
                dispatcher = request.getServletContext().getRequestDispatcher("/jsp/bills.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException exception) {
            log.warn(exception.getMessage());

            dispatcher = request.getServletContext().getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}