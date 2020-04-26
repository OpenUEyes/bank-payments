package com.company.controllers.bill;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

interface CommandBillAction {

    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}