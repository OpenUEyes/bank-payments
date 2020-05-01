package com.company.filters.validation;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationValidationFilter implements Filter {

    private final static Pattern LOGIN;
    private final static Pattern PASSWORD;
    private final static Pattern EMAIL;
    private final static Pattern NAME;
    private final static Pattern SURNAME;
    private final static Pattern PHONE_NUMBER;

    static {
        LOGIN = Pattern.compile("[A-Za-z0-9][A-Za-z0-9_]{2,13}[A-Za-z0-9]");
        PASSWORD = Pattern.compile("[A-Za-z0-9]{5,20}");
        EMAIL = Pattern.compile("[A-Za-z][A-Za-z0-9]{1,15}@([A-Za-z]{2,7}\\.){1,2}(com|ua|net)");
        NAME = Pattern.compile("[A-Za-z]{3,10}");
        SURNAME = Pattern.compile("[A-Za-z]{3,20}");
        PHONE_NUMBER = Pattern.compile("0(39|67|68|96|97|98|50|66|95|99|63|93|91|92|94)[0-9]{7}");
    }

    @Override
    public void init(FilterConfig filterConfig){
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phoneNumber = request.getParameter("phoneNumber");

        Optional<String> errorMessage = validate(login, password, email, name, surname, phoneNumber);
        if (errorMessage.isPresent()) {
            request.setAttribute("errorMessage", errorMessage.get());
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/jsp/registration.jsp");
            dispatcher.forward(request, response);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {
    }

    private Optional<String> validate(String login, String password, String email, String name, String surname, String phoneNumber) {
        Optional<String> errorMessage = messageReportLogin(login);
        if (errorMessage.isPresent()) {
            return errorMessage;
        }

        errorMessage = messageReportPassword(password);
        if (errorMessage.isPresent()) {
            return errorMessage;
        }

        errorMessage = messageReportEmail(email);
        if (errorMessage.isPresent()) {
            return errorMessage;
        }

        errorMessage = messageReportName(name);
        if (errorMessage.isPresent()) {
            return errorMessage;
        }

        errorMessage = messageReportSurname(surname);
        if (errorMessage.isPresent()) {
            return errorMessage;
        }

        errorMessage = messageReportPhoneNumber(phoneNumber);
        return errorMessage;
    }


    private Optional<String> messageReportLogin(String login) {
        if (login == null || login.isEmpty()) {
            return Optional.of("Empty login");
        }
        Matcher matcher = LOGIN.matcher(login);
        if (!matcher.matches()) {
            return Optional.of("Wrong login. Please use only Latin letters. Not less then 4 symbols");
        }
        return Optional.empty();
    }

    private Optional<String> messageReportPassword(String password) {
        if (password == null || password.isEmpty()) {
            return Optional.of("Empty password");
        }
        Matcher matcher = PASSWORD.matcher(password);
        if (!matcher.matches()) {
            return Optional.of("Wrong password. Please use only Latin letters and digits. Not less than 5 symbols");
        }
        return Optional.empty();
    }

    private Optional<String> messageReportEmail(String email) {
        if (email == null || email.isEmpty()) {
            return Optional.of("Empty email");
        }
        Matcher matcher = EMAIL.matcher(email);
        if (!matcher.matches()) {
            return Optional.of("Wrong email. Use format email@domain(com|ua|net)");
        }
        return Optional.empty();
    }

    private Optional<String> messageReportName(String name) {
        if (name == null || name.isEmpty()) {
            return Optional.of("Empty name");
        }
        Matcher matcher = NAME.matcher(name);
        if (!matcher.matches()) {
            return Optional.of("Wrong name. Please use only Latin letters");
        }
        return Optional.empty();
    }

    private Optional<String> messageReportSurname(String surname) {
        if (surname == null || surname.isEmpty()) {
            return Optional.of("Empty surname");
        }
        Matcher matcher = SURNAME.matcher(surname);
        if (!matcher.matches()) {
            return Optional.of("Wrong surname. Please use only Latin letters");
        }
        return Optional.empty();
    }

    private Optional<String> messageReportPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return Optional.of("Empty phone number");
        }
        Matcher matcher = PHONE_NUMBER.matcher(phoneNumber);
        if (!matcher.matches()) {
            return Optional.of("Wrong phone number. Please, use format xxx1234567 , where xxx code of your mobile operator");
        }
        return Optional.empty();
    }
}