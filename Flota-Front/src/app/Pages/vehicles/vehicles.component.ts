import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

interface Vehicle {
  brand: string;
  model: string;
  year: string;
  vin: string;
  plate: string;
  status: string;
}

@Component({
  selector: 'app-vehicle',
  templateUrl: './vehicles.component.html',
  styleUrls: ['./vehicles.component.css']
})
export class VehicleComponent implements OnInit {
  vehicleForm: FormGroup;
  vehicles: Vehicle[] = [];

  constructor(private fb: FormBuilder) {
    this.vehicleForm = this.fb.group({
      brand: ['', Validators.required],
      model: ['', Validators.required],
      year: ['', Validators.required],
      vin: ['', Validators.required],
      plate: ['', Validators.required],
      status: ['', Validators.required]
    });
  }

  ngOnInit(): void {}

  onSubmit() {
    if (this.vehicleForm.valid) {
      this.vehicles.push(this.vehicleForm.value);
      this.vehicleForm.reset();
    }
  }

  onCancel() {
    this.vehicleForm.reset();
  }

  editVehicle(index: number) {
    const vehicle = this.vehicles[index];
    this.vehicleForm.patchValue(vehicle);
  }
}
