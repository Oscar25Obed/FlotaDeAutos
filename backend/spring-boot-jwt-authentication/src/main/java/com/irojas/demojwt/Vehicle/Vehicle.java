package com.irojas.demojwt.Vehicle;

import java.util.Date;

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
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String licensePlate;
    private String model;
    private int year;
    private String status;
    private int mileage; // en kilómetros
    private double fuelConsumption; // en litros por cada 100 km
}
