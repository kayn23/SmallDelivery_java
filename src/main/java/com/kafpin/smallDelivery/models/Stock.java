package com.kafpin.smallDelivery.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="stocks")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "endpoint")
    private List<Invoice> invoices;

    private Boolean deleted;
}
