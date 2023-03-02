package com.kasperovich.desoccer.repository;

import com.kasperovich.desoccer.enums.Discounts;
import com.kasperovich.desoccer.enums.Roles;
import com.kasperovich.desoccer.models.Credentials;
import com.kasperovich.desoccer.models.Edit;
import com.kasperovich.desoccer.models.User;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;


@DataJpaTest
@ActiveProfiles("test")
@FieldDefaults(level = AccessLevel.PRIVATE)
class UserRepositoryTestEmbedded {


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    DiscountRepository discountRepository;


    public User createDefaultUser(){
        return User
                .builder()
                .firstName("Default")
                .lastName("User")
                .credentials(
                        new Credentials("testLogin", "testPassword1")
                )
                .mobilePhone("+1 111 1624")
                .editData(
                        new Edit(new Timestamp(new Date().getTime()), null)
                )
                .isDeleted(false)
                .email("testemail@gmail.com")
                .userDiscount(discountRepository.findDiscountsByName(Discounts.LOGIN_DISCOUNT))
                .address(addressRepository.findByPostcode("10001"))
                .role(roleRepository.findAllByName(Roles.USER_AUTHORIZED))
                .orders(new HashSet<>())
                .build();
    }


    @Test
    @Sql("classpath:test-data-for-user.sql")
    public void shouldFindUserByLoginOREmail(){

        Optional<User> realUserByLogin=userRepository.findByCredentialsLoginOrEmail("testLogin", "testLogin");
        Optional<User> realUserByEmail=userRepository.findByCredentialsLoginOrEmail("testemail@gmail.com", "testemail@gmail.com");

        Assertions.assertThat(realUserByEmail).isNotEmpty();
        Assertions.assertThat(realUserByLogin).isNotEmpty();
        Assertions.assertThat(realUserByEmail).usingRecursiveComparison().isEqualTo(realUserByLogin);
    }

    @Test
    public void shouldSaveUser(){
        User user=createDefaultUser();
        User savedUser=userRepository.save(user);
        Assertions.assertThat(savedUser).usingRecursiveComparison().ignoringFields("id").isEqualTo(user);
    }

}
