package com.company.filters.fill;

import com.company.model.Bill;
import com.company.model.Credit;
import com.company.model.Deposit;
import com.company.model.Type;
import com.company.services.BillService;
import com.company.services.CreditService;
import com.company.services.DepositService;
import lombok.extern.log4j.Log4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@Log4j
public class BillSingleFillingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(false);

        if (null == session) {
            log.warn("Session do not exist");
            ((HttpServletResponse) servletResponse).sendRedirect("/jsp/error.jsp");
        } else {
            Long accountId = (Long) session.getAttribute("accountId");
            if (null == accountId) {
                log.warn("Account id do not exist");
                ((HttpServletResponse) servletResponse).sendRedirect("/jsp/error.jsp");
            } else {

                final BillService billService = new BillService();
                final CreditService creditService = new CreditService();
                final DepositService depositService = new DepositService();

                Long billId = Long.parseLong(request.getParameter("id"));
                try {
                    Optional<Bill> optionalBill = billService.findById(billId, accountId);
                    if (optionalBill.isPresent()) {
                        Bill bill = optionalBill.get();
                        request.setAttribute("bill", bill);

                        Type type = bill.getType();
                        if (Type.CREDIT == type) {
                            Optional<Credit> optionalCredit = creditService.findById(billId, accountId);
                            if (optionalCredit.isPresent()) {
                                Credit credit = optionalCredit.get();
                                request.setAttribute("credit", credit);
                            } else {
                                log.error("Account id:" + accountId + "; Bill id:" + billId
                                        + "; BILL have type:CREDIT, but not attached CREDIT with same id as BILL id present!"
                                        + "Or query with wrong input ACCOUNT.id and BILL.id");
                                ((HttpServletResponse) servletResponse).sendRedirect("/jsp/error.jsp");
                            }

                        } else if (Type.DEPOSIT == type) {
                            Optional<Deposit> optionalDeposit = depositService.findById(billId, accountId);
                            if (optionalDeposit.isPresent()) {
                                Deposit deposit = optionalDeposit.get();
                                request.setAttribute("deposit", deposit);
                            } else {
                                log.error("Account id:" + accountId + "; Bill id:" + billId
                                        + "; BILL have type:DEPOSIT, but not attached DEPOSIT with same id as BILL id present!"
                                                + "Or query with wrong input ACCOUNT.id and BILL.id");
                                ((HttpServletResponse) servletResponse).sendRedirect("/jsp/error.jsp");
                            }
                        }
                    } else {
                        log.error("Bill id:" + billId + "; Account id:" + accountId + "; Query with wrong input ACCOUNT.id and BILL.id");
                        ((HttpServletResponse) servletResponse).sendRedirect("/jsp/error.jsp");
                    }
                } catch (SQLException e) {
                    log.warn(e.getMessage());
                    ((HttpServletResponse) servletResponse).sendRedirect("/jsp/error.jsp");
                }
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {
    }
}