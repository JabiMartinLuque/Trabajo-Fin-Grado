import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StandingDTO } from '../../../dtos/standing.dto';
import { StandingService } from './standing.service';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@Component({
  selector: 'app-standing',
  imports: [CommonModule, MatCardModule, MatTableModule, MatProgressSpinnerModule],
  templateUrl: './standing.component.html',
  styleUrl: './standing.component.css'
})
export class StandingComponent {
  leagueId: string = '';
  standings: StandingDTO[] = [];
  isLoading: boolean = false;
  errorMessage: string = '';
  displayedColumns: string[] = [];

  constructor(
    private route: ActivatedRoute,
    private standingService: StandingService
  ) {}

  ngOnInit(): void {
    // Extraemos el parámetro "league" de la query string
    this.route.parent?.paramMap.subscribe(params => {
      this.leagueId = params.get('league') || '';
      this.loadStandings();
    });
  }

  loadStandings(): void {
    this.isLoading = true;
    this.standingService.getStandingByLeague(this.leagueId).subscribe({
      next: (data: StandingDTO[]) => {
        this.standings = data;
        this.isLoading = false;
        // Definir las columnas de la tabla
        if (this.standings && this.standings.length > 0 && this.standings[0].records?.[0]?.stats) {
          // La primera columna será siempre "team", luego una para cada estadística
          this.displayedColumns = ['team'];
          for (let i = 0; i < this.standings[0].records[0].stats.length; i++) {
            this.displayedColumns.push('stat' + i);
          }
        }
      },
      error: (error) => {
        console.error('Error fetching standings', error);
        this.errorMessage = 'Error fetching standings';
        this.isLoading = false;
      }
    });
  }
}
