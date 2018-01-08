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


        ModelAndView model = new ModelAndView();

        if (pass1.equals(pass2)
                &(pass1.length()>0)
                &(email.length()>0)
                &(!service.mailCheck(email))
                &(name.length()>0)
                &(surname.length()>0)
                &(patronymic.length()>0)
        ) {
            User user = service.register(email, name, surname, patronymic, pass1);
            model.setViewName("afterLogin");
            model.addObject("name", name);
        } else {
            model = new ModelAndView();
            model.setViewName("registration");
            if ((!pass1.equals(pass2)
                    |(pass1.length()==0))){
            model.addObject("msg1", "Пароли введены не верно!");
        }
        if ((email.length()==0)
                    |(name.length()==0)
                    |(name.length()>20)
                    |(surname.length()==0)
                    |(surname.length()>20)
                    |(patronymic.length()==0)
                    |(patronymic.length()>20)){
            model.addObject("msg2", "Данные введены не верно!");
        }
        }
        return model;
    }
}
