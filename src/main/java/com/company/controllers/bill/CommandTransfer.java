package com.company.controllers.bill;

import com.company.model.Bill;
import com.company.services.BillService;
import lombok.extern.log4j.Log4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@Log4j
class CommandTransfer implements CommandBillAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long fromId = Long.valueOf(request.getParameter("senderId"));
        Long toId = Long.valueOf(request.getParameter("recipientId"));
        RequestDispatcher dispatcher;
        final BillService service = new BillService();
        final Long id = (Long) request.getSession().getAttribute("id");

        try {

            Optional<String> errorMessage = service.transfer(fromId, toId, Double.valueOf(request.getParameter("amount")));
            if (errorMessage.isPresent()) {
                Iterable<Bill> bills = service.findAllByAccountId(id);
                request.setAttribute("bills", bills);
                request.setAttribute("errorMessage", errorMessage.get());
                dispatcher = request.getServletContext().getRequestDispatcher("/jsp/bills.jsp");
                dispatcher.forward(request, response);
            } else {
                dispatcher = request.getServletContext().getRequestDispatcher("/jsp/bills_success.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            log.warn(e.getMessage());

            Iterable<Bill> bills = null;
            try {
                bills = service.findAllByAccountId(id);
            } catch (SQLException ex) {
                System.out.println(e.getMessage());
                log.warn(e.getMessage());
            }

            request.setAttribute("bills", bills);
            request.setAttribute("errorMessage", "Service is temporarily unavailable!");
            dispatcher = request.getServletContext().getRequestDispatcher("/jsp/bills.jsp");
            dispatcher.forward(request, response);
        }
    }
}