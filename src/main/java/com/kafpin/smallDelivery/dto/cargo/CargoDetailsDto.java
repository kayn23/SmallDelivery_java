package com.kafpin.smallDelivery.dto.cargo;

import lombok.Data;

@Data
public class CargoDetailsDto {
    private Long id;
    private Float weight;
    private Long invoiceId;
}
