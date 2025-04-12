import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';

@Injectable({
    providedIn: 'root'
  })
  export class TeamsService {
    // Base URL del endpoint en el backend (ajústala según tu configuración)
    private apiUrl = `${environment.apiUrl}/teams`; // URL base para el servicio de standings
  
    constructor(private http: HttpClient) {}
  
    getTeamByid(id: string) {
        const url = `${this.apiUrl}/${id}`;
        console.log(url);
        return this.http.get(url);
        
    }
}