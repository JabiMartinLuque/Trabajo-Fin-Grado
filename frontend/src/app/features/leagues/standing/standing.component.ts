import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StandingDTO, StatDTO } from '../../../dtos/standing.dto';
import { StandingService } from './standing.service';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-standing',
  imports: [CommonModule, MatCardModule, MatTableModule, MatProgressSpinnerModule, RouterModule],
  templateUrl: './standing.component.html',
  styleUrl: './standing.component.scss'
})
export class StandingComponent implements OnInit {
  leagueId = '';
  standings: StandingDTO[] = [];
  isLoading = false;
  errorMessage = '';
  // Abreviaturas de las estadísticas que queremos mostrar, en orden
  importantStats = ['J', 'G', 'E', 'P', 'F', 'A', 'GD', 'PTS'];
  // Después de cargar, contendrá los objetos StatDTO filtrados
  displayedStats: StatDTO[] = [];
  // Columnas finales para MatTable
  displayedColumns: string[] = [];
 
  constructor(
    private route: ActivatedRoute,
    private standingService: StandingService
  ) {}

  ngOnInit(): void {
    this.route.parent?.paramMap.subscribe(params => {
      this.leagueId = params.get('league') || '';
      this.loadStandings();
    });
  }

  loadStandings(): void {
    this.isLoading = true;
    this.standingService.getStandingByLeague(this.leagueId).subscribe({
      next: data => {
        this.standings = data;
        this.isLoading = false;

        if (this.standings.length) {
          // Tomamos las stats del primer registro
          const allStats = this.standings[0].records[0].stats;
          // Filtramos sólo las importantes
          this.displayedStats = this.importantStats
            .map(abbr => allStats.find(s => s.abbreviation === abbr))
            .filter((s): s is StatDTO => !!s);

          // Columnas: indicador, pos, team y luego cada abbreviation
          this.displayedColumns = [
            'indicator',
            'pos',
            'team',
            ...this.displayedStats.map(s => s.abbreviation)
          ];
        }
      },
      error: err => {
        console.error(err);
        this.errorMessage = 'Error fetching standings';
        this.isLoading = false;
      }
    });
  }

  getStatDisplayValue(row: any, statAbbreviation: string): string | undefined {
    return row.records[0]?.stats.find((s: any) => s.abbreviation === statAbbreviation)?.displayValue;
  }
}

