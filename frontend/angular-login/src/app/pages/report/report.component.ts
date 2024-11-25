import { Component, OnInit } from '@angular/core';
import { RentalService } from 'src/app/services/rental/rental.service';
import { Rental } from 'src/app/interface/rental';
@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {
  rentals!: Rental[];

  constructor(private RentalService: RentalService) { }

  ngOnInit(): void {
    this.RentalService.getAllRentals().subscribe((response: any[]) => {
      this.rentals = response;
    });
  }
}