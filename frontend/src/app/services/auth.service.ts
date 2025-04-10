import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { User } from '../entities/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginUrl = 'http://localhost:8080/api/auth/login'; // Ajusta la URL según tu backend
  private registerUrl = 'http://localhost:8080/api/auth/register'; // URL de registro


  constructor(private http: HttpClient) {}

  login(user: User): Observable<any> {
    // Puedes ajustar el tipo de dato del Observable según la respuesta esperada (por ejemplo, un token JWT)
    return this.http.post(this.loginUrl, user, { responseType: 'text' });
  }

  register(user: User): Observable<any> {
    return this.http.post(this.registerUrl, user, { responseType: 'text' });
  }
}
