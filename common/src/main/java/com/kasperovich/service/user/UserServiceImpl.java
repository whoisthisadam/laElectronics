package com.kasperovich.service.user;

import com.kasperovich.enums.Discounts;
import com.kasperovich.enums.Roles;
import com.kasperovich.models.Credentials;
import com.kasperovich.models.User;
import com.kasperovich.repository.AddressRepository;
import com.kasperovich.repository.DiscountRepository;
import com.kasperovich.repository.RoleRepository;
import com.kasperovich.repository.UserRepository;
import com.kasperovich.service.role.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    DiscountRepository discountRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleService roleService;

    @Override
    public User createUser(@NotNull User user) {

        if(user.getCredentials()==null){
            user.setRole(roleService.findRoleByName(Roles.ROLE_USER_NOT_AUTHORIZED));
        }
        else{
            user.setRole(roleService.findRoleByName(Roles.ROLE_USER_AUTHORIZED));
        }

        if(user.getCredentials()!=null){
            user.setUserDiscount(discountRepository.findDiscountsByName(Discounts.LOGIN_DISCOUNT));
            PasswordEncoder encoder=new BCryptPasswordEncoder();
            user.setCredentials(new Credentials(user.getCredentials().getLogin(), encoder.encode(user.getCredentials().getPassword())));
        }


        addressRepository.save(user.getAddress());

        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
