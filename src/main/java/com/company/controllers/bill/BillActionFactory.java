package com.company.controllers.bill;

import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;

@Log4j
class BillActionFactory {

    CommandAction defineCommand(HttpServletRequest request) {
        CommandAction current = new CommandDefault();
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            BillCommandEnum currentEnum = BillCommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            log.warn(e.getMessage());
        }
        return current;
    }
}