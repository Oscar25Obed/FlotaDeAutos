import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './Auth/login/login.component';
import { DriversComponent } from './Pages/drivers/drivers.component';
import { HomeComponent } from './Pages/home/home.component';
import { VehicleComponent } from './Pages/vehicles/vehicles.component';
import { ReportsComponent } from './Pages/reports/reports.component';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' }, // Redirección a la página principal
  { 
    path: 'home', 
    component: HomeComponent
 },
  { 
    path: 'login', 
    component: LoginComponent 
  },
  { 
    path: 'drivers',
     component: DriversComponent 
  },
  { 
    path: 'reports',
     component: ReportsComponent 
  },
  { 
    path: 'vehicles',
     component: VehicleComponent 
  },
  { 
    path: '**', 
    redirectTo: '/home' 
  } // Redirección para rutas no encontradas
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
