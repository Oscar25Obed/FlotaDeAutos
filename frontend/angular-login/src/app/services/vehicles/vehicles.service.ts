import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, retry, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Vehicle } from 'src/app/interface/vehicle';


@Injectable({
  providedIn: 'root'
})
export class VehiclesService {

  private apiUrl = environment.urlApi + 'us/vehicle';

  constructor(private http: HttpClient) { }

  searchVehicleByPlate(licensePlate: string): Observable<Vehicle> {
    const body = { licensePlate };
    return this.http.post<Vehicle>(`${this.apiUrl}/search`, body).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  getAllVehicles(): Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>(this.apiUrl).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  createVehicle(vehicle: Vehicle): Observable<Vehicle> {
    return this.http.post<Vehicle>(`${this.apiUrl}/create`, vehicle).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  updateVehicle(vehicle: Vehicle): Observable<Vehicle> {
    return this.http.put<Vehicle>(`${this.apiUrl}/update`, vehicle).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      console.error('Se ha producido un error', error.error);
    } else {
      console.error('Backend retornó el código de estado', error.status, error.error);
    }
    return throwError(() => new Error('Algo falló. Por favor intente nuevamente.'));
  }
}
