import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router) { }

  canActivate(): boolean {
    // Verifica si estamos en el navegador
    if (typeof window !== 'undefined' && window.localStorage) {
      const token = localStorage.getItem('token');
      if (token) {
        return true;
      }
    }
    // Si no se encontró token o no estamos en el navegador, redirige al login
    this.router.navigate(['/login']);
    return false;
  }
}
