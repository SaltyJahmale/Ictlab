package org.ictlab.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.ictlab.Service.UserService;
import org.ictlab.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.ictlab.security.JwtTokenUtil;
import org.ictlab.security.JwtUser;

import java.util.List;
import java.util.Optional;

@RestController
public class UserRestController {

    private Logger log = LoggerFactory.getLogger(UserRestController.class);

    @Value("${jwt.header}")
    private String tokenHeader;

    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @Autowired
    public UserRestController(JwtTokenUtil jwtTokenUtil, @Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService, UserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }


    /**
     * @param request
     * @return JwtUser
     */
    @GetMapping(value = "/user")
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return (JwtUser) userDetailsService.loadUserByUsername(username);
    }

    /**
     * @param user
     * @return Http status code
     */
    @PostMapping(value = "/signup")
    public ResponseEntity singup(@RequestBody User user) {
        if(userService.getUser(user.getUsername()) != null) {
            log.info(String.format("User with the username %s is already is use", user.getUsername()));
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        userService.saveUser(user);
        log.info(String.format("User was successfully saved with the username %s", user.getUsername()));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * @param user
     * @param username
     * @return Http status code
     */
    @PutMapping(value = "/users/{username}")
    public ResponseEntity updateUser(@RequestBody User user,
                                     @PathVariable("username") String username) {

        User userExists = userService.getUser(username);
        if(userExists == null) {
            log.info(String.format("User with the username %s could not be found", username));
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        User editedUser = userService.getUser(username);
        editedUser.setFirstname(user.getFirstname());
        editedUser.setUsername(user.getUsername());
        editedUser.setLastname(user.getLastname());
        editedUser.setEmail(user.getEmail());
        editedUser.setEnabled(user.getEnabled());
        editedUser.setAuthorities(user.getAuthorities());

        userService.updateUser(editedUser);
        log.info(String.format("User was successfully updated with the username %s", username));
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * @param username
     * @return User
     */
    @GetMapping(value = "/users/{username}")
    public ResponseEntity<User> getUser (@PathVariable("username") String username) {
        User userExists = userService.getUser(username);
        if(userExists == null) {
            log.info(String.format("User with the username %s could not be found", username));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = userService.getUser(username);
        log.info(String.format("Successfully retrieved user with username: %s", username));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * @return List<User>
     */
    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> findAllUsers() {
        if(userService.getAllUsers().isEmpty()) {
            log.info("No users where found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("Successfully returned a list of users");
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    /**
     * @param username
     * @return Http status code
     */
    @DeleteMapping(value = "/users/{username}")
    public ResponseEntity deleteUser (@PathVariable("username") String username) {
        User userExists = userService.getUser(username);
        if(userExists == null) {
            log.info(String.format("User with the username %s could not be found", username));
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        User deleteUser = userService.getUser(username);
        userService.deleteUser(deleteUser);
        log.info(String.format("User was successfully deleted with the username %s", username));
        return new ResponseEntity(HttpStatus.OK);
    }

}
