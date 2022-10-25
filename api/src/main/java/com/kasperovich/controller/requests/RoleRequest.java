package com.kasperovich.controller.requests;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Filter;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequest {

    String name;

}
