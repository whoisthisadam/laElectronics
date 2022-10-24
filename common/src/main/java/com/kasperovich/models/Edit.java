package com.kasperovich.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Edit {

    @NotBlank
    Timestamp creationDate=new Timestamp(new Date().getTime());

    Timestamp modificationDate;

}
