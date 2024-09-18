package com.kafpin.smallDelivery.controllers;

import com.kafpin.smallDelivery.dto.city.CityDetailsDto;
import com.kafpin.smallDelivery.dto.city.CreateCityDto;
import com.kafpin.smallDelivery.models.City;
import com.kafpin.smallDelivery.services.CityService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
@Tag(name="Города")
public class CityController {
    private final CityService cityService;
    private final ModelMapper mapper;

    @GetMapping("")
    public List<CityDetailsDto> getAll(@RequestParam(required = false, name = "page") String pageQuery, @RequestParam(required = false, name = "per_page") String perPageQuery) {
        var p =  pageQuery != null ? Integer.parseInt(pageQuery) : 0;
        var page = p > 0 ? p - 1 : p;
        var per_page = perPageQuery == null ? pageQuery == null ? 10000: 100 : Integer.parseInt(perPageQuery);
        var cities = cityService.getAll(page, per_page);
        return cities.stream().map(element -> mapper.map(element, CityDetailsDto.class)).collect(Collectors.toList());
    }

    @PostMapping("")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long create(@RequestBody CreateCityDto request) {
        var city = new City();
        city.setName(request.getName());
        return cityService.create(city);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable String id) {
        cityService.delete(Long.parseLong(id));
    }

}
