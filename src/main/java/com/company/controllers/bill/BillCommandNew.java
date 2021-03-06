package com.company.controllers.bill;

import com.company.model.Bill;
import com.company.services.BillService;
import lombok.extern.log4j.Log4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Log4j
class BillCommandNew implements CommandAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int billValidityYearsPeriod = 4;
        Bill bill = Bill.builder()
                .balance(0.0)
                .validity(LocalDate.now().plusYears(billValidityYearsPeriod))
                .accountId((Long) request.getSession().getAttribute("accountId"))
                .build();
        RequestDispatcher dispatcher;
        final BillService service = new BillService();

        try {
            Optional<String> errorMessage = service.create(bill);
            if (errorMessage.isPresent()) {
                request.setAttribute("errorMessage", errorMessage.get());
                dispatcher = request.getServletContext().getRequestDispatcher("/jsp/bills.jsp");
                dispatcher.forward(request, response);
            } else {
                dispatcher = request.getServletContext().getRequestDispatcher("/jsp/bills.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
            request.setAttribute("errorMessage", "Service is temporarily unavailable!");
            dispatcher = request.getServletContext().getRequestDispatcher("/jsp/bills.jsp");
            dispatcher.forward(request, response);
        }
    }
}