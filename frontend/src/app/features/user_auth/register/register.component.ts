import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';

import { User } from '../../../entities/user';

@Component({
  selector: 'app-register',
  imports: [FormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  usuario: User = new User('', '', '');
  confirmPassword: string = '';
  errorMessage: string = '';
  successMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  register(): void {
    this.errorMessage = '';
    this.successMessage = '';

    if (!this.validateEmail(this.usuario.email)) {
      this.errorMessage = 'Please enter a valid email';
      return;
    }

    if (this.usuario.password !== this.confirmPassword) {
        this.errorMessage = 'Passwords do not match';
        return;
    }

    this.authService.register(this.usuario).subscribe({
      next: (response) => {
        console.log('Registro exitoso:', response);
        this.successMessage = 'Registration successful! Please log in.';
        // Opcional: redirigir al login después de unos segundos o inmediatamente
        this.router.navigate(['/login']);
      },
      error: (error) => {
        console.error('Error en el registro:', error);
        if (error.status === 400) {
          // Extraemos el mensaje del error, puede venir como string o como objeto
          let backendMsg = '';
          if (typeof error.error === 'string') {
            backendMsg = error.error;
          } else if (error.error && error.error.message) {
            backendMsg = error.error.message;
          }
          
          if (backendMsg.includes("ya está registrado")) {
            this.errorMessage = "This email is already registered. Please use another one.";
          } else {
              this.errorMessage = 'An error occurred during registration. Please try again.';
          }
        } else {
          this.errorMessage = 'An error occurred during registration. Please try again.';
        }
      }
    });
  }

  validateEmail(email: string): boolean {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
  }

}
