import { Injectable } from "@angular/core";
import { environment } from "../../../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";


@Injectable({
    providedIn: 'root'
  })

export class TeamDetailsTopPerformersService {
    private readonly baseUrl = `${environment.apiUrl}/topPerformers`;
    
    constructor(private http: HttpClient) {}
    
    getTopPerformersByTeam(teamId: string, season?: string, league?:string): Observable<any> {
        if (!season) {
            season = '2024';
        }
        if (!league) {
            league = 'esp.1'; 
        }
        return this.http.get<any>(`${this.baseUrl}/team/${teamId}`, { params: { season, league } });
    }
}