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
  private registerUrl = `${environment.apiUrl}/auth/login`; // URL de registro


  constructor(private http: HttpClient) {}

  login(user: User): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(this.loginUrl, user);
  }

  register(user: User): Observable<any> {
    return this.http.post(this.registerUrl, user, { responseType: 'text' });
  }
}
