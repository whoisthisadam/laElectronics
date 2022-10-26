package com.kasperovich.models;

import com.kasperovich.enums.PaymentProviders;
import com.kasperovich.enums.PaymentStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "payment_details")
@EqualsAndHashCode(exclude = "order")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    Long id;

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Order order;

    @Column(length = 10, precision = 2)
    Double amount;

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    PaymentProviders provider;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    PaymentStatus status;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="creationDate", column = @Column(name = "creation_date")),
            @AttributeOverride(name = "modificationDate", column = @Column(name = "modification_date"))
    })
    Edit editData;
}