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
    public ModelAndView search() {
        ArrayList<User> userList = service.getIterableListUsers();
        return new ModelAndView("search", "userList", userList);
    }

    @RequestMapping(value = "/friendRequest", method = RequestMethod.GET)
    public @ResponseBody
    Object friendRequest(HttpSession session, @RequestParam String text) {

        System.out.println(text + " friendRequest");
        int id;
        if (text != null) {
            try {
                id = Integer.parseInt(text);
            } catch (Exception e) {
                return false;
            }
            User userReqFriend = service.getUser(id);
            int currentId = ((User) session.getAttribute("user")).getId();

            if ((!userReqFriend.getListFriends().contains(service.getUser(currentId)))
                    & (!userReqFriend.getListRequestAddToFriends().contains(service.getUser(currentId))
                    & (userReqFriend.getId() != currentId))) {
                System.out.println(service.requestFriends(userReqFriend.getId(), currentId));

            }
        }

        text = "added";
        return text;

    }

    @RequestMapping(value = "/friendReqFault", method = RequestMethod.GET)
    public @ResponseBody
    Object friendReqFault(HttpSession session, @RequestParam String text) {


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
    Object friendAdd(HttpSession session, @RequestParam String text) {

        System.out.println(text + " запрос на добавление  в друзья");
        int id;
        User currentUser = ((User) session.getAttribute("user"));
        if (text != null) {
            try {
                id = Integer.parseInt(text);
            } catch (Exception e) {
                return false;
            }
            if (!currentUser.getListFriends().contains(service.getUser(id)))

            {
                service.addFriends(currentUser.getId(), id);

            }


        }

        return text;
    }


    @RequestMapping(value = "/friendDel", method = RequestMethod.GET)
    public @ResponseBody
    Object friendDel(HttpSession session, @RequestParam String text) {


        int id;
        if (text != null) {
            try {
                id = Integer.parseInt(text);
            } catch (Exception e) {
                return false;
            }


            service.delFriends(((User) session.getAttribute("user")), id);

        }

        return text;
    }

    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public ModelAndView friends(HttpSession session) {
        User user = (User) session.getAttribute("user");
//        receive user id from session
        ArrayList<User> listFriends = user.getListFriends();
        try {
            System.out.println("before DB");
            UseJDBC.reqJDBC();
            System.out.println("after DB");

        } catch (ClassNotFoundException e) {
            System.out.println( e.toString());
        } catch (SQLException e) {
            System.out.println( e.toString());
        }
        return new ModelAndView("friends", "listFriends", listFriends);
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        if (!session.isNew()) {
            ((User) session.getAttribute("user")).setSessionId("");
            session.invalidate();
        }
        return "index";
    }

    @RequestMapping("/see{idString}")
    public ModelAndView see(@PathVariable String idString, HttpSession session) {
        int id = -1;
        try {
            id = Integer.parseInt(idString);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        if (((User) session.getAttribute("user")).getListFriends().contains(service.getUser(id))) {
            return new ModelAndView("see", "friend", service.getUser(id));
        } else {
            ((User) session.getAttribute("user")).setSessionId("");
            session.invalidate();
            return new ModelAndView("index");
        }
    }
}
