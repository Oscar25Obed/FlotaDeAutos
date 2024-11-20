package com.example.vehiclemonitoring.controller;

import com.example.vehiclemonitoring.model.Driver;
import com.example.vehiclemonitoring.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/drivers")
public class DriverController {

  @Autowired
  private DriverService DriverService;

  @GetMapping
  public List<Driver> findAll() {
    return DriverService.findAll();
  }

  @GetMapping("/{id}")
  public Driver findById(@PathVariable Long id) {
    return DriverService.findById(id);
  }

  @PostMapping
  public Driver create(@RequestBody Driver Driver) {
    return DriverService.create(Driver);
  }

  @PutMapping("/{id}")
  public Driver update(@PathVariable Long id, @RequestBody Driver Driver) {
    Driver existingDriver = DriverService.findById(id);
    if (existingDriver != null) {
      existingDriver.setNombre(Driver.getNombre());
      existingDriver.setApellido(Driver.getApellido());
      existingDriver.setLicencia(Driver.getLicencia());
      existingDriver.setFechaVencimientoLicencia(Driver.getFechaVencimientoLicencia());
      existingDriver.setTipoLicencia(Driver.getTipoLicencia());
      return DriverService.update(existingDriver);
    } else {
      return null;
    }
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    DriverService.delete(id);
  }
}
