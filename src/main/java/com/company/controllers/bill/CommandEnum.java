package com.company.controllers.bill;

enum CommandEnum {

    GET {
        {
            this.command = new CommandGet();
        }
    },

    NEW {
        {
            this.command = new CommandNew();
        }
    },

    SEND {
        {
            this.command = new CommandSend();
        }
    },

    TRANSFER {
        {
            this.command = new CommandTransfer();
        }
    };

    CommandBillAction command;

    public CommandBillAction getCurrentCommand() {
        return command;
    }
}
