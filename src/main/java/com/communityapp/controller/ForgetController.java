package com.communityapp.controller;//package com.frex.controller;
//
//import com.frex.dto.PasswordForgot;
//import com.frex.dto.PasswordReset;
//import com.frex.model.PasswordResetToken;
//import com.frex.service.ResetService;
//import com.frex.model.User;
//import com.frex.reposirory.UserRepository;
//import com.frex.service.EmailService;
//import com.frex.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//import java.util.Optional;
//
//@RestController
//@CrossOrigin
//public class ForgetController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private ResetService resetService;
//
//    @Autowired
//    private EmailService emailService;
//    @Autowired
//    private UserRepository userRepository;
//
//    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
//    public String processPasswordForgot(@Valid  PasswordForgot passwordForgot,
//                                        BindingResult result,
//                                        HttpServletRequest request) {
//        if (result.hasErrors()) {
//            return "forgot-password";
//        }
//        //User user = userRepository.findByEmail(passwordForgot.getEmail()).get();
//        Optional<User> optional = userRepository.findByEmail(passwordForgot.getEmail());
//
//        if (!optional.isPresent()) {
//            throw new RuntimeException("We didn't find an account for that e-mail address.");
//        } else {
//
//            User user = optional.get();
//            PasswordResetToken token = new PasswordResetToken();
//            token.setUser(user);
//            //token.setToken(UUID.randomUUID().toString());
////       // token.setExpirationDate(LocalDateTime.now().plusMinutes(30));
//            token = resetService.save(token);
//
////            user.setResetToken(UUID.randomUUID().toString());
////            userRepository.save(user);
//
////        if(user == null){
////           // model.addAttribute("emailError", messageSource.getMessage("EMAIL_NOT_FOUND", new Object[]{}, Locale.ENGLISH));
////            return "forgot-password";
////        }
////        // proceed to send email with link to reset password to this email address
////        PasswordResetToken token = new PasswordResetToken();
////        token.setUser(user);
////        //token.setToken(UUID.randomUUID().toString());
////       // token.setExpirationDate(LocalDateTime.now().plusMinutes(30));
////        token = resetService.save(token);
////        if(token == null){
////           // model.addAttribute("tokenError", messageSource.getMessage("TOKEN_NOT_SAVED", new Object[]{}, Locale.ENGLISH));
////            return "forgot-password";
////        }
//
//            //String appUrl = request.getScheme() + "://" + request.getServerName();
//
//            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
//            passwordResetEmail.setFrom("benardwamache@gmail.com");
//            passwordResetEmail.setTo(user.getEmail());
//            passwordResetEmail.setSubject("Password Reset Request");
//            passwordResetEmail.setText("To reset your password, click the link below:\n"
//                    //+ appUrl
//                    + "token=" + token.getResetToken());
//
//            emailService.sendEmail(passwordResetEmail);
//
//            return "Check your email for reset token";
//        }
//    }
//
//
//    @RequestMapping(value = "/reset", method = RequestMethod.POST)
//    public String resetPassword(@Valid PasswordReset passwordReset,
//                                BindingResult result){
//        if(result.hasErrors()){
//          //  attributes.addFlashAttribute("passwordReset", passwordReset);
//            return "token=" + passwordReset.getToken();
//        }
//        PasswordResetToken token = resetService.findByToken(passwordReset.getToken());
//        User user = token.getUser();
//        user.setPassword(passwordReset.getPassword());
//        userService.updatePassword(user);
//        return " please login";
//    }
//
//
//}
