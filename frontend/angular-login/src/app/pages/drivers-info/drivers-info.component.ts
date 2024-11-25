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

}