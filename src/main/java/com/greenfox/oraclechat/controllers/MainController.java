package com.greenfox.oraclechat.controllers;


import com.greenfox.oraclechat.model.Message;
import com.greenfox.oraclechat.model.User;
import com.greenfox.oraclechat.services.MessageService;
import com.greenfox.oraclechat.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);


    @Autowired
    UserService users;

    @Autowired
    MessageService messages;

    //Enter page

    @GetMapping({"/", ""})
    public String enter(@ModelAttribute User user, Model model, HttpServletRequest request) {
        model.addAttribute("user", new User());
        logger.info("Request" + " " + request.getServletPath() + " " + request.getMethod() + " " + request.getQueryString());
        if (user.getName()==null) {
            model.addAttribute("errorMessage", "Please enter a username!");
            return "login";
        }
        return "login";
    }

    @PostMapping({"/", ""})
    public String enter(@ModelAttribute User user, HttpServletRequest request) {
        for (User u : users.listAllUsers()) {
            if (u.getName().equals(user.getName())) {
                logger.info("Request" + " " + request.getServletPath() + " " + request.getMethod() + " " + request.getQueryString());
                return "redirect:/index?userId=" + u.getId();
            }
        }
        logger.error("Request" + " " + request.getServletPath() + " " + request.getMethod() + " " + request.getQueryString());
        return "redirect:/";
    }

    //Index page

    @GetMapping("/index")
    public String index(@RequestParam(value = "userId") Long id, Model model, HttpServletRequest request) {
        User user = users.findOneUser(id);
        model.addAttribute(user);
        model.addAttribute("defaultMessage", new Message("App", "Hi there! Submit your message using the send button!"));
        model.addAttribute("messages", messages.listAll());
        model.addAttribute("newMessage", new Message(user.getName()));
        logger.info("Request" + " " + request.getServletPath() + " " + request.getMethod() + " " + request.getQueryString());
        return "index";
    }

    @PostMapping("/index")
    public String updateUsername(@ModelAttribute User user, HttpServletRequest request) {
        users.addUser(user);
        logger.info("Request" + " " + request.getServletPath() + " " + request.getMethod() + " " + request.getQueryString());
        return "redirect:/index?userId=" + user.getId();
    }

    @PostMapping("/index/addmessage")
    public String addMessage(@ModelAttribute Message message, HttpServletRequest request) {
        messages.addMessage(message);
        logger.info("Request" + " " + request.getServletPath() + " " + request.getMethod() + " " + request.getQueryString());
        return "redirect:/index?userId=1";
    }
}