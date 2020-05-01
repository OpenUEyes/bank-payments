package com.company.controllers.bill;

enum BillCommandEnum{

//    GET {
//        {
//            this.command = new BillCommandGet();
//        }
//    },

    NEW {
        {
            this.command = new BillCommandNew();
        }
    },

    SEND {
        {
            this.command = new BillCommandSend();
        }
    },

    TRANSFER {
        {
            this.command = new BillCommandTransfer();
        }
    };

    CommandAction command;

    public CommandAction getCurrentCommand() {
        return command;
    }
}
