package com.kafpin.smallDelivery.dto.invoice;

import com.kafpin.smallDelivery.dto.stock.StockDetailsDto;
import com.kafpin.smallDelivery.dto.user.UserDetailsDto;
import com.kafpin.smallDelivery.models.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class InvoiceDetailsDto extends InvoiceSmallDetailsDto{
    private StockDetailsDto endpoint;
    private UserDetailsDto sender;
    private UserDetailsDto recipient;
    private BigDecimal price;
}
