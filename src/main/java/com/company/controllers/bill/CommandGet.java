package com.company.controllers.bill;

import com.company.model.Bill;
import com.company.services.BillService;
import lombok.extern.log4j.Log4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Log4j
class CommandGet implements CommandBillAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        final BillService service = new BillService();
        try {
            Optional<Bill> bill = service.findById(Long.parseLong(request.getParameter("id")));
            if (bill.isPresent()) {
                request.setAttribute("bill", bill.get());
                dispatcher = request.getServletContext().getRequestDispatcher("/jsp/bill.jsp");
                dispatcher.forward(request, response);
            } else {
                log.warn(bill + " must present!!");
                request.setAttribute("errorMessage", "Service is temporarily unavailable!");
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