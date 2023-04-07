package com.communityapp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Utilities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer billId;

    private Double amount;

    private String billType;

    @CreatedDate
    @CreationTimestamp
    private LocalDate paymentDate;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Wallet wallet;

}

