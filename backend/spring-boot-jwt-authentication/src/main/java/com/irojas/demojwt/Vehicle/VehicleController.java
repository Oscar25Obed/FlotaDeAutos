package com.irojas.demojwt.Vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/us/vehicle")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.findAll();
        return ResponseEntity.ok(vehicles);
    }

    @PostMapping("/search")
    public ResponseEntity<Vehicle> getVehicleByLicensePlate(@RequestBody VehicleRequest vehicleRequest) {
        Vehicle vehicle = vehicleService.findByLicensePlate(vehicleRequest.getLicensePlate());
        if (vehicle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehicle);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createVehicle(@RequestBody Vehicle vehicle) {
        Vehicle savedVehicle = vehicleService.save(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body("El vehiculo ha sido creado con éxito.");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateVehicle(@RequestBody Vehicle vehicle) {
        Vehicle currentVehicle = vehicleService.findByLicensePlate(vehicle.getLicensePlate());
        if (currentVehicle == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El vehiculo no existe.");
        }
        currentVehicle.setMileage(vehicle.getMileage());
        currentVehicle.setFuelConsumption(vehicle.getFuelConsumption());
        Vehicle updatedVehicle = vehicleService.save(currentVehicle);
        return ResponseEntity.ok("El vehiculo ha sido actualizado con éxito.");
    }
}
