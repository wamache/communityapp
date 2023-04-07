package com.communityapp.service;//package com.frex.service;
//
//import com.frex.model.PasswordResetToken;
//import com.frex.reposirory.PasswordResetTokenRepository;
//import com.frex.reposirory.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
////
////import com.frex.model.User;
////import com.frex.reposirory.UserRepository;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////
////import java.util.Date;
////import java.util.UUID;
////
//@Service
//public class ResetService {
//
//    @Autowired
//    private PasswordResetTokenRepository passwordResetTokenRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public PasswordResetToken save(PasswordResetToken passwordResetToken) {
//        return passwordResetTokenRepository.save(passwordResetToken);
//
//    }
//
//    public PasswordResetToken findByToken(String token) {
//
//        return passwordResetTokenRepository.findByToken(token).orElse(null);
//    }
//}
////
////
////    public void updateResetPasswordToken(String email) throws Exception {
////        User user = userRepository.findByEmailIgnoreCase(email);
////       // PasswordResetToken passwordResetToken = new PasswordResetToken();
////        if (user != null) {
////
////            throw new Exception("Could not find any user with the email " + email);
////        }
////        PasswordResetToken token = new PasswordResetToken();
////        token.setResetPasswordToken(UUID.randomUUID().toString());
////        token.setUser(user);
////        token.setExpiryDate(new Date(30));
////        passwordResetTokenRepository.save(token);
////    }
////
////    public PasswordResetToken getByResetPasswordToken(String token) {
////        return passwordResetTokenRepository.findByResetPasswordToken(token);
////    }
////
////    public void updatePassword(User user, String newPassword) {
////        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
////        String encodedPassword = passwordEncoder.encode(newPassword);
////        user.setPassword(encodedPassword);
////
////        customer.setResetPasswordToken(null);
////        customerRepo.save(customer);
////    }
////
////}
