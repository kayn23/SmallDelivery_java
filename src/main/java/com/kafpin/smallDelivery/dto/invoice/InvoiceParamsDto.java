package com.kafpin.smallDelivery.dto.invoice;

import com.kafpin.smallDelivery.models.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceParamsDto {
  private Long endpoint;
  private Long senderId;
  private Long recipientId;
  @Enumerated(EnumType.STRING)
  private Status status;
  private BigDecimal price;
}
