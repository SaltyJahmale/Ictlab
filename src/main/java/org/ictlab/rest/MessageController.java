package org.ictlab.rest;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.ictlab.Service.MessageService;
import org.ictlab.Service.UserService;
import org.ictlab.domain.Message;
import org.ictlab.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {

    private final UserService userService;
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping(value = "/{username}")
    public List<Message> getAllById(@PathVariable("username") String username) {

        User user = userService.getUser(username);

        return messageService.getAllMessagesByUserId(user.getId());
    }

    @PostMapping(value = "/{username}")
    public void createMessage(@PathVariable("username") String username,
                              @RequestBody Message message) {
        Boolean user1 = userService.usernameExist(username);
        if(!user1) {
//            log.info(String.format("User with the username %s could not be found", username));
            System.out.println("Not found");
        }

        User user = userService.getUser(username);
        user.getMessages().add(message);
        userService.saveUser(user);
    }
}
