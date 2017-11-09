package com.greenfox.oraclechat.services;

import com.greenfox.oraclechat.model.User;
import com.greenfox.oraclechat.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<User> listAllUsers() {
        List<User> users = new ArrayList<>();
        for (User u: userRepo.findAll()) {
            users.add(u);
        }
        return users;
    }

    public User findOneUser(Long id) {
        return userRepo.findOne(id);
    }

    public void deleteUser(Long id) {
        userRepo.delete(id);
    }

    public String verifyUser(User user) {
        if (listAllUsers().contains(user)) {
            return "redirect:/";
        } else {
            return "redirect:/enter";
        }
    }
}