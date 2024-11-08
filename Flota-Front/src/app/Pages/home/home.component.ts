import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  chartLabels: string[] = ['40%', '30%', '10%', '7%', '13%'];
  fleetStatusData: number[] = [40, 30, 10, 7, 13];
  activeDriversData: number[] = [40, 30, 10, 7, 13];
  criticalAlertsData: number[] = [40, 30, 10, 7, 13];

  registerVehicle() {
    console.log('Registrar Vehículo');
  }

  addDriver() {
    console.log('Añadir Conductor');
  }

  generateReport() {
    console.log('Generar Reporte');
  }
}