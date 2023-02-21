package com.kasperovich.desoccer.dto.users;

import com.kasperovich.desoccer.dto.address.AddressCreateDto;
import com.kasperovich.desoccer.models.Credentials;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;



@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateDto {

    Credentials credentials;

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotBlank
    String mobilePhone;

    String email;

    AddressCreateDto address;

}
