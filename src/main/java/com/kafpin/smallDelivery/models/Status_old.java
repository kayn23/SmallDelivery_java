//package com.kafpin.smallDelivery.models;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Entity
//@Table(name = "statuses")
//@Data
//@NoArgsConstructor
//public class Status_old {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private  String name;
//
//    @OneToMany(mappedBy = "status")
//    private List<Invoice> invoices;
//
//}
