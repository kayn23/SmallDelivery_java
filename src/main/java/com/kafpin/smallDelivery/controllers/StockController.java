package com.kafpin.smallDelivery.controllers;

import com.kafpin.smallDelivery.dto.stock.StockDetailsDto;
import com.kafpin.smallDelivery.dto.stock.StockParamsDto;
import com.kafpin.smallDelivery.models.Stock;
import com.kafpin.smallDelivery.services.CityService;
import com.kafpin.smallDelivery.services.StockService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
@Tag(name = "Склады")
public class StockController {
    private final StockService stockService;
    private final CityService cityService;
    private final ModelMapper mapper;

    @GetMapping("")
    public List<StockDetailsDto> getAll(@RequestParam(name="page", required = false) String pageQuery,
                                        @RequestParam(name="per_page", required = false) String perPageQuery) {
        var p =  pageQuery != null ? Integer.parseInt(pageQuery) : 0;
        var page = p > 0 ? p - 1 : p;
        var per_page = perPageQuery == null ? pageQuery == null ? 10000: 100 : Integer.parseInt(perPageQuery);
        return stockService.getAll(page, per_page)
                .stream()
                .map(element -> mapper.map(element, StockDetailsDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @SecurityRequirement(name="bearerAuth")
    public Long create(@RequestBody StockParamsDto request) {
        var city = cityService.getCityById(request.getCityId());
        if (city.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Неккоректно выбран город");
        }
        var stock = Stock.builder()
                .address(request.getAddress())
                .name(request.getName())
                .city(city.get())
                .build();
        return stockService.create(stock).getId();
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @SecurityRequirement(name="bearerAuth")
    public StockDetailsDto update(@RequestBody StockParamsDto request, @PathVariable String id) {
        var stockOpt = stockService.findById(Long.parseLong(id));
        if (stockOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        var stock = stockOpt.get();
        stock.setAddress(request.getAddress());
        stock.setName(request.getName());
        var city = cityService.getCityById(request.getCityId());
        if (city.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Неккоректно выбран город");
        }
        stock.setCity(city.get());
        return mapper.map(stockService.update(stock), StockDetailsDto.class);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name="bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable String id) {
        var stockOpt = stockService.findById(Long.parseLong(id));
        if (stockOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        stockService.delete(stockOpt.get());
    }
}
