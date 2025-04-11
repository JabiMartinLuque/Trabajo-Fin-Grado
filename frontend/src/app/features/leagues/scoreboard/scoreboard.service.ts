import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ScoreboardDTO } from '../../../dtos/scoreboard';
import { environment } from '../../../../environments/environment';


@Injectable({
    providedIn: 'root'
  })
  export class ScoreboardService {
    // Base URL del endpoint en el backend (ajústala según tu configuración)
    private apiUrl = `${environment.apiUrl}/matches/league`; // URL base para el servicio de standings
  
    constructor(private http: HttpClient) {}
  
    /**
     * Obtiene el scoreboard para la liga indicada.
     * Ejemplo de URL final: http://localhost:8080/api/matches/league/esp.1
     */
    getScoreboard(league: string): Observable<ScoreboardDTO> {
      const url = `${this.apiUrl}/${league}`;
      console.log(url);
      return this.http.get<ScoreboardDTO>(url);
      
    }
  }