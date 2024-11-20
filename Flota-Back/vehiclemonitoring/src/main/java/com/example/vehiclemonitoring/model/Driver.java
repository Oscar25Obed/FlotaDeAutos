package com.example.vehiclemonitoring.model;

import com.example.vehiclemonitoring.repository.TipoLicencia;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Driver {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;
  private String apellido;
  private String licencia;
  private Date fechaVencimientoLicencia;
  private Character tipoLicencia;

}

