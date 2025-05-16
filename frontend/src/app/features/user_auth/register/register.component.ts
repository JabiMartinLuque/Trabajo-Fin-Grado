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

    if(this.usuario.password !== this.confirmPassword) {
      this.errorMessage = 'Las contraseñas no coinciden';
      return;
    }
    
    this.authService.register(this.usuario).subscribe({
      next: (response) => {
        console.log('Registro exitoso:', response);
        this.successMessage = 'Registro exitoso! Por favor, inicia sesión.';
        // Opcional: redirigir al login después de unos segundos o inmediatamente
        this.router.navigate(['/login']);
      },
      error: (error) => {
        console.error('Error en el registro:', error);
        this.errorMessage = 'Ocurrió un error durante el registro. Por favor, intenta nuevamente.';
      }
    });
  }

}
