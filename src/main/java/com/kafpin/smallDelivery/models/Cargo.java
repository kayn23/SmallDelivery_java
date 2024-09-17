package com.kafpin.smallDelivery.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cargoes")
@Data
@NoArgsConstructor
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float weight;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}
