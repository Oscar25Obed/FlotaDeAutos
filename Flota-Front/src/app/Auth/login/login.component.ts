import { Component } from '@angular/core';
import { AuthService } from '../../core/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  constructor(private authService: AuthService) {}

  // Llamar al servicio para iniciar sesión
  login() {
    this.authService.login(this.username, this.password).subscribe(
      response => {
        console.log('Inicio de sesión exitoso');
        // Redirige a la página protegida si es necesario
      },
      error => {
        console.error('Error en el inicio de sesión:', error);
      }
    );
  }
}
