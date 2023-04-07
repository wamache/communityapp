package com.communityapp.dashboard;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private int age;
    private String gender;
    private String address;
    private String phoneNumber;
    // getters and setters


    public Patient() {
    }

    public Patient(Long id, String name, String email, int age, String gender, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

}

