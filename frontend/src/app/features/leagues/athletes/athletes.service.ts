import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';

@Injectable({
    providedIn: 'root'
  })
  export class AthletesService {
    // Base URL del endpoint en el backend (ajústala según tu configuración)
    private apiUrl = `${environment.apiUrl}/athletes`; // URL base para el servicio de standings
  
    constructor(private http: HttpClient) {}

    getAthleteByid(id: string) {
        const url = `${this.apiUrl}/${id}`;
        console.log(url);
        return this.http.get(url);
        
    }
}
