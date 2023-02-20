package com.kasperovich.service.user;

import com.kasperovich.exception.BadPasswordException;
import com.kasperovich.models.User;

import java.util.List;

public interface UserService {

    User createUser(User user) throws BadPasswordException;

    List<User> findAll();

    User updateUser(User user);

    User deleteUser(Long id);

}
