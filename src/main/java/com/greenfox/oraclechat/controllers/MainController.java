package com.greenfox.oraclechat.controllers;

import com.greenfox.oraclechat.OraclechatApplication;
import com.greenfox.oraclechat.model.User;
import com.greenfox.oraclechat.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    UserService users;

    @GetMapping({"/", ""})
    public String index() {
        logger.info("indexLOGGG");
        return "index";
    }

    @GetMapping("/enter")
    public String enter(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/enter")
    public String enter(@ModelAttribute User user) {
        users.addUser(user);
        return "redirect:/";
    }
}