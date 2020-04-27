package com.company.controllers.bill.credit;

import com.company.controllers.bill.CommandAction;
import lombok.extern.log4j.Log4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j
@WebServlet(urlPatterns = "/credit")
public class CreditController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CreditActionFactory actionFactory = new CreditActionFactory();
        CommandAction action = actionFactory.defineCommand(request);
        action.execute(request, response);
    }
}