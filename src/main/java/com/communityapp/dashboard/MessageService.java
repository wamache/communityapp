package com.communityapp.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Optional<Message> getUserMessages(Long id) {
        return messageRepository.findById(id);
       // return messageRepository.findBySender_IdOrRecipient_IdOrderByCreatedAtDesc(userId, userId);
    }

    public Message sendMessage(Message message) {
        message.setCreatedAt(LocalDateTime.now());
        return messageRepository.save(message);
    }
}
