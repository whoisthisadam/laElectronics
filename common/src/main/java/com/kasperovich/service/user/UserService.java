package com.kasperovich.service.user;

import com.kasperovich.exception.BadPasswordException;
import com.kasperovich.models.User;
import org.springframework.context.Lifecycle;

import java.util.List;

public interface UserService {

    public User createUser(User user) throws BadPasswordException;

    public List<User> findAll();

    public User updateUser(User user);

    public User deleteUser(Long id);

}
