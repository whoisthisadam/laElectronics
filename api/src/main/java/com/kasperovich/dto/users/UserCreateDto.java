package com.kasperovich.dto.users;

import com.kasperovich.dto.address.AddressCreateDto;
import com.kasperovich.models.Address;
import com.kasperovich.models.Credentials;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


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
