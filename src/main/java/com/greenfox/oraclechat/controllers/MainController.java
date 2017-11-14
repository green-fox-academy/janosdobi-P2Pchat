package com.greenfox.oraclechat.controllers;


import com.greenfox.oraclechat.OraclechatApplication;
import com.greenfox.oraclechat.model.Client;
import com.greenfox.oraclechat.model.Message;
import com.greenfox.oraclechat.model.User;
import com.greenfox.oraclechat.services.MessageService;
import com.greenfox.oraclechat.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {


    private static final Logger logger = LoggerFactory.getLogger(MainController.class);


    @Autowired
    UserService users;

    @Autowired
    MessageService messages;

    @ExceptionHandler(Exception.class)
    public void excepitonHandling(HttpServletRequest request) {
        logger.error("Request" + " " + request.getServletPath() + " " + request.getMethod() + " " + request.getQueryString());
    }

    //Enter page

    @GetMapping({"/", ""})
    public String enter(Model model, HttpServletRequest request) {
        model.addAttribute("user", new User());
        logger.info("Request" + " " + request.getServletPath() + " " + request.getMethod() + " " + request.getQueryString());
        return "login";
    }

    @PostMapping({"/", ""})
    public String enter(@ModelAttribute User user, Model model, HttpServletRequest request) {
        if (user.getName().equals("")) {
            model.addAttribute("errorMessage", "Please enter a username!");
            return "login";
        }
        for (User u : users.listAllUsers()) {
            if (u.getName().equals(user.getName())) {
                logger.info("Request" + " " + request.getServletPath() + " " + request.getMethod() + " " + request.getQueryString());
                return "redirect:/index?userId=" + u.getId();
            }
        }
        return "redirect:/";
    }

    //Index page

    @GetMapping("/index")
    public String index(@RequestParam(value = "userId") Long id, Model model, HttpServletRequest request) {
        User user = users.findOneUser(id);
        model.addAttribute(user);
        model.addAttribute("defaultMessage", new Message("App", "Hi there! Submit your message using the send button!"));
        model.addAttribute("messages", messages.listTenMostRecent());
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

    @PostMapping("/index/{userId}/addmessage")
    public String addMyMessage(@PathVariable long userId, @ModelAttribute Message message, HttpServletRequest request) {
        messages.addMessage(message);
        messages.sendMessage(message,new Client(OraclechatApplication.CHAT_APP_UNIQUE_ID));
        logger.info("Request" + " " + request.getServletPath() + " " + request.getMethod() + " " + request.getQueryString());
        return "redirect:/index?userId=" + userId;
    }

    //Websocket

    @MessageMapping("/api/message/receive")
    public String addOthersMessage(@PathVariable long userId, @ModelAttribute Message message, HttpServletRequest request) throws Exception {
        Thread.sleep(1000); // simulated delay
        messages.addMessage(message);
        logger.info("Request" + " " + request.getServletPath() + " " + request.getMethod() + " " + request.getQueryString());
        return "redirect:/index?userId=" + userId;
    }
}