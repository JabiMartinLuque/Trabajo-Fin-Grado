import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { LeagueDTO } from '../../dtos/league.dto';

@Injectable({
    providedIn: 'root'
  })

export class LeagueService {
    private readonly baseUrl = `${environment.apiUrl}/leagues`;

    constructor(private http: HttpClient) {}

    getLeagueById(league: String): Observable<LeagueDTO[]> {
        return this.http.get<LeagueDTO[]>(this.baseUrl + '/' + league);
    }

    getLeagues(): Observable<LeagueDTO[]> {
        return this.http.get<LeagueDTO[]>(this.baseUrl);
    }
}