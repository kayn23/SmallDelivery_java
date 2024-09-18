package com.kafpin.smallDelivery.dto.stock;

import lombok.Data;

@Data
public class StockParamsDto {
    private String name;
    private String address;
    private Long cityId;
}
