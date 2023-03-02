package com.kasperovich.desoccer.dto.users;

import com.kasperovich.desoccer.dto.discount.DiscountGetDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGetDto {

    Long id;

    String firstName;

    String lastName;

    String mobilePhone;

    String email;

    DiscountGetDto discount;

}
