package com.mkyong.web.controller;

import com.mkyong.web.model.User;
import com.mkyong.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@SessionAttributes("user")
public class HelloController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView showMessage( HttpSession session, @RequestParam String email,
                                    @RequestParam String pass
    ) {

        System.out.println("русские символы");
//        if (!session.isNew()) {
//            session.invalidate();
//        }

        ModelAndView model = new ModelAndView();

        if ((email.length() > 0)
                & (pass.length() > 0)
                ) {
            if (service.mailCheck(email)) {
                if (service.login(email, pass)) {
                    model.setViewName("afterLogin");

                    User user=service.getUser(service.getId(email));
                    model.addObject("user",user);
                    model.addObject("name", user.getName());
                    service.setUserSession(service.getUser(service.getId(email)),session.getId());
                    ArrayList<User> listRequestAddToFriends = user.getListRequestAddToFriends();

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
    public ModelAndView main(HttpSession session) {
        ModelAndView model = new ModelAndView();

        User user=(User)session.getAttribute("user");

        model.setViewName("afterLogin");
        ArrayList<User> listRequestAddToFriends = user.getListRequestAddToFriends();
        model.addObject("listRequestFriends", listRequestAddToFriends);
        System.out.println("list request size" + listRequestAddToFriends.size());
        return model;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration() {
        return "registration";
    }

}