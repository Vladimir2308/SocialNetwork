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
public class RegistController {
    @Autowired
    private UserService service;
    @RequestMapping(value ="/regist", method = RequestMethod.POST)
    public ModelAndView showMessage(@RequestParam String email,
                                    @RequestParam String name,
                                    @RequestParam String surname,
                                    @RequestParam String patronymic,
                                    @RequestParam String pass1,
                                    @RequestParam String pass2
    ) {

        System.out.println("русские символы");
        System.out.println("email "+ email);
        System.out.println("pass1 "+ pass1);
        System.out.println("pass2 "+ pass2);
        System.out.println("name "+ name);
        System.out.println("surname "+ surname);
        System.out.println("patronymic "+ patronymic);

        ModelAndView model = new ModelAndView();


        if (pass1.equals(pass2)&(pass1!=null)) {
            User user = service.register(email, name, surname, patronymic, pass1);

            model.setViewName("afterLogin");
            model.addObject("test", name);
        } else {
            model = new ModelAndView();
            model.setViewName("registration");
            model.addObject("msg", "Password don't match");
        }
        return model;
    }
}
