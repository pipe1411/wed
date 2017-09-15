package com.me.wed.controllers;

import com.me.wed.domain.*;
import com.me.wed.services.WeddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;

/**
 * Created by pipe on 9/2/17.
 */

@RestController
public class resource {

    @Autowired
    private WeddingService weddingService;

    @RequestMapping("/resource")
    public Map<String,Object> home() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", getLoggedUser());
        return model;
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping("/addWedding")
    public String registration(@RequestBody Wedding wedding, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        wedding.setEmail(((UserDetails)auth.getPrincipal()).getUsername());

        String guuid = UUID.randomUUID().toString();
        wedding.setGuuid(guuid);

        weddingService.saveWedding(wedding);

        return "success";
    }

    @RequestMapping(value = "/getWeddings",method = RequestMethod.GET)
    public ArrayList<Wedding> getWeddings() {
       Iterable<Wedding> weddingIterable = weddingService.findAllWeddings();
       Iterator<Wedding> weddingIterator = weddingIterable.iterator();
       ArrayList<Wedding> weddings = new ArrayList<>();
       while (weddingIterator.hasNext()) {
           Wedding wedding = weddingIterator.next();
           if (wedding.getEmail().equals(getLoggedUser())) {
               weddings.add(wedding);
           }
       }

       return weddings;
    }

    @RequestMapping(value = "/getWedding", method = RequestMethod.POST)
    public Wedding getWedding(@RequestBody Guuid guuid) {
        Iterable<Wedding> weddingIterable = weddingService.findAllWeddings();
        Iterator<Wedding> weddingIterator = weddingIterable.iterator();

        while (weddingIterator.hasNext()) {
            Wedding wedding = weddingIterator.next();
            if (wedding.getEmail().equals(getLoggedUser()) && wedding.getGuuid().equals(guuid.getGuuid())) {
                return wedding;
            }
        }

        return null;
    }

    @RequestMapping(value = "/addGuest", method = RequestMethod.POST)
    public String addGuest(@RequestBody GuestPost guestPost) {
        String guuid = guestPost.getGuuid();
        Guest guest = guestPost.getGuest();
        if (weddingService.addGuest(guuid,guest)){
            return "{\"response\":\"success\"}";
        } else {
            return "{\"response\":\"fail\"}";
        }
    }

    private String getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails)auth.getPrincipal()).getUsername();
    }


}
