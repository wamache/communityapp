package com.communityapp.model;//package com.frex.model;
//
//
//import com.frex.config.GeneralUtils;
//import com.frex.model.User;
//import lombok.Data;
//
//import javax.persistence.*;
//import java.text.DecimalFormat;
//import java.util.Date;
//import java.util.Random;
//
//@Data
//@Entity
//public class PasswordResetToken {
//
//    private static final int EXPIRATION = 60 * 24;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;
//    @Column(nullable = false, length = 100, unique = true)
//    private String token;
//    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
//    private User user;
//    @Column(nullable = false)
//    private Date expirationDate;
//
//    public String getResetToken() {
//        this.user = user;
//       // createdDate = new Date();
//        token = generateOTP(); //UUID.randomUUID().toString();
//        expirationDate = GeneralUtils.calculateExpiryDate(EXPIRATION);
//        return token;
//    }
//
//    public String generateOTP() {
//        return new DecimalFormat("0000")
//                .format(new Random().nextInt(9999));
//    }
//
//}
//
////
////import com.frex.model.User;
////import lombok.AllArgsConstructor;
////import lombok.Data;
////import lombok.NoArgsConstructor;
////
////import javax.persistence.*;
////import java.util.Date;
////
////@Data
////@AllArgsConstructor
////@NoArgsConstructor
////@Entity
////public class PasswordResetToken {
////
////    @Id
////    private long id;
////    private  String resetPasswordToken;
////
////    private Date expiryDate;
////
////
////    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
////    @JoinColumn(nullable = false, name = "user_id")
////    private User user;
////
////
////}
