// drivers-info.module.ts
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DriversInfoComponent } from './drivers-info.component';

@NgModule({
  declarations: [DriversInfoComponent],
  imports: [
    CommonModule,
    FormsModule
  ]
})
export class DriversInfoModule { }
