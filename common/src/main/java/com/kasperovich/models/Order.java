package com.kasperovich.models;

import com.kasperovich.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {
        "user","products"
})
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "order_details")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "total")
    @Size(min=0)
    Long total;

    @OneToOne
    @JoinColumn(name = "payment_id")
    Payment payment;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="creationDate", column = @Column(name = "creation_date")),
            @AttributeOverride(name = "modificationDate", column = @Column(name = "modification_date"))
    })
    Edit editData;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    @ManyToMany(mappedBy = "orders", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<Product>products;



}
