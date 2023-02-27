package com.kasperovich.desoccer.service.user;

import com.kasperovich.desoccer.enums.Roles;
import com.kasperovich.desoccer.exception.BadPasswordException;
import com.kasperovich.desoccer.models.*;
import com.kasperovich.desoccer.repository.AddressRepository;
import com.kasperovich.desoccer.repository.DiscountRepository;
import com.kasperovich.desoccer.repository.RoleRepository;
import com.kasperovich.desoccer.repository.UserRepository;
import com.kasperovich.desoccer.service.role.RoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import static org.mockito.Mockito.*;


@FieldDefaults(level = AccessLevel.PRIVATE)
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    DiscountRepository discountRepository;
    @Mock
    AddressRepository addressRepository;
    @Mock
    RoleRepository roleRepository;
    @Mock
    RoleService roleService;

    @Mock
    PasswordEncoder encoder;

    @Captor
    ArgumentCaptor<Address> addressArgumentCaptor;

    UserService userService;

    @BeforeEach
    public void setup() {
        userService = new UserServiceImpl(userRepository, discountRepository, addressRepository, roleRepository, roleService, new BCryptPasswordEncoder());
    }


    @Test
    void updateUser() {
        encoder = new BCryptPasswordEncoder();
        User expectedUser = User
                .builder()
                .id(133L)
                .firstName("Test")
                .lastName("Case")
                .credentials(
                        new Credentials("login", encoder.encode("password"))
                )
                .mobilePhone("+18927221")
                .editData(
                        new Edit(Timestamp.valueOf("2012-01-01 00:00:00"), new Timestamp(new Date().getTime()))
                )
                .isDeleted(false)
                .email("testemail@gmail.com")
                .userDiscount(new Discount())
                .address(new Address())
                .role(new Role(Roles.ROLE_USER_AUTHORIZED))
                .orders(new HashSet<>())
                .build();

        User user = User
                .builder()
                .id(133L)
                .firstName("Test")
                .lastName("Case")
                .credentials(
                        new Credentials("login", "password")
                )
                .mobilePhone("+18927221")
                .editData(
                        new Edit(Timestamp.valueOf("2012-01-01 00:00:00"), null)
                )
                .isDeleted(false)
                .email("testemail@gmail.com")
                .userDiscount(new Discount())
                .address(new Address())
                .role(new Role(Roles.ROLE_USER_AUTHORIZED))
                .orders(new HashSet<>())
                .build();
        when(userRepository.save(user)).thenReturn(expectedUser);
        User actualUser = userService.updateUser(user);
        Assertions.assertThat(new Timestamp(new Date().getTime())).isCloseTo(actualUser.getEditData().getModificationDate(), 100);
        Assertions.assertThat(actualUser).isEqualTo(expectedUser);
        Assertions.assertThat(encoder.matches("password", user.getCredentials().getPassword())).isTrue();
    }

    @Test
    @DisplayName("Should throw exception when Id is not exist in DB")
    public void deleteUserHasToThrowExceptionWhenIdIsNotExisting() {

        Long id = 199998L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());
        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> userService.deleteUser(id));

    }

    @Test
    @DisplayName("Should set IS_DELETED=TRUE when deleting a user")
    public void shouldSetIsDeletedToTrueWhenDeletingUser() {
        User user = User
                .builder()
                .id(133L)
                .firstName("Test")
                .lastName("Case")
                .credentials(
                        new Credentials("login", "password78675")
                )
                .mobilePhone("+18927221")
                .editData(
                        new Edit(new Timestamp(new Date().getTime()), null)
                )
                .isDeleted(false)
                .email("testemail@gmail.com")
                .userDiscount(new Discount())
                .address(new Address())
                .role(new Role(Roles.ROLE_USER_AUTHORIZED))
                .orders(new HashSet<>())
                .build();

        when(userRepository.findById(41L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        User actualUser = userService.deleteUser(41L);

        Assertions.assertThat(actualUser).isEqualTo(user);
        Assertions.assertThat(actualUser.getIsDeleted()).isTrue();
    }

    @Test
    public void shouldCreateAUserWithoutCredentials() throws BadPasswordException {
        User user = User
                .builder()
                .id(133L)
                .firstName("Test")
                .lastName("Case")
                .mobilePhone("+18927221")
                .editData(
                        new Edit(new Timestamp(new Date().getTime()), null)
                )
                .isDeleted(false)
                .email("testemail@gmail.com")
                .address(Address
                        .builder()
                        .id(1L)
                        .lineOne("1")
                        .lineTwo("Main")
                        .lineThree("Street")
                        .city("NYC")
                        .province("New York")
                        .country("US")
                        .users(new HashSet<>())
                        .build())
                .orders(new HashSet<>())
                .build();
        when(roleService.findRoleByName(Roles.ROLE_USER_NOT_AUTHORIZED)).thenReturn(
                Role
                        .builder()
                        .id(1L)
                        .name(Roles.ROLE_USER_NOT_AUTHORIZED)
                        .users(new HashSet<>(Collections.singletonList(user)))
                        .build()
        );
        when(userRepository.save(user)).thenReturn(user);
        User actualUser = userService.createUser(user);
        Assertions.assertThat(actualUser.getRole().getName()).isEqualTo(Roles.ROLE_USER_NOT_AUTHORIZED);
        Assertions.assertThat(actualUser.getUserDiscount()).isNull();
        verify(addressRepository, times(1)).save(addressArgumentCaptor.capture());
    }

    @Test
    public void shouldThrowExceptionWhenPasswordIsInvalid() throws BadPasswordException {
        User user = User
                .builder()
                .id(133L)
                .firstName("Test")
                .lastName("Case")
                .mobilePhone("+18927221")
                .credentials(
                        new Credentials("login", "password")
                )
                .editData(
                        new Edit(new Timestamp(new Date().getTime()), null)
                )
                .isDeleted(false)
                .email("testemail@gmail.com")
                .address(Address
                        .builder()
                        .id(1L)
                        .lineOne("1")
                        .lineTwo("Main")
                        .lineThree("Street")
                        .city("NYC")
                        .province("New York")
                        .country("US")
                        .users(new HashSet<>())
                        .build())
                .orders(new HashSet<>())
                .build();
        when(roleService.findRoleByName(Roles.ROLE_USER_AUTHORIZED)).thenReturn(
                Role
                        .builder()
                        .id(1L)
                        .name(Roles.ROLE_USER_AUTHORIZED)
                        .users(new HashSet<>(Collections.singletonList(user)))
                        .build()
        );
        Exception exception=org.junit.jupiter.api.Assertions.assertThrows(BadPasswordException.class, () -> userService.createUser(user));
        Assertions.assertThat(exception.getMessage()).isEqualTo("Password must include at least one capital, or number, or symbol");
    }

}
