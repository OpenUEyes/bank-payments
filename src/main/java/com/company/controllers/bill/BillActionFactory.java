package com.company.controllers.bill;

import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;

@Log4j
class BillActionFactory {

    CommandBillAction defineCommand(HttpServletRequest request) {
        CommandBillAction current = new CommandDefault();
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            log.warn(e.getMessage());
        }
        return current;
    }
}