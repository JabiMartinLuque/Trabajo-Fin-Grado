import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment'; // Asegúrate de que la ruta sea correcta
import { StatisticsDTO } from '../../../dtos/statistics.dto';

@Injectable({
    providedIn: 'root'
  })

export class TeamDetailsStatsService {
    // Base URL del endpoint en el backend (ajústala según tu configuración)
    private apiUrl = `${environment.apiUrl}/statistics`; // URL base para el servicio de standings
  
    constructor(private http: HttpClient) {}
  
    getTeamStatisticsByid(id: string, season: string, league:string): Observable<StatisticsDTO> {
        const url = `${this.apiUrl}/team/${id}`;

        return this.http.get<StatisticsDTO>(url, { params: { season, league } });
        
    }
}