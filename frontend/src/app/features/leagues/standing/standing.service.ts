import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, map } from 'rxjs/operators';
import { url } from 'inspector';

@Injectable({
    providedIn: 'root'
    })
export class StandingService {
    
        private apiUrl = 'http://localhost:8080/api/standings/'; 
        private standingsCache: any[] | null = null;
    
        constructor(private http: HttpClient) {}

        standingByLeague(league: string): Observable<any[]> {
            if (this.standingsCache) {
                return of(this.standingsCache);
            } else {
                const url = this.apiUrl + '?league= ' + league;
                return this.http.get<any>(url).pipe(
                    map(response => response.standings),
                    tap(standings => this.standingsCache = standings)
                );
            }
        }

        /**
         * Obtiene la clasificación (standing) de la liga especificada.
         * @param league Identificador de la liga (por ejemplo, "esp.1")
         * @returns Observable con un array de StandingDTO
         */
        getStandings(league: string): Observable<StandingDTO[]> {
            const url = `${this.apiUrl}?league=${league}`;
            return this.http.get<StandingDTO[]>(url);
        }
    }