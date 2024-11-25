import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, retry, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Rental } from 'src/app/interface/rental';


@Injectable({
  providedIn: 'root'
})
export class RentalService {

  private apiUrl = environment.urlApi + 'us/rental';

  constructor(private http: HttpClient) { }

  searchrentalByPlate(id: number): Observable<Rental> {
    const body = id;
    return this.http.post<Rental>(`${this.apiUrl}/search`, body).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  getAllRentals(): Observable<Rental[]> {
    return this.http.get<Rental[]>(this.apiUrl).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  createRental(rental: Rental): Observable<Rental> {
    return this.http.post<Rental>(`${this.apiUrl}/create`, rental).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  updateRental(rental: Rental): Observable<Rental> {
    return this.http.put<Rental>(`${this.apiUrl}/update`, rental).pipe(
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
