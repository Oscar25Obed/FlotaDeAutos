package com.irojas.demojwt.Vehicle;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="vehicle" , uniqueConstraints = @UniqueConstraint(columnNames = "license_plate"))
@Schema(description = "Representa la gestión de un vehículo")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del vehículo", type = "integer", example = "1")
    private Integer id;

    @Schema(description = "Número de placa del vehículo", type = "string", example = "XYZ123")
    private String licensePlate;

    @Schema(description = "Modelo del vehículo", type = "string", example = "Toyota Corolla")
    private String model;

    @Schema(description = "Año de fabricación del vehículo", type = "integer", example = "2022")
    private int year;

    @Schema(description = "Estado del vehículo (activo, en mantenimiento, etc.)", type = "string", example = "activo")
    private String status;

    @Schema(description = "Kilometraje actual del vehículo en kilómetros", type = "integer", example = "25000")
    private int mileage;

    @Schema(description = "Consumo de combustible en litros por cada 100 kilómetros", type = "number", example = "8.5")
    private double fuelConsumption;
}
