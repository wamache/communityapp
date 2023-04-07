package com.communityapp.service;
import com.communityapp.model.ConfirmationToken;
import com.communityapp.model.User;
import com.communityapp.reposirory.ConfirmationTokenRepository;
import com.communityapp.reposirory.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private EmailService emailService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    public User getAUser(Long userid){
        return userRepository.findByUserid(userid);
    }
    @Transactional
    public boolean resendVerificationToken(final String existingVerificationToken) {
        ConfirmationToken vToken = confirmationTokenRepository.findByConfirmationToken(existingVerificationToken);


        if (vToken != null) {
            User user = userRepository.findByEmailIgnoreCase(vToken.getUser().getEmail());



//            ConfirmationToken vToken = confirmationTokenRepository.findByConfirmationToken(existingVerificationToken);
//            if (vToken != null) {
                String otp = vToken.updateToken(vToken.generateOTP());
                confirmationTokenRepository.save(vToken);
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(vToken.getUser().getEmail());
                mailMessage.setSubject("Complete Registration!");
                mailMessage.setFrom("benardwamache@gmail.com");
                mailMessage.setText("To confirm your account, use the otp : "

                        + otp);

                emailService.sendEmail(mailMessage);
//            mailService.sendVerificationToken(vToken.getConfirmationToken(), vToken.getUser());
                return true;
            }
            return false;
        }


//    public void updatePassword(User user) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//       // String encodedPassword = passwordEncoder.encode(newPassword);
//        //user.setPassword(encodedPassword);
//
//        //user.setResetToken(null);
//       // userRepository.save(user);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//    }
//    public void updatePassword(String password, String token) throws Exception {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        User secureToken = userRepository.findByResetToken(token);
//        if(Objects.isNull(secureToken) || !StringUtils.equals(token, secureToken.getToken()) || secureToken.isExpired()){
//            throw new Exception("Token is not valid");
//        }
//        User user = userRepository.findByEmail(String.valueOf(secureToken.getUserid())).get();
//        if(Objects.isNull(user)){
//            throw new Exception("unable to find user for the token");
//        }
//        user.removeToken(secureToken);
//        user.setPassword(passwordEncoder.encode(password));
//        userRepository.save(user);
//    }

//    @Modifying
//    public void updatePassword(User user) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//    }
}

