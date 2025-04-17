import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from '../../../environments/environment';
import { TransactionDTO } from '../../dtos/transaction.dto';

@Injectable({
  providedIn: 'root'
})
export class TransactionsService {
  private apiUrl = `${environment.apiUrl}/transactions`; // Ajusta la URL según tu backend
  private cache = new Map<string, TransactionDTO[]>();


  constructor(private http: HttpClient) { }

  getTransactions(league: string, season: string): Observable<TransactionDTO[]> {

    // Check if the data is already cached
    const cacheKey = `${league}-${season}`;
    if (this.cache.has(cacheKey)) {
      return new Observable<TransactionDTO[]>(observer => {
        observer.next(this.cache.get(cacheKey)!);
        observer.complete();
      });
    }
    // league como path variable y season como query param
    return this.http.get<TransactionDTO[]>(`${this.apiUrl}/league/${league}`, {
      params: { season }
    })
      .pipe(
        tap((transactions: TransactionDTO[]) => this.cache.set(`${league}-${season}`, transactions))); // Cache the result;
  }
}