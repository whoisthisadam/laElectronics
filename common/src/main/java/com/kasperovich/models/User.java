package com.kasperovich.models;



import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data

@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "login", column = @Column(name = "login")),
            @AttributeOverride(name = "password", column = @Column(name = "password"))
    })
    Credentials credentials;

    @Column(name = "first_name")
    @NotBlank
    @Size(max = 20)
    String firstName;

    @Column(name = "last_name")
    @NotBlank
    @Size(max=30)
    String lastName;

    @Column(name = "mobile_phone")
    @NotBlank
    @Size(max=15)
    String mobilePhone;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="creationDate", column = @Column(name = "creation_date")),
            @AttributeOverride(name = "modificationDate", column = @Column(name = "modification_date"))
    })
    Edit editData;

    @Column(name = "is_deleted")
    Boolean isDeleted=false;

    @Column(name = "email")
    @Email
    @Size(max=100)
    String email;

//    @ManyToOne
//    @JoinColumn(name = "discount_id")
//
//
//
//    @ManyToOne
//    @JoinColumn(name = "address_id")

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;

}
