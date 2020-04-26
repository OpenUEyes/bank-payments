package com.company.controllers.bill;

import lombok.extern.log4j.Log4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j
@WebServlet(urlPatterns = "/bill")
public class BillController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BillActionFactory actionFactory = new BillActionFactory();
        CommandBillAction action = actionFactory.defineCommand(request);
        action.execute(request, response);
    }
}