package com.kafpin.smallDelivery.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafpin.smallDelivery.models.Stock;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StockRepository extends PagingAndSortingRepository<Stock, Long>, JpaRepository<Stock, Long> {
    public void deleteById(long id);
}
