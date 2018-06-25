package org.ictlab.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ictlab.Service.MessageService;
import org.ictlab.Service.UserService;
import org.ictlab.domain.Message;
import org.ictlab.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    private final UserService userService;
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<List<Message>> getAllById(@PathVariable("username") String username) {

        Boolean userExists = userService.usernameExist(username);
        if(!userExists) {
            log.info(String.format("User with the username %s could not be found", username));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = userService.getUser(username);
        log.info(String.format("Get all messages from user with username %s", username));
        return new ResponseEntity<>(messageService.getAllMessagesByUserId(user.getId()), HttpStatus.OK);
    }

    @PostMapping(value = "/{username}")
    public ResponseEntity sendMessageToUser(@PathVariable("username") String username,
                                            @RequestBody Message message) {
        Boolean userExists = userService.usernameExist(username);
        if(!userExists) {
            log.info(String.format("User with the username %s could not be found", username));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = userService.getUser(username);
        user.getMessages().add(message);
        userService.saveUser(user);
        log.info(String.format("Message got send to user with username %s", username));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteMessageById(@PathVariable("id") Long id) {

        Optional<Message> message = messageService.getMessageById(id);

        if(message == null) {
            log.info(String.format("Message with id %s could not be found", id));
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        log.info(String.format("Delete message with id %s", id));
        messageService.deleteMessageById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
