import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:9090'; // URL base de tu backend
  private tokenKey = 'authToken'; // Clave para almacenar el token

  constructor(private http: HttpClient) {}

  // Método para iniciar sesión, enviando credenciales a /login
  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, { username, password })
      .pipe(
        tap(response => {
          // Guarda el token JWT en localStorage si es exitoso
          localStorage.setItem(this.tokenKey, response.token);
        })
      );
  }

  // Obtener el token guardado en el localStorage
  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  // Cerrar sesión (remover el token)
  logout(): void {
    localStorage.removeItem(this.tokenKey);
  }
}
