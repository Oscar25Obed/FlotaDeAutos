package com.example.vehiclemonitoring.repository;

import com.example.vehiclemonitoring.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
