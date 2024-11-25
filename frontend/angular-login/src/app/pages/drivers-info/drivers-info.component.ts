import { Component, OnInit } from '@angular/core';
import { Driver } from 'src/app/interface/driver';

import { DriverService } from 'src/app/services/driver/driver.service';
@Component({
  selector: 'app-drivers-info',
  templateUrl: './drivers-info.component.html',
  styleUrls: ['./drivers-info.component.css']
})
export class DriversInfoComponent implements OnInit {
  drivers!: Driver[];

  constructor(private driverService: DriverService) { }

  ngOnInit(): void {
    this.driverService.getAllDrivers().subscribe((response: Driver[]) => {
      this.drivers = response;
    });
  }
  searchDriverById(id: number): void {
    this.driverService.searchDriversBy(id).subscribe((response: any) => {
      console.log('Search Result:', response);
    });
  }

  createDriver(driver: Driver): void {
    this.driverService.createDriver(driver).subscribe((response: any) => {
      console.log('Driver Created:', response);
    });
  }

  updateDriver(driver: Driver): void {
    this.driverService.updateDriver(driver).subscribe((response: any) => {
      console.log('Driver Updated:', response);
    });
  }
}