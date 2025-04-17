import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EventDTO } from '../../../dtos/scoreboard';
import { environment } from '../../../../environments/environment';



@Injectable({
  providedIn: 'root'
})
export class MatchDetailService {
  private apiUrl = `${environment.apiUrl}/matches/`;

  constructor(private http: HttpClient) {}

  getMatchDetail(matchId: string): Observable<EventDTO> {
    const url = `${this.apiUrl}${matchId}`;
    console.log(url);
    return this.http.get<EventDTO>(url);
  }
}
