package com.communityapp.dashboard;

import com.communityapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    public File uploadFile(MultipartFile file, Long userId) {
        try {
            File uploadedFile = new File();
            uploadedFile.setCreatedAt(LocalDateTime.now());
            uploadedFile.setOwner(new User(userId));
            uploadedFile.setFileName(file.getOriginalFilename());
            uploadedFile.setFileType(file.getContentType());
            uploadedFile.setData(file.getBytes());
            return fileRepository.save(uploadedFile);
        } catch (IOException e) {
            throw new RuntimeException("Error uploading file", e);
        }
    }

//    public List<File> getUserFiles(Long userId) {
//        return fileRepository.findByOwnerOrFileName(userId);
//       // return fileRepository.findBySender_IdOrRecipient_IdOrderByCreatedAtDesc(userId, userId);
//    }
}

//@Service
//public class StorageService {
//    public void store(MultipartFile file) {
//    }

    // private final Path rootLocation;

    //  @Autowired

