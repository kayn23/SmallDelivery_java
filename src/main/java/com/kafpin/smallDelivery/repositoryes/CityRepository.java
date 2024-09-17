package com.kafpin.smallDelivery.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafpin.smallDelivery.models.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
