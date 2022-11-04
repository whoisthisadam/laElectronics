package com.kasperovich.repository;

import com.kasperovich.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findUserByCredentialsLogin(String login);

    public Optional<User> findUserByEmail(String email);

}
