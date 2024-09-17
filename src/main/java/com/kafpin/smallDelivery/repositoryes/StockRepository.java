package com.kafpin.smallDelivery.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafpin.smallDelivery.models.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
