package com.example.vehiclemonitoring.repository;

import com.example.vehiclemonitoring.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}

