import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { NewsDTO } from '../../../dtos/news.dto';

@Injectable({
    providedIn: 'root'
  })
    export class NewsService {
        // Base URL del endpoint en el backend (ajústala según tu configuración)
        private apiUrl = `${environment.apiUrl}/news`; // URL base para el servicio de standings
    
        constructor(private http: HttpClient) {}
    
        /**
         * Obtiene las noticias para la liga indicada.
         * Ejemplo de URL final: http://localhost:8080/api/news/league/esp.1
         */
        getNews(league: string): Observable<NewsDTO> {
        const url = `${this.apiUrl}/league/${league}`;
        return this.http.get<NewsDTO>(url);        
        }
    }