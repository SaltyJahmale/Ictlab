package org.ictlab.rest;

import org.ictlab.Service.UserService;
import org.ictlab.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private final UserService userService;


    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/signup")
    public void singup(@RequestBody User user) {
        userService.saveOrUpdate(user);
    }
}
