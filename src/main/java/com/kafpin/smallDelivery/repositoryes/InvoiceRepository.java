package com.kafpin.smallDelivery.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafpin.smallDelivery.models.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
