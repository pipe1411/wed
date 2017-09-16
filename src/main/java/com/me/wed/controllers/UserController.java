package com.me.wed.controllers;

import com.me.wed.domain.User;
import com.me.wed.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pipe on 9/4/17.
 */

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
/*    public String registration(@ModelAttribute("data") User data, BindingResult bindingResult, Model model)*/
    public Map<String,Object> registration(@RequestBody User user, BindingResult bindingResult){
        Map<String,Object> model = new HashMap<>();
        if (bindingResult.hasErrors()) {
            model.put("result","binding error");
            return model;
        }

        userService.saveUser(user);
        model.put("result","success");

        return model;
    }
}
