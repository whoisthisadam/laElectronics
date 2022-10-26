package com.kasperovich.models;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = "users")
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "line_1")
    String lineOne;

    @Column(name = "line_2")
    String lineTwo;

    @Column(name = "line_3")
    String lineThree;

    @Column(name = "city")
    String city;

    @Column(name = "province")
    String province;

    @Column(name = "country")
    String country;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<User>users;
}
