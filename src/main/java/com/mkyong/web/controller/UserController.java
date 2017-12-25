package com.mkyong.web.controller;

import com.mkyong.web.model.User;
import com.mkyong.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

//    @RequestMapping(value = "/register", method = RequestMethod.GET)
//    public ModelAndView registerUser(@RequestParam("name") String name) {
//        User user = service.register(name);
//
//        ModelAndView model = new ModelAndView();
//        model.setViewName("registered");
//        model.addObject("user", user);
//
//        return model;
//    }
}
