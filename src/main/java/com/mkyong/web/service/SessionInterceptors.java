package com.mkyong.web.service;

import com.mkyong.web.model.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptors extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        String sessionId = request.getSession().getId();
        String sessionUserId = "0";
        System.out.println( "  sessionId "+ sessionId);
        if (request.getSession().getAttribute("user")!=null) {
            sessionUserId = ((User) request.getSession().getAttribute("user")).getSessionId();
//       String SessionId=request.getRequestedSessionId();
        }
        if (!sessionId.equals(sessionUserId)) {
            response.sendRedirect("/index");
            return false;
        }
        return super.preHandle(request, response, handler);
    }


}