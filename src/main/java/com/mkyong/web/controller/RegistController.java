package com.mkyong.web.controller;

import com.mkyong.web.model.User;
import com.mkyong.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
@SessionAttributes("user")
public class RegistController {
    @Autowired
    private UserService service;
    @RequestMapping(value ="/registration", method = RequestMethod.POST)
    public ModelAndView showMessage(HttpSession session, @RequestParam String email,
                                    @RequestParam String name,
                                    @RequestParam String surname,
                                    @RequestParam String patronymic,
                                    @RequestParam String pass1,
                                    @RequestParam String pass2,
                                    @RequestParam String phone
    ) throws SQLException, ClassNotFoundException {

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
            User user = null;
            try {
                user = service.register (email, name, surname, patronymic, pass1, phone);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            model.setViewName("afterLogin");
            model.addObject("name", name);
            model.addObject("user",user);
            service.setUserSession(service.getUser(email).getId(),session.getId());
            ArrayList<User> listRequestAddToFriends = service.getListReqToFriends(user.getId());
            model.addObject("listRequestFriends", listRequestAddToFriends);
        } else {
            model = new ModelAndView();
            model.setViewName("registration");
            if (!pass1.equals(pass2)
                    |(pass1.length()==0)
                    | (pass1.length()>20)){
            model.addObject("msg1", "Пароли введены не верно!");
        }
        if (service.mailCheck(email)){
            model.addObject("msg2", "This email already registered!");
        }
            if ((email.length()==0)
                    |(email.length()>30)
                    |(name.length()==0)
                    |(name.length()>20)
                    |(surname.length()==0)
                    |(surname.length()>20)
                    |(phone.length()==0)
                    |(phone.length()>20)
                    |(patronymic.length()==0)
                    |(patronymic.length()>20)){
                model.addObject("msg3", "Данные введены не верно!");
            }
        }
        return model;
    }
}
