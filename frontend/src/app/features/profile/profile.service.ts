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
  private apiUrlUser = `${environment.apiUrl}/users`; // Ajusta la URL según tu backend

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

  uploadProfileImage(userId: number, file: File): Observable<string> {
    const formData = new FormData();
    formData.append('file', file);
    return this.http.post(`${this.apiUrlUser}/${userId}/profile-image`, formData, { responseType: 'text' });
  }

  deleteProfileImage(userId: number): Observable<any> {
    return this.http.delete(`${environment.apiUrl}/users/${userId}/profile-image`, { responseType: 'text' });
  }

  updateProfile(user: User): Observable<User> {
    return this.http.put<User>(`${environment.apiUrl}/users/${user.id}`, user);
  }
}
