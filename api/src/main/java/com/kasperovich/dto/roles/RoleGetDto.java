package com.kasperovich.dto.roles;

import com.kasperovich.models.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RoleGetDto {

    Long id;

    String name;

    Set<User> userIds;

}
