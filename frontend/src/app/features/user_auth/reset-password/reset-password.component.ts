import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-reset-password',
  imports: [CommonModule, FormsModule],
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.css'
})
export class ResetPasswordComponent {
  token: string = '';
    newPassword: string = '';
    confirmPassword: string = '';
    errorMessage: string = '';
    successMessage: string = '';

    constructor(private route: ActivatedRoute, private authService: AuthService, private router: Router) {}

    ngOnInit(): void {
        this.route.queryParams.subscribe(params => {
            this.token = params['token'] || '';
        });
    }

    resetPassword(): void {
        this.errorMessage = '';
        this.successMessage = '';

        if (this.newPassword !== this.confirmPassword) {
            this.errorMessage = 'Passwords do not match';
            return;
        }

        // Llama al servicio para restablecer la contraseña
        this.authService.resetPassword(this.token, this.newPassword).subscribe({
            next: (response) => {
            this.successMessage = 'Password successfully reset. You can now log in.';
                setTimeout(() => this.router.navigate(['/login']), 2000);
            },
            error: (error) => {
                console.error('Error al restablecer contraseña:', error);
                this.errorMessage = 'Error resetting the password. Please try again.';
            }
        });
    }
}
