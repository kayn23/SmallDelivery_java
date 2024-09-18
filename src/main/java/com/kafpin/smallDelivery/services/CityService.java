package com.kafpin.smallDelivery.services;

import com.kafpin.smallDelivery.models.City;
import com.kafpin.smallDelivery.repositoryes.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository repository;

    public List<City> getAll(Integer page, Integer perPage) {
        var pag = PageRequest.of(page, perPage);
        return repository.findAll(pag).stream().toList();
    }

    public Optional<City> getCityById(Long id) {
        return repository.findById(id);
    }

    public Long create(City city) {
        if (repository.existsByName(city.getName())) {
            throw new RuntimeException("Такой город уже существует в базе");
        }
        return repository.save(city).getId();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
