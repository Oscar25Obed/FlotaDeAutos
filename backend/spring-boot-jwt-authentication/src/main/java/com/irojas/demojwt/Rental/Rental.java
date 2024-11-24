package com.irojas.demojwt.Rental;

import java.util.Date;

import com.irojas.demojwt.Driver.Driver;
import com.irojas.demojwt.Vehicle.Vehicle;
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
@Table(name="rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @Column(nullable = false)
    private Date rentalDate;

    private Date returnDate;

    @Column(columnDefinition = "INT DEFAULT 0")
    private int totalDistance;
}
