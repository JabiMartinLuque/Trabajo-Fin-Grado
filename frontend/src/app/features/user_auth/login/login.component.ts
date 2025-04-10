import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { User } from '../../../entities/user'; // Asegúrate de que la ruta sea correcta
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  // Inicializamos el usuario con username y password vacíos
  user: User = new User('', '', ''); 
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  login() {
    this.authService.login(this.user).subscribe({
      next: (token: string) => {
        console.log('Login exitoso, token:', token);
        // Guardamos el token en localStorage
        localStorage.setItem('token', token);
        // Redirigimos a una ruta protegida (por ejemplo, home)
        this.router.navigate(['/home']);
      },
      error: (error) => {
        console.error('Error en el login:', error);
        this.errorMessage = 'Credenciales incorrectas o error en el login.';
      }
    });
  }
}