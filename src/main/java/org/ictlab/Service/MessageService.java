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

    public Optional<Message> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    public List<Message> getAllMessagesByUserId(Long userId) {
        return messageRepository.findAllByUsers_Id(userId);
    }

    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    public void deleteMessageById(Long id) {
        messageRepository.deleteById(id);
    }

}
