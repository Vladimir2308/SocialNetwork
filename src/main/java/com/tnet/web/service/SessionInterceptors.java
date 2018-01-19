package com.tnet.web.service;

import com.tnet.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptors extends HandlerInterceptorAdapter {
    @Autowired
    private UserService service;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String sessionId = request.getSession().getId();
        String sessionUserId = "0";
        if (request.getSession().getAttribute("user") != null) {
            User user = ((User) request.getSession().getAttribute("user"));
            sessionUserId = service.getUserSession(user.getId());
        }
        if (!sessionId.equals(sessionUserId)) {
            response.sendRedirect("/index");
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}