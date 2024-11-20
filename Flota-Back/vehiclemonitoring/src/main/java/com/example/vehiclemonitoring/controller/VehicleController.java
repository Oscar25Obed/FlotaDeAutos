package com.example.vehiclemonitoring.controller;

import com.example.vehiclemonitoring.model.Vehicle;
import com.example.vehiclemonitoring.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.findAll();
    }

    @GetMapping("/{licensePlate}")
    public Vehicle getVehicleByLicensePlate(@PathVariable String licensePlate) {
        return vehicleService.findByLicensePlate(licensePlate);
    }

    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.save(vehicle);
    }

    @PutMapping("/{licensePlate}")
    public Vehicle updateVehicle(@PathVariable String licensePlate, @RequestBody Vehicle vehicle) {
        Vehicle currentVehicle = getVehicleByLicensePlate(licensePlate);
        currentVehicle.setMileage(vehicle.getMileage());
        currentVehicle.setFuelConsumption(vehicle.getFuelConsumption());
        return vehicleService.save(currentVehicle);
    }
}

