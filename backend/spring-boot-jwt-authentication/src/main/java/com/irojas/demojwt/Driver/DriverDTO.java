package com.irojas.demojwt.Driver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {
    Long id;
    String nombre;
    String apellido;
    String licencia;
    Date fechaVencimientoLicencia;
    Character tipoLicencia;
}
