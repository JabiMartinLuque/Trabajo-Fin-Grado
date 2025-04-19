import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { ScoreboardDTO } from '../../dtos/scoreboard';
import { TeamEventDTO } from '../../dtos/team-event.dto';

@Injectable({
    providedIn: 'root'
  })
export class HomeService {
    private apiUrl = `${environment.apiUrl}/matches`;

    constructor(private http: HttpClient) { }

    getMatchesByLeague(leagueId: string): Observable<ScoreboardDTO> {
        return this.http.get<any>(`${this.apiUrl}/league` + `/${leagueId}`);
    }

    getMatchesByTeam(teamId: string): Observable<TeamEventDTO> {
        return this.http.get<any>(`${this.apiUrl}/team` + `/${teamId}` + "/event");
    }

    getMatchesByAthlete(athleteId: string): Observable<TeamEventDTO> {
        return this.http.get<any>(`${this.apiUrl}/athlete` + `/${athleteId}`);
    }
}
