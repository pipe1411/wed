package com.me.wed.controllers;

import com.me.wed.domain.User;
import com.me.wed.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created by pipe on 9/4/17.
 */

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
/*    public String registration(@ModelAttribute("data") User data, BindingResult bindingResult, Model model)*/
    public String registration(@RequestBody User user,BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.saveUser(user);


        return "success";
    }
}
