import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, map } from 'rxjs/operators';
import { StandingDTO } from '../../../dtos/standing.dto';
import { url } from 'inspector';
import { environment } from '../../../../environments/environment';


@Injectable({
    providedIn: 'root'
    })
export class StandingService {
    
        private apiUrl = `${environment.apiUrl}/standings`; // URL base para el servicio de standings
        private standingsCache: any[] | null = null;
    
        constructor(private http: HttpClient) {}

        getStandingByLeague(league: string): Observable<any[]> {
            const url = `${this.apiUrl}?league=${league}`;
            console.log('Fetching standings for league:', league, 'URL:', url);
            // Se espera que el backend retorne directamente un arreglo de StandingDTO
            return this.http.get<StandingDTO[]>(url);
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