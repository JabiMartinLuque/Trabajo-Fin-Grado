import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { AthleteDTO } from '../../../dtos/athlete.dto';

@Injectable({
    providedIn: 'root'
  })

export class TeamDetailsAthletesService {
    private readonly baseUrl = `${environment.apiUrl}/athletes`;

    constructor(private http: HttpClient) {}

    getTeamAthletesByTeam(teamId: String): Observable<AthleteDTO[]> {
        return this.http.get<AthleteDTO[]>(this.baseUrl + '/team/' + teamId);
    }

    getAthleteById(athleteId: String): Observable<AthleteDTO> {
        return this.http.get<AthleteDTO>(this.baseUrl + '/' + athleteId);
    }
}