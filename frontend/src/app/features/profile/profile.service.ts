import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { FavoritePlayer, FavoriteTeam } from '../../entities/user';

export interface UserWithFavorites { 
  id: number;
  username: string;
  email: string;
  password: string;
  role?: string;
  favoritePlayers: FavoritePlayer[];
  favoriteTeams: FavoriteTeam[];
}

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  private apiUrl = `${environment.apiUrl}/users/profile`; // Ajusta la URL según tu backend

  constructor(private http: HttpClient) { }

  getUserProfile(userId: number): Observable<UserWithFavorites> {
    // Agrega el query param
    console.log(this.http.get<UserWithFavorites>(this.apiUrl, {
      params: { userId: userId.toString() }
    }));
    return this.http.get<UserWithFavorites>(this.apiUrl, {
      params: { userId: userId.toString() }
    });
  }
}
