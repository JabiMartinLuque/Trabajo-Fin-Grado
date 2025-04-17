import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Observable } from 'rxjs';
import { AthleteDTO } from '../../../dtos/athlete.dto';

@Injectable({
    providedIn: 'root'
  })
  export class AthletesService {
    // Base URL del endpoint en el backend (ajústala según tu configuración)
    private apiUrl = `${environment.apiUrl}/athletes`; // URL base para el servicio de standings
  
    constructor(private http: HttpClient) {}

    getAthleteByid(id: string): Observable<AthleteDTO> {
        const url = `${this.apiUrl}/${id}`;
        console.log(url);
        return this.http.get<AthleteDTO>(url);
        
    }
}
