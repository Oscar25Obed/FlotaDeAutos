package com.irojas.demojwt.Rental;

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
@RequestMapping(value = "/api/v1/us/rental")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
@Tag(name = "Rental Management", description = "Endpoints para gestionar rentas de vehículos")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping
    @Operation(summary = "Obtener todos los vehículos",
            description = "Devuelve una lista de todas las rentas de vehículos disponibles",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de rentas obtenida con éxito",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Rental.class)))
            })
    public ResponseEntity<List<Rental>> getAllRentals() {
        List<Rental> rentals = rentalService.findAll();
        return ResponseEntity.ok(rentals);
    }

    @PostMapping("/search")
    @Operation(summary = "Buscar renta por ID",
            description = "Busca y devuelve una renta específica según el ID proporcionado",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "ID de la renta que se desea buscar",
                    required = true,
                    content = @Content(schema = @Schema(type = "integer", example = "1"))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Renta encontrada",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Rental.class))),
                    @ApiResponse(responseCode = "404", description = "Renta no encontrada")
            })
    public ResponseEntity<Rental> getRentalById(@RequestBody Integer id) {
        Rental rental = rentalService.findById(id);
        if (rental == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rental);
    }

    @PostMapping("/create")
    @Operation(summary = "Crear una nueva renta",
            description = "Crea una nueva renta basada en los detalles proporcionados",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Detalles de la nueva renta",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Rental.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Renta creada con éxito",
                            content = @Content(mediaType = "text/plain",
                                    schema = @Schema(type = "string", example = "La renta ha sido creada con éxito.")))
            })
    public ResponseEntity<String> createRental(@RequestBody Rental rental) {
        Rental createdRental = rentalService.create(rental);
        return ResponseEntity.status(HttpStatus.CREATED).body("La renta ha sido creada con éxito.");
    }

    @PutMapping("/update")
    @Operation(summary = "Actualizar una renta existente",
            description = "Actualiza una renta ya existente con los nuevos datos proporcionados",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Detalles actualizados de la renta",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Rental.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Renta actualizada con éxito",
                            content = @Content(mediaType = "text/plain",
                                    schema = @Schema(type = "string", example = "La renta ha sido actualizada con éxito."))),
                    @ApiResponse(responseCode = "404", description = "Renta no encontrada")
            })
    public ResponseEntity<String> updateRental(@RequestBody Rental rental) {
        Rental existingRental = rentalService.findById(rental.getId());
        if (existingRental == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha podido encontrar la renta.");
        }
        existingRental.setVehicle(rental.getVehicle());
        existingRental.setDriver(rental.getDriver());
        existingRental.setRentalDate(rental.getRentalDate());
        existingRental.setReturnDate(rental.getReturnDate());
        existingRental.setTotalDistance(rental.getTotalDistance());
        Rental updatedRental = rentalService.update(existingRental);
        return ResponseEntity.status(HttpStatus.OK).body("La renta ha sido actualizada con éxito.");
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Eliminar una renta",
            description = "Elimina una renta específica basada en su ID",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "ID de la renta a eliminar",
                    required = true,
                    content = @Content(schema = @Schema(type = "integer", example = "1"))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Renta eliminada con éxito",
                            content = @Content(mediaType = "text/plain",
                                    schema = @Schema(type = "string", example = "La renta ha sido eliminada con éxito."))),
                    @ApiResponse(responseCode = "404", description = "Renta no encontrada")
            })
    public ResponseEntity<String> deleteRental(@RequestBody Integer id) {
        Rental existingRental = rentalService.findById(id);
        if (existingRental == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha podido encontrar la renta.");
        }
        rentalService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("La renta ha sido eliminada con éxito.");
    }
}