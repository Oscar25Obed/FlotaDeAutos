package com.example.vehiclemonitoring.service;

import com.example.vehiclemonitoring.model.Driver;
import com.example.vehiclemonitoring.repository.DriverRepository;
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
  
  public Driver findById(Long id) {
    return DriverRepository.findById(id).orElse(null);
  }
  
  public Driver create(Driver Driver) {
    return DriverRepository.save(Driver);
  }
  
  public Driver update(Driver Driver) {
    return DriverRepository.save(Driver);
  }
  
  public void delete(Long id) {
    DriverRepository.deleteById(id);
  }
}
