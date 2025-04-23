import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';

import { TeamEventDTO } from '../../../dtos/team-event.dto';

@Injectable({
  providedIn: 'root'
})
export class TeamDetailsMatchesService {
    private readonly baseUrl = `${environment.apiUrl}/matches`;
    
    constructor(private http: HttpClient) {}
    
    getMatchesByTeam(teamId: string): Observable<TeamEventDTO[]> {
        return this.http.get<TeamEventDTO[]>(`${this.baseUrl}/team/${teamId}`);
    }
    
}