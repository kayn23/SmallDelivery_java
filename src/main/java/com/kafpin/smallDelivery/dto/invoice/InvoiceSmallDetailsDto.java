package com.kafpin.smallDelivery.dto.invoice;

import com.kafpin.smallDelivery.models.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class InvoiceSmallDetailsDto {
    private Long id;
    @Enumerated(EnumType.STRING)
    private Status status;
}
