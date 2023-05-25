package com.ussdwork.ussd.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.ussdwork.ussd.Service.*;
import com.ussdwork.ussd.model.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import com.ussdwork.ussd.repository.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ussd")
public class USSDController {
    @Autowired
    USSDService ussdService;
    // UserRepository userRepository;

    @GetMapping("/")
    public List<User> getAll() {
        return ussdService.getAll();
    }

    @PutMapping("/")
    public User update(@RequestBody User change) {
        return ussdService.update(change);
    }

}
