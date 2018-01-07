package com.mkyong.web.controller;

import com.mkyong.web.model.User;
import com.mkyong.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView showMessage(@RequestParam String email,
                                    @RequestParam String pass
    ) {

        System.out.println("русские символы");


        ModelAndView model = new ModelAndView();

        if ((email.length() > 0)
                & (pass.length() > 0)
                ) {
            if (service.mailCheck(email)) {
                if (service.login(email, pass)) {
                    model.setViewName("afterLogin");

                    model.addObject("name", service.getName(email));
                } else {
                    model = new ModelAndView();
                    model.setViewName("form");
                    model.addObject("msg", "Пароль введён не верно!");
                }
            } else {
                model = new ModelAndView();
                model.setViewName("form");
                model.addObject("msg", "email не найден");
            }
        } else {
            model = new ModelAndView();
            model.setViewName("form");
            model.addObject("msg", "необходимо ввести email и пароль");
        }


        return model;
    }


    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index() {
        return "form";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration() {
        return "registration";
    }


    @RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {

        ModelAndView model = new ModelAndView();
        model.setViewName("hello");
        model.addObject("msg", name);

        return model;

    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String showForm() {
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public ModelAndView handleForm(@RequestParam String email ) {
        ModelAndView model = new ModelAndView();
        model.setViewName("form");
//        model.addObject("name", email);
        return model;
    }

}