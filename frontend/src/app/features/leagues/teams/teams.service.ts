import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Observable } from 'rxjs';
import { TeamDTO } from '../../../dtos/team.dto'; 


@Injectable({
    providedIn: 'root'
  })
  export class TeamsService {
    // Base URL del endpoint en el backend (ajústala según tu configuración)
    private apiUrl = `${environment.apiUrl}/teams`; // URL base para el servicio de standings
  
    constructor(private http: HttpClient) {}
  
    getTeamByid(id: string): Observable<TeamDTO> {
        const url = `${this.apiUrl}/${id}`;
        console.log(url);
        console.log(this.http.get<TeamDTO>(url));
        return this.http.get<TeamDTO>(url);
        
    }

    getTeamsByLeague(leagueId: string): Observable<TeamDTO[]> {
        const url = `${this.apiUrl}/league/${leagueId}`;
        return this.http.get<TeamDTO[]>(url);
    }
}