package com.communityapp.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    //List<File> findByOwnerOrFileName(Long userId);
    //List<File> findByOwner_IdOrRecipient_IdOrderByCreatedAtDesc(Long userId, Long userId1);
}
