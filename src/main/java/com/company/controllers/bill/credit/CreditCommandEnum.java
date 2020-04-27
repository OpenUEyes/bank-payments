package com.company.controllers.bill.credit;

import com.company.controllers.bill.CommandAction;

enum CreditCommandEnum {
    NEW {
        {
            this.command = new CreditCommandNew();
        }
    };

    CommandAction command;

    public CommandAction getCurrentCommand() {
        return command;
    }
}
