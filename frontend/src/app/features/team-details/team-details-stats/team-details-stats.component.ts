import { Component } from '@angular/core';
import { SplitDTO, StatisticsDTO } from '../../../dtos/statistics.dto';
import { ActivatedRoute } from '@angular/router';
import { TeamDetailsStatsService } from './team-details-stats.service';
import { TeamDTO } from '../../../dtos/team.dto';
import { TeamsService } from '../../leagues/teams/teams.service';

import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-team-details-stats',
  imports: [MatCardModule, MatProgressSpinnerModule, CommonModule, MatFormFieldModule, MatSelectModule, MatTableModule],
  templateUrl: './team-details-stats.component.html',
  styleUrl: './team-details-stats.component.css'
})
export class TeamDetailsStatsComponent {
  team?: TeamDTO;
  allStats: StatisticsDTO[] = [];
  selectedSplit?: SplitDTO;
  isLoading = false;
  errorMessage = '';
  selectedLeague?: string;

  constructor(
    private route: ActivatedRoute,
    private statsService: TeamDetailsStatsService,
    private teamService: TeamsService
  ) {}

  ngOnInit(): void {
    this.route.parent?.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.loadTeam(id);
        this.loadStats(id);
        console.log(this.team?.competitions)
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

  private loadStats(id: string): void {
    this.isLoading = true;
    const season = this.team?.currentSeason || '2024';
    const league = this.selectedLeague || 'esp.1';
    this.statsService.getTeamStatisticsByid(id, season, league).subscribe({
      next: (stats: StatisticsDTO) => {
        this.allStats = [stats];
        // seleccionar por defecto el primer split si existe
        if (this.allStats.length > 0) {
          this.selectedSplit = this.allStats[0].splits;
        }
        this.isLoading = false;
      },
      error: () => {
        this.errorMessage = 'No se pudo cargar las estadísticas';
        this.isLoading = false;
      }
    });
  }

  onSplitChange(splitId: string): void {
    const found = this.allStats.find(s => s.splits.id === splitId);
    this.selectedSplit = found?.splits;
  }
}