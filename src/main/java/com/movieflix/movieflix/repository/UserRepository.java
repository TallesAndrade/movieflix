package com.movieflix.movieflix.repository;

import com.movieflix.movieflix.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<UserDetails> findUserByEmail(String username);
}
