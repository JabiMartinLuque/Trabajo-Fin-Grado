import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

import { User } from '../entities/user';

export interface LoginResponse {
  token: string;
  userId: number;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginUrl = `${environment.apiUrl}/auth/login`; // Ajusta la URL según tu backend
  private registerUrl = `${environment.apiUrl}/auth/register`; // URL de registro
  private forgotPasswordUrl = `${environment.apiUrl}/auth/forgot-password`; // URL de recuperación de contraseña


  constructor(private http: HttpClient) {}

  login(user: User): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(this.loginUrl, user);
  }

  register(user: User): Observable<any> {
    return this.http.post(this.registerUrl, user, { responseType: 'text' });
  }

  forgotPassword(email: string): Observable<any> {
    return this.http.post(this.forgotPasswordUrl, { email }, { responseType: 'text' });
  }

  resetPassword(token: string, newPassword: string): Observable<any> {
  return this.http.post(`${environment.apiUrl}/auth/reset-password`, { token, newPassword }, { responseType: 'text' });
}
}
