// src/app/services/favorites.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
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
    return this.http.post<void>(
      `${this.baseUrl}/leagues/add`,
      { userId: this.userId, leagueId }
    );
  }
  removeFavoriteLeague(leagueId: string): Observable<void> {
    return this.http.post<void>(
      `${this.baseUrl}/leagues/remove`,
      { userId: this.userId, leagueId }
    );
  }

  // ——— EQUIPOS ———
  getFavoriteTeams(): Observable<string[]> {
    return this.http.get<string[]>(
      `${this.baseUrl}/teams?userId=${this.userId}`
    );
  }
  addFavoriteTeam(teamId: string): Observable<void> {
    return this.http.post<void>(
      `${this.baseUrl}/teams/add`,
      { userId: this.userId, teamId }
    );
  }
  removeFavoriteTeam(teamId: string): Observable<void> {
    return this.http.post<void>(
      `${this.baseUrl}/teams/remove`,
      { userId: this.userId, teamId }
    );
  }

  // ——— JUGADORES ———
  getFavoritePlayers(): Observable<string[]> {
    return this.http.get<string[]>(
      `${this.baseUrl}/players?userId=${this.userId}`
    );
  }
  addFavoritePlayer(playerId: string): Observable<void> {
    return this.http.post<void>(
      `${this.baseUrl}/players/add`,
      { userId: this.userId, playerId }
    );
  }
  removeFavoritePlayer(playerId: string): Observable<void> {
    return this.http.post<void>(
      `${this.baseUrl}/players/remove`,
      { userId: this.userId, playerId }
    );
  }
}
