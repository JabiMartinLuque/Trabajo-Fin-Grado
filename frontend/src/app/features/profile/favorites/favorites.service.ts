// src/app/services/favorites.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class FavoritesService {
  private readonly baseUrl = `${environment.apiUrl}/favorites`;
  private get userId(): number {
    // Ajusta esto al modo en que guardas el userId / token
    return Number(localStorage.getItem('userId'));
  }

  constructor(private http: HttpClient) {}

  // ——— LIGAS ———
  getFavoriteLeagues(): Observable<string[]> {
    return this.http.get<string[]>(
      `${this.baseUrl}/leagues?userId=${this.userId}`
    );
  }
  addFavoriteLeague(leagueId: string): Observable<void> {
    return this.http.post(
      `${this.baseUrl}/leagues/add`,
      { userId: this.userId, leagueId },
      { responseType: 'text' }   // ← texto, no JSON
    ).pipe(map(() => void 0));   //   castea a void
  }
  
  removeFavoriteLeague(leagueId: string): Observable<void> {
    return this.http.delete(
      `${this.baseUrl}/leagues/remove`,
      {
        body: { userId: this.userId, leagueId },
        responseType: 'text'          // ← texto, no JSON
      }
    ).pipe(map(() => void 0));        // ← castea a void
  }
  

  // ——— EQUIPOS ———
  getFavoriteTeams(): Observable<string[]> {
    return this.http.get<string[]>(
      `${this.baseUrl}/teams?userId=${this.userId}`
    );
  }
  addFavoriteTeam(teamId: string): Observable<void> {
    return this.http.post(
      `${this.baseUrl}/teams/add`,
      { userId: this.userId, teamId },
      { responseType: 'text' }   // ← texto, no JSON
    ).pipe(map(() => void 0));   //   castea a void
  }
  removeFavoriteTeam(teamId: string): Observable<void> {
    return this.http.delete(
      `${this.baseUrl}/teams/remove`,
      {
        body: { userId: this.userId, teamId },
        responseType: 'text'          // ← texto, no JSON
      }
    ).pipe(map(() => void 0));        // ← castea a void
  }

  // ——— JUGADORES ———
  getFavoritePlayers(): Observable<string[]> {
    return this.http.get<string[]>(
      `${this.baseUrl}/players?userId=${this.userId}`
    );
  }
  addFavoritePlayer(playerId: string): Observable<void> {
    return this.http.post(
      `${this.baseUrl}/players/add`,
      { userId: this.userId, playerId },
      { responseType: 'text' }   // ← texto, no JSON
    ).pipe(map(() => void 0));   //   castea a void
  }
  removeFavoritePlayer(playerId: string): Observable<void> {
    return this.http.delete(
      `${this.baseUrl}/players/remove`,
      {
        body: { userId: this.userId, playerId },
        responseType: 'text'          // ← texto, no JSON
      }
    ).pipe(map(() => void 0));        // ← castea a void
  }
}
