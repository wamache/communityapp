package com.communityapp.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

//
//    @PostMapping("/feedback")
//    public String postFeedback(@RequestBody Feedback feedback) {
//        // Here, we can write the logic to save the feedback in a database or send it to a backend system for processing
//        // We can also perform any necessary validations or checks on the feedback before saving or processing it
//        return "Thank you for your feedback!";
//    }

    @PostMapping("/feedback")
    public ResponseEntity<String> submitFeedback(@RequestBody Feedback feedback) {
        feedbackRepository.save(feedback);
        // Here, we can write the logic to store the feedback in a database or send it to an external service
        // This logic can include validation of the feedback data and handling of any errors that occur

        // For now, we'll just return a success response
        return ResponseEntity.status(HttpStatus.CREATED).body("Feedback submitted successfully");
    }

    @GetMapping("/feedback/{id}")
    public ResponseEntity<Feedback> getFeedback(@PathVariable Long id) {
        Feedback feedback = new Feedback();
        feedbackRepository.findById(id);
        // Here, we can write the logic to fetch the feedback with the given ID from a database or external service
        // This logic can include error handling if the feedback does not exist or cannot be retrieved

        // For now, we'll just return a sample feedback object

        return ResponseEntity.ok(feedback);
    }

}
