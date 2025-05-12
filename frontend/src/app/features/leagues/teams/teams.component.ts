import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TeamsService } from './teams.service';
import { TeamDTO } from '../../../dtos/team.dto';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-teams',
  imports: [CommonModule, MatCardModule, MatProgressSpinnerModule, MatCheckboxModule, MatIconModule, RouterModule],
  templateUrl: './teams.component.html',
  styleUrl: './teams.component.scss'
})
export class TeamsComponent {
  teams?: TeamDTO[];
  isLoading: boolean = false;
  errorMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private teamsService: TeamsService
  ) {}

  ngOnInit(): void {
    this.route.parent?.paramMap.subscribe(params => {
      const leagueId = params.get('league') || 'eng.1';
      this.loadTeams(leagueId);
    });
  }

  loadTeams(leagueId: string): void {
    this.isLoading = true;
    this.teamsService.getTeamsByLeague(leagueId).subscribe({
      next: data => {
        this.teams = data;
        this.isLoading = false;
      },
      error: err => {
        console.error('Error fetching teams', err);
        this.errorMessage = 'Error fetching teams data';
        this.isLoading = false;
      }
    });
  }
  
}
