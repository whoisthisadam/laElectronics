package com.kasperovich.desoccer.service.user;

import com.kasperovich.desoccer.exception.BadPasswordException;
import com.kasperovich.desoccer.models.User;

import java.util.List;

public interface UserService {

    User createUser(User user) throws BadPasswordException;

    List<User> findAll();

    User updateUser(User user);

    User deleteUser(Long id);

}
