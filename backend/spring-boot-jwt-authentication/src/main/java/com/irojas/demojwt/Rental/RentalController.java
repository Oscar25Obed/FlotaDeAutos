package com.irojas.demojwt.Rental;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/us/rental")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping
    public ResponseEntity<List<Rental>> getAllRentals() {
        List<Rental> rentals = rentalService.findAll();
        return ResponseEntity.ok(rentals);
    }

    @PostMapping("/search")
    public ResponseEntity<Rental> getRentalById(@RequestBody Integer id) {
        Rental rental = rentalService.findById(id);
        if (rental == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rental);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createRental(@RequestBody Rental rental) {
        Rental createdRental = rentalService.create(rental);
        return ResponseEntity.status(HttpStatus.CREATED).body("La renta ha sido creado con éxito.");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateRental(@RequestBody Rental rental) {
        Rental existingRental = rentalService.findById(rental.getId());
        if (existingRental == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("No se ha podido encontrar la renta.");
        }
        existingRental.setVehicle(rental.getVehicle());
        existingRental.setDriver(rental.getDriver());
        existingRental.setRentalDate(rental.getRentalDate());
        existingRental.setReturnDate(rental.getReturnDate());
        existingRental.setTotalDistance(rental.getTotalDistance());
        Rental updatedRental = rentalService.update(existingRental);
        return ResponseEntity.status(HttpStatus.OK).body("La renta ha sido actualizada con éxito.");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteRental(@RequestBody Integer id) {
        Rental existingRental = rentalService.findById(id);
        if (existingRental == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("No se ha podido encontrar la renta.");
        }
        rentalService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("La renta ha sido eliminada con éxito.");
    }
}
