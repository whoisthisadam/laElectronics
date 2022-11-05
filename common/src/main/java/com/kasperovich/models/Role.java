package com.kasperovich.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kasperovich.enums.Roles;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = {
        "users"
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column
    @Enumerated(EnumType.STRING)
    Roles name;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    @JsonIgnore
    Set<User>users;

}
