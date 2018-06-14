package org.ictlab.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.ictlab.security.JwtTokenUtil;
import org.ictlab.security.JwtUser;

@RestController
public class UserRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
//    private final JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    public UserRestController(JwtTokenUtil jwtTokenUtil, @Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
//        this.jwtUserDetailsService = jwtUserDetailsService;
    }


    @GetMapping(value = "/user")
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return (JwtUser) userDetailsService.loadUserByUsername(username);
    }

//    @PostMapping(value = "/singup")
//    public void singUp(@RequestBody User user) {
////        jwtUserDetailsService.saveUser(user);
//    }
}
