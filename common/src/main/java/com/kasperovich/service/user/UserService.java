package com.kasperovich.service.user;

import com.kasperovich.models.User;
import org.springframework.context.Lifecycle;

import java.util.List;

public interface UserService {

    public User createUser(User user);

    public List<User> findAll();

}
