package com.kasperovich.desoccer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kasperovich.desoccer.dto.discount.DiscountGetDto;
import com.kasperovich.desoccer.dto.users.UserGetDto;
import com.kasperovich.desoccer.enums.Discounts;
import com.kasperovich.desoccer.mapping.converters.discount.DiscountCreateConverter;
import com.kasperovich.desoccer.mapping.converters.discount.DiscountGetConverter;
import com.kasperovich.desoccer.mapping.converters.order.OrderGetConverter;
import com.kasperovich.desoccer.mapping.converters.order.OrderUpdateConverter;
import com.kasperovich.desoccer.mapping.converters.product.ProductUpdateConverter;
import com.kasperovich.desoccer.mapping.converters.user.UserGetConverter;
import com.kasperovich.desoccer.mapping.converters.user.UserUpdateConverter;
import com.kasperovich.desoccer.mapping.mappers.UserMapper;
import com.kasperovich.desoccer.models.*;
import com.kasperovich.desoccer.repository.OrderRepository;
import com.kasperovich.desoccer.security.jwt.JwtTokenHelper;
import com.kasperovich.desoccer.service.user.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@Import(WebSecurityConfiguration.class)
//@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@WebMvcTest(UserController.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class UserControllerTest {

    @MockBean
    UserService userService;
    @MockBean
    UserMapper userMapper;
    @MockBean
    UserUpdateConverter userUpdateConverter;

    @InjectMocks
    UserGetConverter userGetConverter;
    @Mock
    DiscountGetConverter discountGetConverter;

    @MockBean
    OrderUpdateConverter orderUpdateConverter;

    @MockBean
    ProductUpdateConverter productUpdateConverter;
    @MockBean
    OrderRepository orderRepository;

    @MockBean
    DiscountCreateConverter discountCreateConverter;

    @MockBean
    JwtTokenHelper jwtTokenHelper;

    @MockBean
    OrderGetConverter orderGetConverter;

    @Autowired
    JacksonTester<List<UserGetDto>>jsonUserListGet;

    @InjectMocks
    UserController userController;

    @InjectMocks
    ObjectMapper objectMapper;



//    @Mock
//    AuthenticationTokenFilter authenticationTokenFilter;

//    @Mock
//    ServletRequest request;
//
//    @Mock
//    ServletResponse response;
//
//    @Mock
//    FilterChain filterChain;

    @Autowired
    MockMvc mockMvc;



    public User createDefaultUser(){
        return User
                .builder()
                .id(133L)
                .firstName("Test")
                .lastName("Case")
                .mobilePhone("+1 416 123 4567")
                .credentials(
                        new Credentials("testlogin", "testPassword1")
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
                .userDiscount(
                        Discount
                                .builder()
                                .id(1L)
                                .name(Discounts.TEST_DISCOUNT)
                                .discountPercent(10)
                                .editData(new Edit())
                                .isDeleted(false)
                                .build()
                )
                .orders(new HashSet<>())
                .build();
    }

    @Test
    void shouldSecureFindAllUsers() throws Exception {
        mockMvc.perform(get("/data/users/all"))
                .andExpect(status().is(401));
    }

    @Test
    @WithAnonymousUser
    void cannotCreateCustomerIfNotAuthorized() throws Exception {
        mockMvc.perform(get("/data/users/all")
                        .with(csrf())
                )
                .andExpect(status().isUnauthorized());
    }



    @Test
    @WithMockUser
    void shouldReturnListOfUsers() throws Exception {
        User user=createDefaultUser();
        given(userService.findAll()).willReturn(List.of(user));
        given(userMapper.toDto(user)).willReturn(userGetConverter.convert(user));
        when(discountGetConverter.convert(any(Discount.class))).thenReturn(
                DiscountGetDto.builder()
                        .id(user.getUserDiscount().getId())
                        .name(user.getUserDiscount().getName())
                        .percent(user.getUserDiscount().getDiscountPercent())
                        .build()
        );
        MockHttpServletResponse response=mockMvc.perform(get("/data/users/all")
                )
                .andExpect(status().isOk()).andReturn().getResponse();
        String expected=jsonUserListGet.write(List.of(userMapper.toDto(user))).getJson();
        Assertions.assertThat(response.getContentAsString()).isEqualTo(expected);
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

}