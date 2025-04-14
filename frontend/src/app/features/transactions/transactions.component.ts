import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TransactionsService } from './transactions.service';
import { TransactionDTO } from '../../dtos/transaction.dto';
import { ActivatedRoute } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';

@Component({
  selector: 'app-transactions',
  imports: [CommonModule, MatCardModule, MatDividerModule, MatProgressSpinnerModule, MatSelectModule, MatFormFieldModule],
  templateUrl: './transactions.component.html',
  styleUrl: './transactions.component.css'
})
export class TransactionsComponent {

  isLoading: boolean = true;
  transactions: TransactionDTO[] = [];
  league: string = '';
  season: string = '2024';

  seasonOptions: string[] = ['2024', '2023', '2022', '2021', '2020', '2019', '2018', '2017', '2016', '2015', '2014', '2013', '2012', '2011', '2010', '2009', '2008'];

  constructor(private transactionsService: TransactionsService, 
              private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.league = params.get('league') || 'esp.1';
      this.getTransactions();
        });

  }

  getTransactions(): void {
    this.isLoading = true;
    this.transactionsService.getTransactions(this.league, this.season).subscribe({
      next: (data) => {
        this.transactions = data;
        this.isLoading = false;
        console.log('Transactions:', this.transactions);
      },
      error: (err) => {
        this.isLoading = false;
        console.error('Error fetching transactions:', err);
      }
    });
  }

  onSeasonChange(newSeason: string): void {
    this.season = newSeason;
    this.getTransactions();
  }

  transform(value: number): string {
    if (value == null) return '';
    
    if (value >= 1000000) {
      // Dividimos en millones
      return (value / 1000000).toFixed(1).replace(/\.0$/, '') + 'M';
    } else if (value >= 1000) {
      // Dividimos en miles
      return (value / 1000).toFixed(1).replace(/\.0$/, '') + 'K';
    } else {
      return value.toString();
    }
  }
}
