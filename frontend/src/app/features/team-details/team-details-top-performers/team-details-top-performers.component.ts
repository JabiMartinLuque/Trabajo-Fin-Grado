import { Component } from '@angular/core';
import { TopPerformersDTO, CategoryDTO, LeaderDTO } from '../../../dtos/top-performers.dto';
import { ActivatedRoute } from '@angular/router';
import { TeamDetailsTopPerformersService } from './team-details-top-performers.service';
import { TeamsService } from '../../leagues/teams/teams.service';
import { AthletesService } from '../../leagues/athletes/athletes.service';
import { TeamDTO } from '../../../dtos/team.dto';

import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatAccordion } from '@angular/material/expansion';
import { MatListModule } from '@angular/material/list';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatTabsModule } from '@angular/material/tabs';

@Component({
  selector: 'app-team-details-top-performers',
  imports: [MatCardModule, MatProgressSpinnerModule, CommonModule, MatFormFieldModule, MatSelectModule, MatListModule, MatExpansionModule, MatTabsModule],
  templateUrl: './team-details-top-performers.component.html',
  styleUrl: './team-details-top-performers.component.scss'
})
export class TeamDetailsTopPerformersComponent {
  teamId!: string;
  topPerformers?: TopPerformersDTO;
  team: TeamDTO | undefined;
  athleteNames: Record<string, string> = {};

  isLoading = false;
  errorMessage = '';

  allowedCategoryNames = [
    'goalsLeaders',
    'assistsLeaders',
    'yellowCards',
    'redCards',
    'foulsCommitted'
  ];

  constructor(
    private route: ActivatedRoute,
    private service: TeamDetailsTopPerformersService,
    private teamService: TeamsService,
    private athletesService: AthletesService
  ) {}

  ngOnInit(): void {
    this.route.parent?.paramMap.subscribe(params => {
      const id = params.get('id');
      console.log('ID de equipo:', id); // Debugging line
      if (id) {
        this.teamId = id;
        this.loadTeam(id);
        this.loadTopPerformers();
      } else {
        this.errorMessage = 'ID de equipo no proporcionado';
      }
    });
  }

  private loadTeam(id: string): void {
    this.isLoading = true;
    this.teamService.getTeamByid(id).subscribe({
      next: team => {
        this.team = team;
        this.isLoading = false;
      },
      error: () => {
        this.errorMessage = 'No se pudo cargar los datos del equipo';
        this.isLoading = false;
      }
    });
  }

  private loadTopPerformers(): void {
    this.isLoading = true;
    this.service.getTopPerformersByTeam(this.teamId, this.team?.currentSeason ,this.team?.league, ).subscribe({
      next: (data) => {
        this.topPerformers = data;
        console.log('Top performers:', this.topPerformers); // Debugging line
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error fetching top performers', err);
        this.errorMessage = 'No se pudieron cargar los máximos anotadores';
        this.isLoading = false;
      }
    });
  }

  getAthleteName(ref: string | undefined): string {
    if (!ref) {
      return 'Desconocido';
    }
    const parts = ref.split('/');
    const lastPart = parts.pop() || '';
    const athleteId = lastPart.split('?')[0];
  
    if (this.athleteNames[athleteId]) {
      return this.athleteNames[athleteId];
    } else {
      this.athletesService.getAthleteByid(athleteId).subscribe({
        next: athlete => {
          this.athleteNames[athleteId] = athlete.fullName;
        },
        error: error => {
          console.error(`Error al obtener el atleta ${athleteId}:`, error);
        }
      });
      return athleteId;
    }
  }

  trackByCategory(_: number, cat: CategoryDTO) {
    return cat.name; // o cat.abbreviation, que es único por categoría
  }
  
  trackByLeader(_: number, leader: LeaderDTO) {
    return leader.athleteRef;
  }
  
}
