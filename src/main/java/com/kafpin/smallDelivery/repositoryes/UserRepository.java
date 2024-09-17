package com.kafpin.smallDelivery.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kafpin.smallDelivery.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
