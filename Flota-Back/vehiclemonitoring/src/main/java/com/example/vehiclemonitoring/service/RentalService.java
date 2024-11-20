package com.example.vehiclemonitoring.service;

import com.example.vehiclemonitoring.model.Rental;
import com.example.vehiclemonitoring.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    public List<Rental> findAll() {
        return rentalRepository.findAll();
    }

    public Rental findById(Long id) {
        return rentalRepository.findById(id).orElse(null);
    }

    public Rental create(Rental rental) {
        return rentalRepository.save(rental);
    }

    public Rental update(Rental rental) {
        return rentalRepository.save(rental);
    }

    public void delete(Long id) {
        rentalRepository.deleteById(id);
    }
}
