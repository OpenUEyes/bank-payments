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

@Log4j
public class CommandDefault implements CommandAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        BillService service = new BillService();
        Long id = (Long) request.getSession().getAttribute("accountId");
        try {
            Iterable<Bill> bills = service.findAllByAccountId(id);
            request.setAttribute("bills", bills);

            dispatcher = request.getServletContext().getRequestDispatcher("/jsp/bills.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            log.warn(e.getMessage());

            dispatcher = request.getServletContext().getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}