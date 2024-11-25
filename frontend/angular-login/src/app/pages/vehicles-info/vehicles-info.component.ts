import { Component, OnInit } from '@angular/core';
import { Vehicle } from 'src/app/interface/vehicle';

import { VehiclesService } from 'src/app/services/vehicles/vehicles.service';

@Component({
  selector: 'app-vehicles-info',
  templateUrl: './vehicles-info.component.html',
  styleUrls: ['./vehicles-info.component.css']
})
export class VehiclesInfoComponent implements OnInit {
  vehicles!: Vehicle[];

  constructor(private vehiclesService: VehiclesService) { }

  ngOnInit(): void {
    this.vehiclesService.getAllVehicles().subscribe((response: Vehicle[]) => {
      this.vehicles = response;
    });
  }

  onSubmit() {
    const placa = (document.getElementById('placa') as HTMLInputElement).value;
    const modelo = (document.getElementById('modelo') as HTMLInputElement).value;
    const anio = parseInt((document.getElementById('anio') as HTMLInputElement).value);
    const estado = (document.getElementById('estado') as HTMLInputElement).value;
    const kilometraje = parseInt(
      (document.getElementById('kilometraje') as HTMLInputElement).value
    )
    const consumoCombustible = parseInt(
      (document.getElementById('consumoCombustible') as HTMLInputElement).value
    );

    const vehicle = {
      licensePlate: placa,
      model: modelo,
      year: anio,
      status: estado,
      mileage: kilometraje,
      fuelConsumption: consumoCombustible
    };

    this.vehiclesService.createVehicle(vehicle).subscribe((response: any) => {
      console.log(response);
    });
    this.vehiclesService.getAllVehicles().subscribe((response: Vehicle[]) => {
      this.vehicles = response;
    });
  }
}