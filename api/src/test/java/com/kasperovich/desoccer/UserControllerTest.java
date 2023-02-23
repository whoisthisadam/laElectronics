package com.kasperovich.desoccer;

import com.kasperovich.desoccer.controller.UserController;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @MockBean
    UserController userController;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void contextLoads() throws Exception{
        assertThat(userController).isNotNull();
    }
}
