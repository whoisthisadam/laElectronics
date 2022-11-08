package com.kasperovich.service.user;

import com.kasperovich.enums.Discounts;
import com.kasperovich.enums.Roles;
import com.kasperovich.exception.BadPasswordException;
import com.kasperovich.models.Credentials;
import com.kasperovich.models.Edit;
import com.kasperovich.models.User;
import com.kasperovich.repository.AddressRepository;
import com.kasperovich.repository.DiscountRepository;
import com.kasperovich.repository.RoleRepository;
import com.kasperovich.repository.UserRepository;
import com.kasperovich.service.role.RoleService;
import com.kasperovich.util.ValidCheck;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    PasswordEncoder encoder=new BCryptPasswordEncoder();

    @Override
    public User createUser(@Valid User user) throws BadPasswordException {

        if(user.getCredentials()==null){
            user.setRole(roleService.findRoleByName(Roles.ROLE_USER_NOT_AUTHORIZED));
        }
        else{
            user.setRole(roleService.findRoleByName(Roles.ROLE_USER_AUTHORIZED));
        }

        if(user.getCredentials()!=null){
            if(!new ValidCheck().isPasswordValid(user.getCredentials().getPassword())){
                throw new BadPasswordException("Password must include at least one capital, or number, or symbol");
            }
            user.setUserDiscount(discountRepository.findDiscountsByName(Discounts.LOGIN_DISCOUNT));
            user.setCredentials(new Credentials(user.getCredentials().getLogin(), encoder.encode(user.getCredentials().getPassword())));
        }


        addressRepository.save(user.getAddress());

        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll().stream().filter(x-> !x.getIsDeleted()).collect(Collectors.toList());
    }

    @Override
    public User updateUser(@Valid User user) {
        user.setEditData(new Edit(user.getEditData().getCreationDate(), new Timestamp(new Date().getTime())));
        if(user.getCredentials()!=null){
            user.setCredentials(new Credentials(user.getCredentials().getLogin(), encoder.encode(user.getCredentials().getPassword())));
        }
        return userRepository.save(user);
    }

    @Override
    public User deleteUser(Long id) {
        User user=userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        user.setIsDeleted(true);
        return userRepository.save(user);
    }
}
