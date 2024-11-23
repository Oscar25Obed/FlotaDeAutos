import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatIconModule } from '@angular/material/icon';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { VehicleComponent } from './Pages/vehicles/vehicles.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './Auth/login/login.component';

@NgModule({
  declarations: [
    VehicleComponent,
    LoginComponent
  ],
  imports: [
    MatIconModule,
    BrowserModule,
    FormsModule,
    HttpClientModule
  ]
})
export class AppModule { }
