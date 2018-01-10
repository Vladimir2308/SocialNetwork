package com.mkyong.web.controller;

import com.mkyong.web.model.User;
import com.mkyong.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@Controller
public class UserController {
    @Autowired
    private UserService service;


    @RequestMapping("/search")
    public ModelAndView search() {
        ArrayList<User> userList = service.getIterableListUsers();
        return new ModelAndView("search", "userList", userList);
    }

    @RequestMapping(value = "/friendRequest", method = RequestMethod.GET)
    public @ResponseBody
    Object friendRequest(HttpServletRequest request, HttpSession session,@RequestParam String text) {

        System.out.println(text + " запрос на добавление  в друзья");
        int id;
        if (text != null) {
            try {
                id = Integer.parseInt(text);
            } catch (Exception e) {
                return false;
            }

            service.requestFriends(1, id);
        }

        return text;
    }

    @RequestMapping(value = "/friendAdd", method = RequestMethod.POST)
    public @ResponseBody
    Object friendAdd(HttpServletRequest request, HttpSession session, @RequestParam String text) {

        System.out.println(text + " запрос на добавление  в друзья");
        int id;
        if (text != null) {
            try {
                id = Integer.parseInt(text);
            } catch (Exception e) {
                return false;
            }


            service.addFriends(((User)session.getAttribute("user")).getId(), id);
            System.out.println(" session.getAttribute " + ((User)((User) session.getAttribute("user"))).getSessionId());
        }

        return text;
    }
    @RequestMapping("/friends")
    public ModelAndView friends() {
        int idUser=1;
//        receive user id from session
       ArrayList<User> listFriends= service.getUser(idUser).getListFriends();
        return new ModelAndView("friends", "listFriends", listFriends);
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        if (!session.isNew()) {
            session.invalidate();
        }
        return "index";
    }
}
