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
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-team-details-matches',
  imports: [CommonModule, MatCardModule, MatProgressSpinnerModule, MatTableModule, RouterModule, MatIconModule, MatChipsModule, MatExpansionModule],
  templateUrl: './team-details-matches.component.html',
  styleUrl: './team-details-matches.component.css'
})
export class TeamDetailsMatchesComponent {
  matches: TeamEventDTO[] = []; // Array to hold team event data
  upcomingMatches: TeamEventDTO[] = [];
  pastMatches: TeamEventDTO[] = [];
  isLoading: boolean = false; // Loading state
  errorMessage: string = ''; // Error message state

  constructor(
    private route: ActivatedRoute, // ActivatedRoute is used to get route parameters
    private teamDetailsMatchesService: TeamDetailsMatchesService // Service to fetch team event data
  ) {}
  ngOnInit(): void {
    this.route.parent?.paramMap.subscribe(params => {
      const id = params.get('id'); // Get the team ID from the route parameters
      if (id) {
        this.loadMatches(id); // Load matches for the team
      } else {
        this.errorMessage = 'ID de equipo no proporcionado'; // Set error message if ID is not provided
      }
    });
  }
  private loadMatches(teamId: string): void {
    this.isLoading = true;
    this.teamDetailsMatchesService.getMatchesByTeam(teamId).subscribe({
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
  goToMatch(matchId: string): void {
    // Navigate to the match details page using the matchId
    window.location.href = '/matches/' + matchId;
  }
}
