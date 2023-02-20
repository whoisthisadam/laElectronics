package com.kasperovich.repository;

import com.kasperovich.models.Discount;
import com.kasperovich.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByCredentialsLogin(String login);

    Optional<User> findByCredentialsLoginOrEmail(String login, String email);

    Optional<User> findUserByEmail(String email);

    List<User>findUsersByUserDiscount(Discount discount);

}
