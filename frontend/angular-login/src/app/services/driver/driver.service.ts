import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, retry, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DriverService {

  private apiUrl = environment.urlApi + 'us/driver';

  constructor(private http: HttpClient) { }

  searchDriversBy(id : number): Observable<any> {
    const body = id;
    return this.http.post(`${this.apiUrl}/search`, body).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  getAllDrivers(): Observable<any> {
    return this.http.get(this.apiUrl).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  createDriver(driver: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/create`, driver).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  updateDriver(driver: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/update`, driver).pipe(
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