package com.communityapp.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping
    public File uploadFile(@RequestParam("file") MultipartFile file, @RequestParam Long userId) {
        return fileService.uploadFile(file, userId);
    }

//    @GetMapping("/{userId}")
//    public List<File> getUserFiles(@PathVariable Long userId) {
//        return fileService.getUserFiles(userId);
//    }
}



//
//@Controller
//public class FileUploadController {
//
//    @Autowired
//    private StorageService storageService;
//
//    @PostMapping("/upload")
//    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
//        storageService.store(file);
//        return ResponseEntity.ok().body("File uploaded successfully!");
//    }
//
//}
