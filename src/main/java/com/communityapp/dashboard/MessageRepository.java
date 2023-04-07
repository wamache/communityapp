package com.communityapp.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
    Optional<Message> findById(Long id);

    //List<Message> findBySender_IdOrRecipient_IdOrderByCreatedAtDesc(Long userId, Long userId1);
}