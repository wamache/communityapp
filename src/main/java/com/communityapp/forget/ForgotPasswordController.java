package com.communityapp.forget;//package com.frex.forget;
//
//import com.frex.JWT.JwtTokenFilter;
//import com.frex.dto.PasswordReset;
//
//import com.frex.model.User;
//import com.frex.reposirory.UserRepository;
//import com.frex.service.EmailService;
//import com.frex.service.UserService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//
//import java.util.Map;
//import java.util.Optional;
//import java.util.UUID;
//
//@RestController
//@CrossOrigin
//public class ForgotPasswordController {
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private EmailService emailService;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    private JwtTokenFilter jwtFilter;
//
//
//    // Process form submission from forgotPassword page
//    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
//    public ResponseEntity<?> processForgotPasswordForm(@RequestParam("email") String email, HttpServletRequest request) {
//
//        // Lookup user in database by e-mail
//        Optional<User> optional = userRepository.findByEmail(email);
//
//        if (!optional.isPresent()) {
//            throw new RuntimeException("We didn't find an account for that e-mail address.");
//        } else {
//
//            User user = optional.get();
//            user.setResetToken(UUID.randomUUID().toString());
//            userRepository.save(user);
//
//            String appUrl = request.getScheme() + "://" + request.getServerName();
//
//            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
//            passwordResetEmail.setFrom("benardwamache@gmail.com");
//            passwordResetEmail.setTo(user.getEmail());
//            passwordResetEmail.setSubject("Password Reset Request");
//            passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
//                    + "/reset?token=" + user.getResetToken());
//
//            emailService.sendEmail(passwordResetEmail);
//
//        }
//
//        return new ResponseEntity<>("a password reset is sent to your email", HttpStatus.OK);
//
//    }
//
//
//    @RequestMapping(value = "/reset", method = RequestMethod.POST)
//    public String resetPassword(@Valid PasswordReset passwordReset,
//                                HttpServletRequest request,
//                                BindingResult result) throws Exception {
//        userService.updatePassword(passwordReset.getPassword(), passwordReset.getToken());
//
////        //String token = request.getParameter("token");
////        if (result.hasErrors()) {
////            //  attributes.addFlashAttribute("passwordReset", passwordReset);
////            return "token=";
////        }
////        //PasswordResetToken token = resetService.findByToken(passwordReset.getToken());
////        //User user = token.getUser();
////        User user = userRepository.findByResetToken(token);
////        if (user == null) {
////
////            return "No token was found";
////        } else {
////            user.setPassword(passwordReset.getPassword());
////            userService.updatePassword(user);
//            return " Password changed successfully";
//        }
////    public String processResetPassword(@Valid PasswordReset passwordReset, HttpServletRequest request) {
////        String token = request.getParameter("token");
////        String password = request.getParameter("password");
////
////        User user = userRepository.findByResetToken(token);
////        if (user == null) {
////
////            return "message";
////        } else {
////            userService.updatePassword(user, password);
////            return "You have successfully changed your password.";
////
////
////        }
//
////    public ResponseEntity<?> setNewPassword(HttpServletRequest request) {
////
////        Optional<User> user = userRepository.findByResetToken(request.getParameter("token"));
////
////        if (user.isPresent()) {
////
////            User resetUser = user.get();
////
////            // Set new password
////            resetUser.setPassword(bCryptPasswordEncoder.encode(request.getParameter("password")));
////
////            // Set the reset token to null so it cannot be used again
////            resetUser.setResetToken(null);
////            userRepository.save(resetUser);
////            return new ResponseEntity<>("  Your Password has been reset link.", HttpStatus.OK);
////        } else {
////            return new ResponseEntity<>("Oops!  This is an invalid password reset link.", HttpStatus.INTERNAL_SERVER_ERROR);
////
////        }
//
//
//
//
//
//    // Going to reset page without a token redirects to login page
//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    public ResponseEntity<?> handleMissingParams(MissingServletRequestParameterException ex) {
//        return new ResponseEntity<>("The token is empty", HttpStatus.BAD_REQUEST);
//    }
//
////    @PostMapping("/resets")
////    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
////        try{
////            User user = userRepository.findByEmail(jwtFilter.getCurrentUser());
////            if(!user.equals(null)){
////                if(user.getPassword().equals(requestMap.get("oldPassword"))){
////                    user.setPassword(requestMap.get("newPassword"));
////                    userRepository.save(user);
////                    return new ResponseEntity<>("Password Updated Successfully", HttpStatus.OK);
////                }
////                return new ResponseEntity<>("Incorrect OldPassword", HttpStatus.BAD_REQUEST);
////
////            }
////            return new ResponseEntity<>("SOMETHING_WENT_WRONG", HttpStatus.INTERNAL_SERVER_ERROR);
////
////
////        } catch (Exception ex){
////            ex.printStackTrace();
////        }
////        return new ResponseEntity<>("SOMETHING_WENT_WRONG", HttpStatus.INTERNAL_SERVER_ERROR);
////    }
//
//
//
//}
