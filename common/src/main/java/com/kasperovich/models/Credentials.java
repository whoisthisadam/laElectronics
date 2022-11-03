package com.kasperovich.models;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Credentials {

    String login;

    String password;

}
