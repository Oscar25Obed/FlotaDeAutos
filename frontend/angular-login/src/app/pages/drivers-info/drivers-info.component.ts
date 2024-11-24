import { Component } from '@angular/core';
interface Driver {
  fullName: string;
  licenseNumber: string;
  expirationDate: string;
  experience: number;
  infractions: string;
}
@Component({
  selector: 'app-drivers-info',
  templateUrl: './drivers-info.component.html',
  styleUrls: ['./drivers-info.component.css']
})

export class DriversInfoComponent {
  drivers: Driver[] = [
    { fullName: 'Juan Pérez', licenseNumber: 'A1234567', expirationDate: '12/2025', experience: 5, infractions: '' },
    { fullName: 'Carlos Gómez', licenseNumber: 'B2345678', expirationDate: '09/2024', experience: 4, infractions: '' },
    { fullName: 'María Rodríguez', licenseNumber: 'C3456789', expirationDate: '11/2026', experience: 7, infractions: '' },
    { fullName: 'Ana Torres', licenseNumber: 'D4567890', expirationDate: '05/2023', experience: 4, infractions: '' },
    { fullName: 'Luis Fernández', licenseNumber: 'E5678901', expirationDate: '09/2025', experience: 6, infractions: '' }
  ];

  driver: Driver = {
    fullName: '',
    licenseNumber: '',
    expirationDate: '',
    experience: 0,
    infractions: ''
  };

  onSubmit() {
    if (this.driver.fullName && this.driver.licenseNumber) {
      this.drivers.push({ ...this.driver });
      this.resetForm();
    }
  }

  resetForm() {
    this.driver = { fullName: '', licenseNumber: '', expirationDate: '', experience: 0, infractions: '' };
  }

  editDriver(driver: Driver) {
    // Aquí puedes agregar lógica para editar un conductor
    alert('Función de edición no implementada');
  }

  onCancel() {
    this.resetForm();
  }
}
