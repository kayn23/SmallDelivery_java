package com.kafpin.smallDelivery.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.kafpin.smallDelivery.models.User;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long>, CrudRepository<User, Long> {
  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);
}
