package com.example.vehiclemonitoring.repository;

import com.example.vehiclemonitoring.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {

}
