package com.kasperovich.desoccer.repository;

import com.kasperovich.desoccer.models.Discount;
import com.kasperovich.desoccer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByCredentialsLogin(String login);

    Optional<User> findByCredentialsLoginOrEmail(String login, String email);

    Optional<User> findUserByEmail(String email);

    List<User>findUsersByUserDiscount(Discount discount);

}
