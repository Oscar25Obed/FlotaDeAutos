package com.irojas.demojwt.Vehicle;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
@RequestMapping(value = "/api/v1/us/vehicle")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
@Tag(name = "Vehicle Controller", description = "Gestión de vehículos")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    /**
     * Obtiene todos los vehículos registrados.
     *
     * @return Lista de vehículos.
     */
    @GetMapping
    @Operation(
            summary = "Obtener todos los vehículos",
            description = "Recupera una lista de todos los vehículos registrados en el sistema.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de vehículos recuperada con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Vehicle.class))
                            )
                    )
            }
    )
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.findAll();
        return ResponseEntity.ok(vehicles);
    }

    /**
     * Busca un vehículo por su placa.
     *
     * @param vehicleRequest Objeto con la placa del vehículo a buscar.
     * @return El vehículo encontrado o un estado 404 si no existe.
     */
    @PostMapping("/search")
    @Operation(
            summary = "Buscar vehículo por placa",
            description = "Busca un vehículo registrado en el sistema utilizando su número de placa.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Placa del vehículo a buscar",
                    required = true,
                    content = @Content(schema = @Schema(implementation = VehicleRequest.class))
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Vehículo encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Vehicle.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Vehículo no encontrado"
                    )
            }
    )
    public ResponseEntity<Vehicle> getVehicleByLicensePlate(@RequestBody VehicleRequest vehicleRequest) {
        Vehicle vehicle = vehicleService.findByLicensePlate(vehicleRequest.getLicensePlate());
        if (vehicle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehicle);
    }

    /**
     * Crea un nuevo vehículo.
     *
     * @param vehicle Objeto del vehículo a crear.
     * @return Mensaje de éxito.
     */
    @PostMapping("/create")
    @Operation(
            summary = "Crear un nuevo vehículo",
            description = "Registra un nuevo vehículo en el sistema.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del vehículo a registrar",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Vehicle.class))
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Vehículo creado con éxito",
                            content = @Content(mediaType = "text/plain")
                    )
            }
    )
    public ResponseEntity<String> createVehicle(@RequestBody Vehicle vehicle) {
        Vehicle savedVehicle = vehicleService.save(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body("El vehículo ha sido creado con éxito.");
    }

    /**
     * Actualiza la información de un vehículo existente.
     *
     * @param vehicle Objeto con los datos del vehículo a actualizar.
     * @return Mensaje de éxito o error si el vehículo no existe.
     */
    @PutMapping("/update")
    @Operation(
            summary = "Actualizar un vehículo existente",
            description = "Actualiza la información de un vehículo existente en el sistema.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos actualizados del vehículo",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Vehicle.class))
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Vehículo actualizado con éxito",
                            content = @Content(mediaType = "text/plain")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Vehículo no encontrado"
                    )
            }
    )
    public ResponseEntity<String> updateVehicle(@RequestBody Vehicle vehicle) {
        Vehicle currentVehicle = vehicleService.findByLicensePlate(vehicle.getLicensePlate());
        if (currentVehicle == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El vehículo no existe.");
        }
        currentVehicle.setMileage(vehicle.getMileage());
        currentVehicle.setFuelConsumption(vehicle.getFuelConsumption());
        Vehicle updatedVehicle = vehicleService.save(currentVehicle);
        return ResponseEntity.ok("El vehículo ha sido actualizado con éxito.");
    }
}
