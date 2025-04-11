import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EventDTO } from '../../../dtos/scoreboard';



@Injectable({
  providedIn: 'root'
})
export class MatchDetailService {
  private apiUrl = 'http://tfgproject.duckdns.org/api/matches/';

  constructor(private http: HttpClient) {}

  getMatchDetail(matchId: string): Observable<EventDTO> {
    const url = `${this.apiUrl}${matchId}`;
    console.log(url);
    return this.http.get<EventDTO>(url);
  }
}
