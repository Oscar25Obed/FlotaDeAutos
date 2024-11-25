package com.irojas.demojwt.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    Vehicle findByLicensePlate(String licensePlate);
}

