package com.kafpin.smallDelivery.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="invoices")
@Data
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="endpoint_id")
    private Stock endpoint;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name="recipient_id")
    private User recipient;

    @ManyToOne
    private Status status;

    @OneToMany(mappedBy = "invoice")
    private List<Cargo> cargoes;

    private BigDecimal price;
}
