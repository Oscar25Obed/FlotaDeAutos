package com.example.vehiclemonitoring.controller;

import com.example.vehiclemonitoring.model.Rental;
import com.example.vehiclemonitoring.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping
    public List<Rental> findAll() {
        return rentalService.findAll();
    }

    @GetMapping("/{id}")
    public Rental findById(@PathVariable Long id) {
        return rentalService.findById(id);
    }

    @PostMapping
    public Rental create(@RequestBody Rental rental) {
        return rentalService.create(rental);
    }

    @PutMapping("/{id}")
    public Rental update(@PathVariable Long id, @RequestBody Rental rental) {
        Rental existingRental = rentalService.findById(id);
        if (existingRental != null) {
            existingRental.setVehicle(rental.getVehicle());
            existingRental.setDriver(rental.getDriver());
            existingRental.setRentalDate(rental.getRentalDate());
            existingRental.setReturnDate(rental.getReturnDate());
            existingRental.setTotalDistance(rental.getTotalDistance());
            return rentalService.update(existingRental);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        rentalService.delete(id);
    }
}
