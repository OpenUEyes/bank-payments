package com.company.controllers.bill.deposit;

import com.company.controllers.bill.CommandAction;
import lombok.extern.log4j.Log4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j
@WebServlet(urlPatterns = "/deposit")
public class DepositController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DepositActionFactory actionFactory = new DepositActionFactory();
        CommandAction action = actionFactory.defineCommand(request);
        action.execute(request, response);
    }
}