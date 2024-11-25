package com.irojas.demojwt.Driver;

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
@Table(name="driver")
@Schema(description = "Representa la gestión de conductores")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del conductor", type = "integer", example = "1")
    private Integer id;

    @Schema(description = "Nombre del conductor", type = "string", example = "Juan")
    private String nombre;

    @Schema(description = "Apellido del conductor", type = "string", example = "Pérez")
    private String apellido;

    @Schema(description = "Número de licencia del conductor", type = "string", example = "ABC12345")
    private String licencia;

    @Schema(description = "Fecha de vencimiento de la licencia del conductor", type = "string", format = "date", example = "2025-12-31")
    private Date fechaVencimientoLicencia;

    @Schema(description = "Tipo de licencia del conductor, por ejemplo, A, B, C", type = "string", allowableValues = "A, B, C", example = "B")
    private Character tipoLicencia;

}
