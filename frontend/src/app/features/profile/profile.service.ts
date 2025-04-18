import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { User } from '../../entities/user';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  private apiUrl = `${environment.apiUrl}/users/profile`; // Ajusta la URL según tu backend

  constructor(private http: HttpClient) { }

  getUserProfile(userId: number): Observable<User> {
    // Agrega el query param
    console.log(this.http.get<User>(this.apiUrl, {
      params: { userId: userId.toString() }
    }));
    return this.http.get<User>(this.apiUrl, {
      params: { userId: userId.toString() }
    });
  }
}
