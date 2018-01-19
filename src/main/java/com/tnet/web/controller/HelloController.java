package com.tnet.web.controller;

import com.tnet.web.model.User;
import com.tnet.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
@SessionAttributes("user")
public class HelloController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView showMessage(HttpSession session, @RequestParam String email,
                                    @RequestParam String pass
    ) throws SQLException, ClassNotFoundException {
        ModelAndView model = new ModelAndView();
        if ((email.length() > 0)
                & (pass.length() > 0)
                ) {
            if (service.mailCheck(email)) {
                if (service.login(email, pass)) {
                    model.setViewName("afterLogin");
                    User user = service.getUser(email);
                    model.addObject("user", user);
                    model.addObject("name", user.getName());
                    service.setUserSession(service.getId(email), session.getId());
                    ArrayList<User> listRequestAddToFriends = service.getListReqToFriends(user.getId());
                    model.addObject("listRequestFriends", listRequestAddToFriends);
                } else {
                    model = new ModelAndView();
                    model.setViewName("index");
                    model.addObject("msg", "Пароль введён не верно!");
                }
            } else {
                model = new ModelAndView();
                model.setViewName("index");
                model.addObject("msg", "email не найден");
            }
        } else {
            model = new ModelAndView();
            model.setViewName("index");
            model.addObject("msg", "необходимо ввести email и пароль");
        }
        return model;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = {"/main"}, method = RequestMethod.GET)
    public ModelAndView main(HttpSession session) throws SQLException {
        ModelAndView model = new ModelAndView();
        User user = (User) session.getAttribute("user");
        model.setViewName("afterLogin");
        ArrayList<User> listRequestAddToFriends = service.getListReqToFriends(user.getId());
        model.addObject("listRequestFriends", listRequestAddToFriends);
        return model;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration() {
        return "registration";
    }

}