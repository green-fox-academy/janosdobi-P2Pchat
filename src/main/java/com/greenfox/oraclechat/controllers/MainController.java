package com.greenfox.oraclechat.controllers;

import com.greenfox.oraclechat.model.User;
import com.greenfox.oraclechat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    UserService users;

    @GetMapping({"/", ""})
    public String index() {
        return "index";
    }

    @GetMapping("/enter")
    public String enter(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/enter")
    public String enter(@ModelAttribute User user) {
        if (!user.equals(null)) {
            users.addUser(user);
            return "redirect:/";
        } else {
            return "redirect:/enter";
        }

    }
}