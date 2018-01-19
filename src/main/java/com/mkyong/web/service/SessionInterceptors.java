package com.mkyong.web.service;

import com.mkyong.web.model.User;
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
        System.out.println( "  sessionId "+ sessionId);
        if (request.getSession().getAttribute("user")!=null) {
            User user=((User) request.getSession().getAttribute("user"));
            sessionUserId = service.getUserSession(user.getId());
            System.out.println("  sessionUserId "+ sessionUserId);
//            sessionUserId = ((User) request.getSession().getAttribute("user")).getSessionId();
//       String SessionId=request.getRequestedSessionId();
        }
        if (!sessionId.equals(sessionUserId)) {
            System.out.println("redirect");
            response.sendRedirect("/index");
            return false;
        }
        return super.preHandle(request, response, handler);
    }


}