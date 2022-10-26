package com.kasperovich.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = {
        "users", "products"
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "discount_percent")
    @Size(min = 0, max = 100)
    Integer discountPercent;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="creationDate", column = @Column(name = "creation_date")),
            @AttributeOverride(name = "modificationDate", column = @Column(name = "modification_date"))
    })
    Edit editData;

    @OneToMany(mappedBy = "userDiscount", fetch = FetchType.EAGER)
    Set<User>users;

    @OneToMany(mappedBy = "productDiscount", fetch = FetchType.EAGER)
    Set<Product>products;
}
