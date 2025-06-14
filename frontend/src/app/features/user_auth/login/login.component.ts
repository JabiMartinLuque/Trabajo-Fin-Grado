import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { User } from '../../../entities/user'; // Asegúrate de que la ruta sea correcta
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { jwtDecode } from 'jwt-decode';

interface DecodedToken {
  sub: string; // O la propiedad que uses para almacenar el ID o username
  iat: number;
  exp: number;
}

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule, RouterModule],
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
      next: (res: any) => {
        console.log('Respuesta del backend:', res);
        localStorage.setItem('token', res.token);
        localStorage.setItem('userId', res.userId.toString());
        this.router.navigate(['/home']);
      },
      error: (error) => {
        console.error('Error en el login:', error);
        this.errorMessage = 'Login error.';
      }
    });
  }
}