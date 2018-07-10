package org.ictlab.Service;


import org.ictlab.domain.Message;
import org.ictlab.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;


    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * @param id
     * @return Message
     */
    public Message getById(Long id) {
        return messageRepository.getOne(id);
    }

    /**
     * @param userId
     * @return List<Message>
     */
    public List<Message> getAllMessagesByUserId(Long userId) {
        return messageRepository.findAllByUsers_Id(userId);
    }

    /**
     * @param message
     */
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    /**
     * @param id
     */
    public void deleteMessageById(Long id) {
        messageRepository.deleteById(id);
    }

}
