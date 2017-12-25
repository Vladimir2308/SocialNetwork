package com.mkyong.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {


    @RequestMapping(value ={ "/", "/index" } , method = RequestMethod.GET)
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

    @RequestMapping(method = RequestMethod.POST)
    public String handleForm(@RequestParam("name") String name, ModelMap model) {
        model.addAttribute("name", name);
        return "form";
    }

}