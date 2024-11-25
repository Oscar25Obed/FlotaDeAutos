package com.irojas.demojwt.Driver;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Driver Controller", description = "Gestión de conductores")
public class DriverController {

    @Autowired
    private DriverService driverService;

    /**
     * Busca un conductor por su ID.
     *
     * @param id ID del conductor.
     * @return El conductor encontrado o un estado 404 si no existe.
     */
    @PostMapping("/search")
    @Operation(
            summary = "Buscar conductor por ID",
            description = "Busca un conductor registrado en el sistema por su identificador único.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "ID del conductor a buscar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "integer", description = "ID del conductor")
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Conductor encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Driver.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Conductor no encontrado"
                    )
            }
    )
    public ResponseEntity<Driver> getDriver(@RequestBody Integer id) {
        Driver driver = driverService.findById(id);
        if (driver == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(driver);
    }

    /**
     * Obtiene todos los conductores registrados.
     *
     * @return Lista de conductores.
     */
    @GetMapping
    @Operation(
            summary = "Obtener todos los conductores",
            description = "Recupera una lista de todos los conductores registrados en el sistema.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de conductores recuperada con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Driver.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No hay conductores registrados"
                    )
            }
    )
    public ResponseEntity<List<Driver>> findAll() {
        List<Driver> drivers = driverService.findAll();
        if (drivers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(drivers);
    }

    /**
     * Crea un nuevo conductor.
     *
     * @param driver Objeto del conductor a crear.
     * @return Mensaje de éxito.
     */
    @PostMapping("/create")
    @Operation(
            summary = "Crear un nuevo conductor",
            description = "Registra un nuevo conductor en el sistema.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del conductor a registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Driver.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Conductor creado con éxito",
                            content = @Content(mediaType = "text/plain")
                    )
            }
    )
    public ResponseEntity<String> create(@RequestBody Driver driver) {
        Driver createdDriver = driverService.create(driver);
        return ResponseEntity.status(HttpStatus.CREATED).body("El usuario ha sido creado con éxito.");
    }

    /**
     * Actualiza un conductor existente.
     *
     * @param driver Objeto con los datos del conductor a actualizar.
     * @return Mensaje de éxito o error si el conductor no existe.
     */
    @PutMapping("/update")
    @Operation(
            summary = "Actualizar un conductor existente",
            description = "Actualiza la información de un conductor existente en el sistema.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos actualizados del conductor",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Driver.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Conductor actualizado con éxito",
                            content = @Content(mediaType = "text/plain")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Conductor no encontrado"
                    )
            }
    )
    public ResponseEntity<String> update(@RequestBody Driver driver) {
        Driver existingDriver = driverService.findById(driver.getId());
        if (existingDriver != null) {
            existingDriver.setNombre(driver.getNombre());
            existingDriver.setApellido(driver.getApellido());
            existingDriver.setLicencia(driver.getLicencia());
            existingDriver.setFechaVencimientoLicencia(driver.getFechaVencimientoLicencia());
            existingDriver.setTipoLicencia(driver.getTipoLicencia());
            Driver updatedDriver = driverService.update(existingDriver);
            return ResponseEntity.status(HttpStatus.OK).body("El usuario ha sido actualizado con éxito.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe.");
        }
    }

    /**
     * Elimina un conductor.
     *
     * @param id ID del conductor a eliminar.
     * @return Mensaje de éxito o error si el conductor no existe.
     */
    @DeleteMapping("/delete")
    @Operation(
            summary = "Eliminar un conductor",
            description = "Elimina un conductor registrado en el sistema.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "ID del conductor a eliminar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "integer", description = "ID del conductor")
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Conductor eliminado con éxito",
                            content = @Content(mediaType = "text/plain")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Conductor no encontrado"
                    )
            }
    )
    public ResponseEntity<String> delete(@RequestBody Integer id) {
        Driver existingDriver = driverService.findById(id);
        if (existingDriver != null) {
            driverService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("El usuario ha sido eliminado con éxito.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe.");
        }
    }
}
