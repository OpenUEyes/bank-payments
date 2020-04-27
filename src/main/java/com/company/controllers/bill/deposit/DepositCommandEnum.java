package com.company.controllers.bill.deposit;

import com.company.controllers.bill.CommandAction;

enum DepositCommandEnum {
    NEW {
        {
            this.command = new DepositCommandNew();
        }
    };

    CommandAction command;

    public CommandAction getCurrentCommand() {
        return command;
    }
}
