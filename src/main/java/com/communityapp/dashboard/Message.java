package com.communityapp.dashboard;

import com.communityapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "users")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(unique = true)
//    private String username;
//    private String password;
//    private String firstName;
//    private String lastName;
//    private String role; // can be either "CHW" (community health worker) or "Patient"
//    // getters and setters
//}

@Entity
@Data
@AllArgsConstructor
@NamedEntityGraph
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    private User sender;
    @ManyToOne(fetch = FetchType.LAZY)
    private User recipient;
    private String content;

//    public void setCreatedAt(LocalDateTime now) {
//        return createdAt;
//    }
    // getters and setters
}


