package com.kasperovich.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Embeddable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Edit {

    Timestamp creationDate;

    Timestamp modificationDate;

}
