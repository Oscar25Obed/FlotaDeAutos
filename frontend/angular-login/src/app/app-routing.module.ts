import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ReportComponent } from './pages/report/report.component';
import { DriversInfoComponent } from './pages/drivers-info/drivers-info.component';
import { VehiclesInfoComponent } from './pages/vehicles-info/vehicles-info.component';

const routes: Routes = [
  {path:'',redirectTo:'/inicio', pathMatch:'full'},
  {path:'inicio',component:DashboardComponent},
  {path:'iniciar-sesion',component:LoginComponent},
  {path:'**',redirectTo:'/inicio', pathMatch:'full'},
  {path:'drivers-ingo',component:DriversInfoComponent},
  {path:'report',component:ReportComponent},
  {path:'vehicles-ingo',component:VehiclesInfoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
