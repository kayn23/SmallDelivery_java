package com.kafpin.smallDelivery.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafpin.smallDelivery.models.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long>, PagingAndSortingRepository<Invoice, Long> {
}
