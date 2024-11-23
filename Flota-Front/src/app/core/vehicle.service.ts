import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {
  private apiUrl = 'http://localhost:9090/api/user/vehicles';

  constructor(private http: HttpClient) { }

  // Método para obtener la lista de vehículos
  getVehicles(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }

  // Método para agregar un nuevo vehículo
  addVehicle(vehicleData: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, vehicleData);
  }
}

