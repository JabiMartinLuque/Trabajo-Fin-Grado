import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TeamEventDTO } from '../../../dtos/team-event.dto';
import { ActivatedRoute } from '@angular/router';
import { TeamDetailsMatchesService } from './team-details-matches.service';
import { TeamDTO } from '../../../dtos/team.dto';

import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { MatAccordion } from '@angular/material/expansion';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatExpansionPanel } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatDividerModule } from '@angular/material/divider';
import { RouterModule, Router } from '@angular/router';

@Component({
  selector: 'app-team-details-matches',
  imports: [CommonModule, MatCardModule, MatProgressSpinnerModule, MatTableModule, RouterModule, MatIconModule, MatChipsModule, MatExpansionModule, MatFormFieldModule, MatSelectModule, MatDividerModule],
  templateUrl: './team-details-matches.component.html',
  styleUrl: './team-details-matches.component.scss'
})
export class TeamDetailsMatchesComponent {
  matches: TeamEventDTO[] = []; // Array to hold team event data
  upcomingMatches: TeamEventDTO[] = [];
  pastMatches: TeamEventDTO[] = [];
  isLoading: boolean = false; // Loading state
  errorMessage: string = ''; // Error message state
  teamId: string = ''; // Team ID

  selectedMonth: number = new Date().getMonth() + 1;
  selectedYear: number = new Date().getFullYear();

  monthOptions = [
    { value: 1, name: 'Enero' },
    { value: 2, name: 'Febrero' },
    { value: 3, name: 'Marzo' },
    { value: 4, name: 'Abril' },
    { value: 5, name: 'Mayo' },
    { value: 6, name: 'Junio' },
    { value: 7, name: 'Julio' },
    { value: 8, name: 'Agosto' },
    { value: 9, name: 'Septiembre' },
    { value: 10, name: 'Octubre' },
    { value: 11, name: 'Noviembre' },
    { value: 12, name: 'Diciembre' }
  ];

  yearOptions = [
    { value: new Date().getFullYear(), name: new Date().getFullYear() },
    { value: new Date().getFullYear() - 1, name: new Date().getFullYear() - 1 },
    { value: new Date().getFullYear() - 2, name: new Date().getFullYear() - 2 },
    { value: new Date().getFullYear() - 3, name: new Date().getFullYear() - 3 },
    { value: new Date().getFullYear() - 4, name: new Date().getFullYear() - 4 },
    { value: new Date().getFullYear() - 5, name: new Date().getFullYear() - 5 }
  ];

  constructor(
    private route: ActivatedRoute, // ActivatedRoute is used to get route parameters
    private router: Router, // RouterModule is used for navigation
    private teamDetailsMatchesService: TeamDetailsMatchesService // Service to fetch team event data
  ) {}
  ngOnInit(): void {
    this.route.parent?.paramMap.subscribe(params => {
      const id = params.get('id'); // Get the team ID from the route parameters
      this.teamId = id || ''; // Set the team ID or an empty string if not found
      if (id) {
        this.loadMatches(id); // Load matches for the team
      } else {
        this.errorMessage = 'ID de equipo no proporcionado'; // Set error message if ID is not provided
      }
    });
  }
  public loadMatches(teamId: string): void {
    const date = new Date();
    let season: number = 0;
    this.isLoading = true;
    if (this.selectedMonth <= 8 ) {
      season = this.selectedYear - 1;
    } else {
      season = this.selectedYear;
    }
    this.teamDetailsMatchesService.getMatchesByTeam(teamId, season, this.selectedMonth, this.selectedYear).subscribe({
      next: (data: TeamEventDTO[]) => {
        // 1) Orden cronológico ascendente (más antiguo primero)
        data.sort((a, b) => new Date(a.date).getTime() - new Date(b.date).getTime());

        // 2) Separar en próximos y pasados
        const now = Date.now();
        this.upcomingMatches = data.filter(e => new Date(e.date).getTime() >= now);
        this.pastMatches     = data.filter(e => new Date(e.date).getTime() <  now);

        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error fetching matches', err);
        this.errorMessage = 'Error al cargar los partidos';
        this.isLoading = false;
      }
    });
  }
  goToMatch(matchId: string) {
    this.router.navigate(['/match', matchId]);
  }
}
