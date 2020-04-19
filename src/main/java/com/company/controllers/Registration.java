package com.company.controllers;

import com.company.model.Account;
import com.company.services.AccountService;
import lombok.extern.log4j.Log4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Log4j
@WebServlet(urlPatterns = "/registration")
public class Registration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = Account.builder()
                .login(request.getParameter("login"))
                .password(request.getParameter("password"))
                .email(request.getParameter("email"))
                .name(request.getParameter("name"))
                .surname(request.getParameter("surname"))
                .phoneNumber(request.getParameter("phoneNumber"))
                .build();
        AccountService accountService = new AccountService();
        RequestDispatcher dispatcher;
        try {
            Optional<String> saveMessage = accountService.create(account);
            if (saveMessage.isPresent()) {
                request.setAttribute("errorMessage", saveMessage.get());
                dispatcher = getServletContext().getRequestDispatcher("/jsp/registration.jsp");
                dispatcher.forward(request, response);
            } else {
                dispatcher = getServletContext().getRequestDispatcher("/jsp/registration_success.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
            request.setAttribute("errorMessage", "Registration is temporarily unavailable!");
            dispatcher = getServletContext().getRequestDispatcher("/jsp/registration.jsp");
            dispatcher.forward(request, response);
        }
    }
}