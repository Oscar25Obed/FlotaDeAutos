package com.irojas.demojwt.Rental;

import java.util.Date;

import com.irojas.demojwt.Driver.Driver;
import com.irojas.demojwt.Vehicle.Vehicle;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="rental")
@Schema(description = "Representa una renta de vehículo")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la renta", example = "1")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    @Schema(description = "Nombre del vehículo rentado", example = "Toyota Corolla")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    @Schema(description = "Nombre del conductor", example = "Juan Pérez")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Driver driver;

    @Column(nullable = false)
    @Schema(description = "Fecha de inicio de la renta (ISO 8601)", example = "2024-01-05T00:00:00.000+00:00")
    private Date rentalDate;

    @Schema(description = "Fecha de devolución de la renta (ISO 8601)", example = "2024-01-05T00:00:00.000+00:00")
    private Date returnDate;

    @Column(columnDefinition = "INT DEFAULT 0")
    @Schema(description = "Distancia total estimada a recorrer en kilómetros", example = "500")
    private int totalDistance;
}
