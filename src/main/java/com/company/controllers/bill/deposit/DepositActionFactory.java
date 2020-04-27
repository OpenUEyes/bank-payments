package com.company.controllers.bill.deposit;

import com.company.controllers.bill.CommandAction;
import com.company.controllers.bill.CommandDefault;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;

@Log4j
class DepositActionFactory {

    CommandAction defineCommand(HttpServletRequest request) {
        CommandAction current = new CommandDefault();
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            DepositCommandEnum currentEnum = DepositCommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            log.warn(e.getMessage());
        }
        return current;
    }
}