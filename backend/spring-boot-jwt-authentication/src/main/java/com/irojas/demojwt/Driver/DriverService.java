package com.irojas.demojwt.Driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    @Autowired
    private DriverRepository DriverRepository;

    public List<Driver> findAll() {
        return DriverRepository.findAll();
    }

    public Driver findById(Integer id) {
        return DriverRepository.findById(id).orElse(null);
    }

    public Driver create(Driver Driver) {
        return DriverRepository.save(Driver);
    }

    public Driver update(Driver Driver) {
        return DriverRepository.save(Driver);
    }

    public void delete(Integer id) {
        DriverRepository.deleteById(id);
    }
}
