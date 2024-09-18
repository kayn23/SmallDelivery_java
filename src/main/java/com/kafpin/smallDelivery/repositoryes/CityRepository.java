package com.kafpin.smallDelivery.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafpin.smallDelivery.models.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CityRepository extends PagingAndSortingRepository<City, Long>, CrudRepository<City, Long> {
    public boolean existsByName(String name);
    public void deleteById(Long id);

}
