package com.kasperovich.models;

import com.kasperovich.enums.Roles;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {
        "users"
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    Long id;

    @Column
    @Size(max = 40)
    @Enumerated(EnumType.STRING)
    Roles name;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    Set<User>users;

}
