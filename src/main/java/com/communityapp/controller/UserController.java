package com.communityapp.controller;


import com.communityapp.config.AppConstants;
import com.communityapp.dto.ApiResponse;
import com.communityapp.dto.UserRequest;
import com.communityapp.dto.UserResponse;
import com.communityapp.dto.VerifyResponse;
import com.communityapp.model.ConfirmationToken;
import com.communityapp.model.User;
import com.communityapp.reposirory.ConfirmationTokenRepository;
import com.communityapp.reposirory.UserRepository;
import com.communityapp.service.EmailService;
import com.communityapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailService emailService;


    @GetMapping("/users")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }


    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest) throws Exception {

        User user = new User(userRequest.getEmail(), userRequest.getPassword(), userRequest.getFullName(), userRequest.getPhoneNumber(), userRequest.getConfirmPassword());

        User existingUser = userRepository.findByEmailIgnoreCase(userRequest.getEmail());

        if (existingUser != null) {
            // throw new Exception("This email already exists!");

            String message = "The email you entered already exists";
            UserResponse response = new UserResponse(user.getEmail(), message);

            return ResponseEntity.ok().body(response);

        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
            String encodedCpassword = passwordEncoder.encode(userRequest.getConfirmPassword());
            user.setPassword(encodedPassword);
            userRequest.setConfirmPassword(encodedCpassword);
            userRepository.save(user);

            ConfirmationToken confirmationToken = new ConfirmationToken(user);

            //int otp = confirmationToken.generateOTP();

            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(userRequest.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("benardwamache@gmail.com");
            mailMessage.setText("To confirm your account, use the otp : "
            //        +"https://verifyaccounts.herokuapp.com/confirm-account?token="+confirmationToken.getConfirmationToken());
                    //+"http://localhost:8090/confirm-account?token="
                    +confirmationToken.getConfirmationToken());

            emailService.sendEmail(mailMessage);
        }

        String message = "User registered successfully, check email for verification token";

        UserResponse response = new UserResponse(user.getEmail(), message);

        return ResponseEntity.ok().body(response);
    }


    @RequestMapping(value="/confirm-account", method= {RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(String confirmationToken)
    {

        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            String message = "Your acount was verified successfully, proceed to login";

            VerifyResponse response = new VerifyResponse(message);

            return ResponseEntity.ok().body(response);
        }
        else
        {
            String message = "The token is invalid or broken!";

            VerifyResponse response = new VerifyResponse(message);

            return ResponseEntity.ok().body(response);



        }

        //return new ResponseEntity<>(token, HttpStatus.OK);
    }
    @PostMapping("/token/resend")
    @ResponseBody
    public ResponseEntity<?> resendRegistrationToken(@NotEmpty @RequestBody String expiredToken) {
        if (!userService.resendVerificationToken(expiredToken)) {
            return new ResponseEntity<>(new ApiResponse(false, "Token not found!"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.SUCCESS));
    }
}