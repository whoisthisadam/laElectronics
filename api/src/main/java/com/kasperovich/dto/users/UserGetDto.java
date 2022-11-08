package com.kasperovich.dto.users;

import com.kasperovich.dto.discount.DiscountGetDto;
import com.kasperovich.enums.Discounts;
import com.kasperovich.enums.Roles;
import com.kasperovich.models.Address;
import com.kasperovich.models.Discount;
import com.kasperovich.models.Edit;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserGetDto {

    Long id;

    String firstName;

    String lastName;

    String mobilePhone;

    String email;

    DiscountGetDto discount;

}
