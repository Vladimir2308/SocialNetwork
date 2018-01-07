package com.mkyong.web.controller;

import com.mkyong.web.model.Response;
import com.mkyong.web.model.User;
import com.mkyong.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;



@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;


    @RequestMapping("/search")
    public ModelAndView search() {
        ArrayList<User> userList = service.getIterableListUsers();
        return new ModelAndView("search", "userList", userList);
    }
    @RequestMapping(value = "/getCharNum", method = RequestMethod.POST)
    public @ResponseBody
    Object getCharNum(@RequestParam String text) {

        System.out.println(text+" _____");

        if (text != null) {


        }

        return text;
    }
}

