package com.irojas.demojwt.Driver;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/us/driver")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class DriverController {

    @Autowired
    private DriverService DriverService;

    @PostMapping("/search")
    public ResponseEntity<Driver> getDriver(@RequestBody Integer id) {
        Driver driver = DriverService.findById(id);
        if (driver == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(driver);
    }

    @GetMapping
    public ResponseEntity<List<Driver>> findAll() {
        List<Driver> drivers = DriverService.findAll();
        if (drivers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(drivers);
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Driver Driver) {
        Driver createdDriver = DriverService.create(Driver);
        return ResponseEntity.status(HttpStatus.CREATED).body("El usuario ha sido creado con éxito.");
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Driver Driver) {
        Driver existingDriver = DriverService.findById(Driver.getId());
        if (existingDriver != null) {
            existingDriver.setNombre(Driver.getNombre());
            existingDriver.setApellido(Driver.getApellido());
            existingDriver.setLicencia(Driver.getLicencia());
            existingDriver.setFechaVencimientoLicencia(Driver.getFechaVencimientoLicencia());
            existingDriver.setTipoLicencia(Driver.getTipoLicencia());
            Driver updatedDriver = DriverService.update(existingDriver);
            return ResponseEntity.status(HttpStatus.OK).body("El usuario ha sido actualizado con éxito.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe.");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Integer id) {
        Driver existingDriver = DriverService.findById(id);
        if (existingDriver != null) {
            DriverService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("El usuario ha sido eliminado con éxito.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe.");
        }
    }

}
