package com.kasperovich.desoccer.repository;

import com.kasperovich.desoccer.models.Discount;
import com.kasperovich.desoccer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByCredentialsLoginOrEmail(String login, String email);

    List<User>findUsersByUserDiscount(Discount discount);

    Optional<User>findUserById(Long id);

}
