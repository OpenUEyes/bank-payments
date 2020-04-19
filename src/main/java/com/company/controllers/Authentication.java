package com.company.controllers;

import com.company.services.AccountService;
import lombok.extern.log4j.Log4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@Log4j
@WebServlet(urlPatterns = "/authentication")
public class Authentication extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        AccountService accountService = new AccountService();
        RequestDispatcher dispatcher;
        try {
            Optional<Long> id = accountService.getId(login, password);
            if (id.isPresent()) {
                System.out.println(1);
                HttpSession session = request.getSession();
                session.setAttribute("id", id.get());
            } else {
                System.out.println(2);
                request.setAttribute("errorMessage", "Wrong login or password!");
                dispatcher = getServletContext().getRequestDispatcher("/jsp/index.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            System.out.println(3);
            log.warn(e.getMessage());
            request.setAttribute("errorMessage", "Authentication is temporarily unavailable!");
            dispatcher = getServletContext().getRequestDispatcher("/jsp/index.jsp");
            dispatcher.forward(request, response);
        }
        dispatcher = getServletContext().getRequestDispatcher("/jsp/index.jsp");// TODO change jsp
        dispatcher.forward(request, response);
    }
}