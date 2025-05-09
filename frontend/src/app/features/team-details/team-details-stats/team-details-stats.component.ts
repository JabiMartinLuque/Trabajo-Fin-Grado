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
import { MatListModule } from '@angular/material/list';

@Component({
  selector: 'app-team-details-stats',
  imports: [MatCardModule, MatProgressSpinnerModule, CommonModule, MatFormFieldModule, MatSelectModule, MatTableModule, MatListModule],
  templateUrl: './team-details-stats.component.html',
  styleUrl: './team-details-stats.component.scss'
})
export class TeamDetailsStatsComponent {
  team?: TeamDTO;
  allStats: StatisticsDTO[] = [];
  selectedSplit?: SplitDTO;
  isLoading = false;
  errorMessage = '';
  selectedLeague?: string;
  cat: any;

  allowedStats = ['appearances', 'draws', 'foulsCommitted', 'foulsSuffered', 'losses', 'redCards', 'wins', 'yellowCards'];
  orderedStats = ['appearances', 'wins', 'draws', 'losses', 'foulsCommitted', 'foulsSuffered', 'yellowCards', 'redCards'];

  allowedDefensiveStats = ['blockedShots', 'effectiveClearance', 'effectiveTackles', 'inneffectiveTackles', 'interceptions', 'tacklePct', 'totalClearance', 'totalTackles'];
  orderedDefensiveStats = ['blockedShots', 'effectiveClearance', 'effectiveTackles', 'inneffectiveTackles', 'interceptions', 'tacklePct', 'totalClearance', 'totalTackles'];
  
  allowedAttackStats = ['accuratePasses', 'freeKickGoals', 'freeKickShots', 'gameWinningGoals', 'goalAssists', 'headedGoals', 'offsides', 'penaltyKickPct', 'penaltyKickShots', 'shotsOnTarget', 'totalGoals', 'totalPasses', 'totalShots'];

  orderedAttackStats = ['totalGoals', 'gameWinningGoals', 'goalAssists', 'freeKickGoals', 'freeKickShots', 'shotsOnTarget', 'accuratePasses', 'totalPasses', 'totalShots', 'headedGoals', 'offsides', 'penaltyKickPct', 'penaltyKickShots'];

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
        this.loadStats(id);
      },
      error: () => {
        this.errorMessage = 'No se pudo cargar los datos del equipo';
        this.isLoading = false;
      }
    });
  }

  private loadStats(id: string): void {
    this.isLoading = true;
    this.statsService.getTeamStatisticsByid(id, this.team?.currentSeason || '2024', this.team?.league || 'esp.1').subscribe({
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

  getAllowedStats(stats: any[]): any[] {
    return stats.filter(s => this.allowedStats.includes(s.name));
  }

  getOrderedStats(stats: any[]): any[] {
    return stats.sort((a, b) =>
      this.orderedStats.indexOf(a.name) - this.orderedStats.indexOf(b.name)
    );
  }

  getAllowedDefensiveStats(stats: any[]): any[] {
    return stats.filter(s => this.allowedDefensiveStats.includes(s.name));
  }
  getOrderedDefensiveStats(stats: any[]): any[] {
    return stats.sort((a, b) =>
      this.orderedDefensiveStats.indexOf(a.name) - this.orderedDefensiveStats.indexOf(b.name)
    );
  }

  getAllowedAttackStats(stats: any[]): any[] {
    return stats.filter(s => this.allowedAttackStats.includes(s.name));
  }

  // Función para ordenar las stats de ataque según orderedAttackStats
  getOrderedAttackStats(stats: any[]): any[] {
    return stats.sort((a, b) =>
      this.orderedAttackStats.indexOf(a.name) - this.orderedAttackStats.indexOf(b.name)
    );
  }
}