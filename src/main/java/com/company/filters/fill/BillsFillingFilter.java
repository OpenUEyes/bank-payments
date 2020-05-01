package com.company.filters.fill;

import com.company.model.Bill;
import com.company.services.BillService;
import lombok.extern.log4j.Log4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Log4j
public class BillsFillingFilter implements Filter {

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
                BillService billService = new BillService();
                Iterable<Bill> bills = null;
                try {
                    bills = billService.findAllByAccountId(accountId);
                } catch (SQLException e) {
                    log.warn(e.getMessage());
                    ((HttpServletResponse) servletResponse).sendRedirect("/jsp/error.jsp");
                }
                request.setAttribute("bills", bills);
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {
    }
}