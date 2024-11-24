import { Component, OnInit } from '@angular/core';
import { VehicleService } from '../../core/vehicle.service';

@Component({
  selector: 'app-vehicle',
  templateUrl: './vehicles.component.html',
  styleUrls: ['./vehicles.component.css']
})
export class VehicleComponent implements OnInit {
  vehicles: any[] = [];
  vehicleData = {
    marca: '',
    modelo: '',
    anio: '',
    vin: '',
    placa: '',
    estado: ''
  };

  constructor(private vehicleService: VehicleService) { }

  ngOnInit(): void {
    this.getVehicles();
  }

  // Obtener la lista de vehículos
  getVehicles(): void {
    this.vehicleService.getVehicles().subscribe(
      data => this.vehicles = data,
      error => console.error('Error al obtener vehículos:', error)
    );
  }

  // Agregar un nuevo vehículo
  addVehicle(): void {
    this.vehicleService.addVehicle(this.vehicleData).subscribe(
      data => {
        this.getVehicles(); // Actualizar la lista después de agregar
        this.resetForm();   // Limpiar el formulario
      },
      error => console.error('Error al agregar vehículo:', error)
    );
  }

  // Resetear el formulario después de agregar
  resetForm(): void {
    this.vehicleData = {
      marca: '',
      modelo: '',
      anio: '',
      vin: '',
      placa: '',
      estado: ''
    };
  }
}