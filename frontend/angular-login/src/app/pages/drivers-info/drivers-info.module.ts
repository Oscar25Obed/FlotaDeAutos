import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DriversInfoComponent } from './drivers-info.component';
import { SidebarComponent } from 'src/app/sidebar/sidebar.component';

@NgModule({
  declarations: [DriversInfoComponent, SidebarComponent], // Agrega SidebarComponent a la lista de declaraciones
  imports: [
    CommonModule,
    FormsModule
  ]
})
export class DriversInfoModule { }