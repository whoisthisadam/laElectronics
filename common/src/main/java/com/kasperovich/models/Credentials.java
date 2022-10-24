package com.kasperovich.models;


import lombok.*;
import lombok.experimental.FieldDefaults;

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

    @NotBlank
    @Size(min = 3, max = 100)
    String login;

    @NotBlank
    @Size(min = 3, max = 100)
    String password;

}
