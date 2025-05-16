import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-forgot-password',
  imports: [FormsModule, CommonModule],
  templateUrl: './forgot-password.component.html',
  styleUrl: './forgot-password.component.css'
})
export class ForgotPasswordComponent {
  email: string = '';
  errorMessage: string = '';
  successMessage: string = '';

  constructor(private authService: AuthService) {}

  forgotPassword(): void {
    this.errorMessage = '';
    this.successMessage = '';
    this.authService.forgotPassword(this.email).subscribe({
      next: (response) => {
        this.successMessage = 'Se han enviado instrucciones a tu email.';
      },
      error: (error) => {
        console.error('Error en recuperación:', error);
        this.errorMessage = 'Ocurrió un error. Por favor, intenta de nuevo.';
      }
    });
  }
}
