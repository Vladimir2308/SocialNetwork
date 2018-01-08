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
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @RequestMapping("/friends")
    public ModelAndView friends(HttpSession session) {
        System.out.println("123");
        ArrayList<User> friendList =  service.getUser((int)session.getAttribute("id")).getListFriends();
        System.out.println("456");
        return new ModelAndView("friends", "friendList", friendList);
    }
    @RequestMapping(value = "/friendRequest", method = RequestMethod.GET)
    public @ResponseBody
    Object friendRequest(@RequestParam String text) {

        System.out.println(text+" request for add friends");
        int id=0;
        if (text != null) {
            try {
                id = Integer.parseInt(text);
            } catch (Exception e) {
                return false;
            }

        }

        service.requestFriends(1,id);
        return text;
    }
    @RequestMapping(value = "/friendAdd", method = RequestMethod.POST)
    public @ResponseBody
    Object friendAdd(HttpServletRequest request, HttpSession session, @RequestParam String text) {

        System.out.println(text+" запрос на добавление  в друзья");
        int id;
        if (text != null) {
            try {
                id = Integer.parseInt(text);
            } catch (Exception e) {
                return false;
            }

            service.addFriends((int)session.getAttribute("id"),id);
            System.out.println(" session.getAttribute "+ (int)session.getAttribute("id"));
        }

        return text;
    }




    @RequestMapping("/logout")
    public String  logout() {
        return "redirect:/login";
    }
}
