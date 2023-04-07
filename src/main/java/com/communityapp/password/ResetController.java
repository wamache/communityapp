package com.communityapp.password;
import com.communityapp.JWT.JwtTokenFilter;
import com.communityapp.Twilio.Config.TwilioConfiguration;
import com.communityapp.model.ConfirmationToken;
import com.communityapp.model.User;
import com.communityapp.reposirory.ConfirmationTokenRepository;
import com.communityapp.reposirory.UserRepository;
import com.communityapp.service.EmailService;
import com.communityapp.service.UserService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class ResetController {

    @Autowired
    private TwilioConfiguration twilioConfiguration;

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    JwtTokenFilter jwtFilter;

    @Autowired
    private UserService userService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    //    @Autowired
//    private BCryptPasswordEncoder encoder;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    @RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
    public ResponseEntity<?> forgotUserPassword(@RequestParam("email") String email) {
        User existingUser = userRepository.findByEmailIgnoreCase(email);
        if (existingUser != null) {
            // Create token
            ConfirmationToken confirmationToken = new ConfirmationToken(existingUser);

            // Save it
            confirmationTokenRepository.save(confirmationToken);

            // Create the email
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(existingUser.getEmail());
            mailMessage.setSubject("Complete Password Reset!");
            mailMessage.setFrom("benardwamache@gmail.com");
            mailMessage.setText("To complete the password reset process, your otp is : "
                     + confirmationToken.getConfirmationToken());

            // Send the email
            emailService.sendEmail(mailMessage);
            return new ResponseEntity<>("ok", HttpStatus.OK);


        } else {
            return new ResponseEntity<>("User was not found", HttpStatus.OK);

        }

    }

    @RequestMapping(value = "/forgot-password-by-phone", method = RequestMethod.POST)
    public ResponseEntity<?> forgotUserPasswordByPhone(@RequestParam("phoneNumber") String phoneNumber) {
        User existingUser = userRepository.findByPhoneNumberIgnoreCase(phoneNumber);
        if (existingUser != null) {
            // Create token
            ConfirmationToken confirmationToken = new ConfirmationToken(existingUser);

            // Save it
            confirmationTokenRepository.save(confirmationToken);
            PhoneNumber to = new PhoneNumber(existingUser.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
            String otp = confirmationToken.getConfirmationToken();
            String message = "Your OTP is " + otp + ". Use it to verify your password reset!";

            MessageCreator creator = Message.creator(to, from, message);
            creator.create();

            return new ResponseEntity<>("ok", HttpStatus.OK);


        } else {
            return new ResponseEntity<>("User was not found", HttpStatus.OK);

        }
    }

    @RequestMapping(value = "/confirm-reset", method = {RequestMethod.POST})
    public ResponseEntity<?> validateResetToken(@RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            String message = "The token is confirmed";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            String message = "The link is invalid or broken!";
            //modelAndView.addObject("message", "The link is invalid or broken!");
            //modelAndView.setViewName("error");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        //return new ResponseEntity<>("message", HttpStatus.OK);
    }

    // Endpoint to update a user's password
//    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
//    public ResponseEntity<?> resetUserPassword( ResetDto resetDto) throws Exception {
//        try {
//           // User user = new User(resetDto.getPassword(), resetDto.getConfirmPassword());
//          //  ConfirmationToken confirmationToken = new ConfirmationToken(resetDto.getToken());
//          //   if (user.getEmailId() != null) {
//            // Use email to find user
//            User user = userRepository.findByEmailIgnoreCase(jwtFilter.getCurrentUser());
//            //confirmationTokenRepository.findByConfirmationToken(String.valueOf(confirmationToken));
//           // confirmationToken.getConfirmationToken(confirmationToken);
//            //user.getEmailId();
//            user.setPassword(encoder.encode(resetDto.getPassword()));
//            userRepository.save(user);
//            String text = "password reset successfully";
//            return new ResponseEntity<>(text, HttpStatus.OK);
//        }catch (Exception ex){
//            throw new Exception("Unable to reset your password");
//        }
//        //    modelAndView.addObject("message", "Password successfully reset. You can now log in with the new credentials.");
//          //  modelAndView.setViewName("successResetPassword");
//       // } else {
//         //   String text = "The link is invalid or broken!";
//
//           // return new ResponseEntity<>(text, HttpStatus.BAD_REQUEST);
//        }
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public String resetPassword(@Valid ResetDto resetDto) {
       // ConfirmationToken otp = new ConfirmationToken();
//        //ConfirmationToken vToken = confirmationTokenRepository.findByConfirmationToken(resetDto.getToken());
//
//
//        //if (vToken != null) {
//            User user = new User(resetDto.getPassword(), resetDto.getConfirmPassword());
//           // User user = userRepository.findByEmailIgnoreCase(vToken.getUser().getEmail());
////        if(result.hasErrors()){
////
////            return "token=" + resetDto.getToken();
////        }
//            //ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(resetDto.getToken());
//            //User user = token.getUser();
//            // User user = new User();
//
//            // User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
//            // user.setPassword(resetDto.getPassword());
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(resetDto.getToken());

        if (token != null) {
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
          //  user.setPassword(passwordEncoder.encode(resetDto.getPassword()));

        String encodedPassword = passwordEncoder.encode(resetDto.getPassword());
        String encodedCpassword = passwordEncoder.encode(resetDto.getConfirmPassword());
        user.setPassword(encodedPassword);
        resetDto.setConfirmPassword(encodedCpassword);
        userRepository.save(user);

          //  userRepository.save(user);
            // userService.updatePassword(user);
            return "Updated successfully";
        } else {
            return "update was not successfull";
        }

    }
}