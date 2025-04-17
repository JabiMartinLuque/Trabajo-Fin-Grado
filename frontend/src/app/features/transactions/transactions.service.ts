import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { TransactionDTO } from '../../dtos/transaction.dto';

@Injectable({
  providedIn: 'root'
})
export class TransactionsService {
  private apiUrl = `${environment.apiUrl}/transactions`; // Ajusta la URL según tu backend

  constructor(private http: HttpClient) { }

  getTransactions(league: string, season: string): Observable<TransactionDTO[]> {
    // league como path variable y season como query param
    return this.http.get<TransactionDTO[]>(`${this.apiUrl}/league/${league}`, {
      params: { season }
    });
  }
}