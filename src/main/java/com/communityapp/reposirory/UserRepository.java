package com.communityapp.reposirory;

import com.communityapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("user")
public interface UserRepository extends CrudRepository<User, String> {
    User findByEmailIgnoreCase(String email);

    Optional<User> findByEmail(String email);


   // User findByToken(String token);

    //Optional<User> findByResetToken(String token);

    User findByResetToken(String token);


    User findByPhoneNumberIgnoreCase(String phoneNumber);

    User findByUserid(Long userid);

    Optional<User> findByPhoneNumber(String targetno);
}
