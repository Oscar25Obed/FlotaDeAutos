import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ReportComponent } from './pages/report/report.component';
import { DriversInfoComponent } from './pages/drivers-info/drivers-info.component';
import { VehiclesInfoComponent } from './pages/vehicles-info/vehicles-info.component';
import { AuthGuard } from './sercices/auth/auth.guard.service';

const routes: Routes = [
  {path:'',redirectTo:'/iniciar-sesion', pathMatch:'full'},
  {
    path: 'inicio',
    component: DashboardComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'drivers-info',
    component: DriversInfoComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'report',
    component: ReportComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'vehicles-info',
    component: VehiclesInfoComponent,
    canActivate: [AuthGuard]
  },
  {path:'iniciar-sesion',component:LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
