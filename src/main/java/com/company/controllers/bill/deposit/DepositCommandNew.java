package com.company.controllers.bill.deposit;

import com.company.controllers.bill.CommandAction;
import com.company.model.Deposit;
import com.company.services.DepositService;
import lombok.extern.log4j.Log4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

@Log4j
class DepositCommandNew implements CommandAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher;
        Double amount = Double.valueOf(request.getParameter("amount"));
        Long billId = Long.valueOf(request.getParameter("id"));
        Deposit deposit = Deposit.builder()
                .id(billId)
                .amount(amount)
                .rate(1.2)
                .start(LocalDate.now())
                .finish(LocalDate.now().plusYears(1))
                .build();
        final DepositService depositService = new DepositService();

        try {
            Optional<String> errorMessage = depositService.create(deposit);
            if (errorMessage.isPresent()) {
                request.setAttribute("errorMessage", errorMessage.get());
                dispatcher = request.getServletContext().getRequestDispatcher("/jsp/bill.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("billId", billId);
                dispatcher = request.getServletContext().getRequestDispatcher("/jsp/bill_success.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException exception) {
            log.warn(exception.getMessage());

            dispatcher = request.getServletContext().getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
