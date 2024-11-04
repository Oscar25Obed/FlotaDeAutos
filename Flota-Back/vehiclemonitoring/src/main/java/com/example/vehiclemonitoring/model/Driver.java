package com.example.vehiclemonitoring.model;

import com.example.vehiclemonitoring.repository.TipoLicencia;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Driver")
public class Driver {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;
  private String apellido;
  private String licencia;
  private Date fechaVencimientoLicencia;
  private Character tipoLicencia;

  // Getters y setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getLicencia() {
    return licencia;
  }

  public void setLicencia(String licencia) {
    this.licencia = licencia;
  }

  public Date getFechaVencimientoLicencia() {
    return fechaVencimientoLicencia;
  }

  public void setFechaVencimientoLicencia(Date fechaVencimientoLicencia) {
    this.fechaVencimientoLicencia = fechaVencimientoLicencia;
  }

  public Character getTipoLicencia() {
    return tipoLicencia;
  }

  public void setTipoLicencia(Character tipoLicencia) {
    this.tipoLicencia = tipoLicencia;
  }
}

