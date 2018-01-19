package com.mkyong.web.controller;

import com.mkyong.web.model.User;
import com.mkyong.web.service.UseJDBC;
import com.mkyong.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;


@Controller
public class UserController {
    @Autowired
    private UserService service;


    @RequestMapping("/search")
    public ModelAndView search() throws SQLException {
        ArrayList<User> userList = service.getIterableListUsers();
        return new ModelAndView("search", "userList", userList);
    }

    @RequestMapping(value = "/friendRequest", method = RequestMethod.GET)
    public @ResponseBody
    Object friendRequest(HttpSession session, @RequestParam String text) throws SQLException {

        System.out.println(text + " friendRequest");
        int id;
        if (text != null) {
            try {
                id = Integer.parseInt(text);
            } catch (Exception e) {
                return false;
            }
            User userReqFriend = service.getUser(id);
            User currentUser = ((User) session.getAttribute("user"));
            System.out.println(service.toString());

            if ((!service.getListFriends(userReqFriend.getId()).contains(currentUser))
                    & (!service.getListReqToFriends(userReqFriend.getId()).contains(currentUser)
                    & (userReqFriend.getId() != currentUser.getId()))) {
                System.out.println(service.requestFriends(userReqFriend.getId(), currentUser.getId()));

            }
        }

        text = "added";
        return text;

    }

    @RequestMapping(value = "/friendReqFault", method = RequestMethod.GET)
    public @ResponseBody
    Object friendReqFault(HttpSession session, @RequestParam String text) throws SQLException {


        int id;
        if (text != null) {
            try {
                id = Integer.parseInt(text);
            } catch (Exception e) {
                return false;
            }
            User userReqFriend = service.getUser(id);
            int currentId = ((User) session.getAttribute("user")).getId();


            service.requestFriendsFault(currentId, userReqFriend.getId());

        }

        text = "Fault";
        return text;
    }


    @RequestMapping(value = "/friendAdd", method = RequestMethod.POST)
    public @ResponseBody
    Object friendAdd(HttpSession session, @RequestParam String text) throws SQLException {

        System.out.println(text + " запрос на добавление  в друзья");
        int id;
        User currentUser = ((User) session.getAttribute("user"));
        if (text != null) {
            try {
                id = Integer.parseInt(text);
            } catch (Exception e) {
                return false;
            }
            if (!service.getListFriends(currentUser.getId()).contains(service.getUser(id))) {
                service.addFriends(currentUser.getId(), id);
            }
        }

        return text;
    }

    @RequestMapping("/del{idString}")
    public ModelAndView del(@PathVariable String idString, HttpSession session) throws SQLException {
        int id = -1;
        try {
            id = Integer.parseInt(idString);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        int currentId = ((User) session.getAttribute("user")).getId();
        ArrayList<User> listFriends = service.getListFriends(currentId);
        service.delFriends(currentId, id);
        return new ModelAndView("friends", "listFriends", listFriends);
    }
//    @RequestMapping(value = "/friendDel", method = RequestMethod.GET)
//    public @ResponseBody
//    Object friendDel(HttpSession session, @RequestParam String text) {
//        int id;
//        if (text != null) {
//            try {
//                id = Integer.parseInt(text);
//            } catch (Exception e) {
//                return false;
//            }
//            service.delFriends(((User) session.getAttribute("user")), id);
//        }
//        return text;
//    }

    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public ModelAndView friends(HttpSession session) throws SQLException {
        User user = (User) session.getAttribute("user");
        ArrayList<User> listFriends = service.getListFriends(user.getId());

        return new ModelAndView("friends", "listFriends", listFriends);
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session) throws SQLException {
        if (!session.isNew()) {
            service.setUserSession(((User) session.getAttribute("user")).getId(), "");
            session.invalidate();
        }
        return "index";
    }

    @RequestMapping("/see{idString}")
    public ModelAndView see(@PathVariable String idString, HttpSession session) throws SQLException {
        int id = -1;
        try {
            id = Integer.parseInt(idString);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        if (service.getListFriends(((User) session.getAttribute("user")).getId()).contains(service.getUser(id))) {
            return new ModelAndView("see", "friend", service.getUser(id));
        } else {
            service.setUserSession(((User) session.getAttribute("user")).getId(), "");
            session.invalidate();
            return new ModelAndView("index");
        }
    }
}
