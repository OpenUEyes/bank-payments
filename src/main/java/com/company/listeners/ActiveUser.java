package com.company.listeners;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/*
* LOG IN
* ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
      if (activeUser == null) {
          activeUser = new ActiveUser();
          session.setAttribute("activeUser", activeUser);
      }
* LOG OUT
* HttpSession session = req.getSession(false);
      if (session != null && session.getAttribute("activeUser") != null) {
          System.out.println("-- removing activeUser attribute from session --");
          session.removeAttribute("activeUser");
      }
* LOG OUT 2
* HttpSession session = req.getSession(false);
      if (session != null) {
          System.out.println("-- invalidating session --");
          session.invalidate();
      }
*/

@Log4j
@NoArgsConstructor
public class ActiveUser implements HttpSessionBindingListener {
    private Long userId;

    public ActiveUser(Long userId) {
        this.userId = userId;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        log.info("User: account_id=" + userId + " logged in.");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        log.info("User: account_id=" + userId + " logged out.");
    }
}