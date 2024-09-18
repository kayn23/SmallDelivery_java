package com.kafpin.smallDelivery.dto.stock;

import com.kafpin.smallDelivery.dto.AbstractDto;
import lombok.Data;

@Data
public class StockDetailsDto extends AbstractDto {
  private String name;
  private String address;
  private String cityName;
}
