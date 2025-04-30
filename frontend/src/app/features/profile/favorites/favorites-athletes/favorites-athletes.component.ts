import { Component } from '@angular/core';
import { LeagueDTO } from '../../../../dtos/league.dto';
import { TeamDTO } from '../../../../dtos/team.dto';
import { AthleteDTO } from '../../../../dtos/athlete.dto';
import { LeagueService } from '../../../leagues/league.service';
import { TeamsService } from '../../../leagues/teams/teams.service';
import { AthletesService } from '../../../leagues/athletes/athletes.service';
import { FavoritesService } from '../favorites.service';
import { ProfileService } from '../../profile.service';

import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatCheckboxChange, MatCheckboxModule } from '@angular/material/checkbox';

@Component({
  selector: 'app-favorites-athletes',
  imports: [CommonModule, MatCardModule, MatListModule, MatIconModule, MatProgressSpinnerModule, MatCheckboxModule],
  templateUrl: './favorites-athletes.component.html',
  styleUrl: './favorites-athletes.component.css'
})
export class FavoritesAthletesComponent {
  // Paso 1: ligas
  allLeagues: LeagueDTO[] = [];
  isLoadingLeagues = false;

  // Paso 2: equipos de la liga seleccionada
  selectedLeagueSlug: string | null = null;
  allTeams: TeamDTO[] = [];
  isLoadingTeams = false;

  // Paso 3: jugadores del equipo seleccionado
  selectedTeamId: string | null = null;
  allAthletes: AthleteDTO[] = [];
  isLoadingAthletes = false;

  // Favoritos
  favoritePlayerIds = new Set<string>();

  errorMsg = '';

  constructor(
    private leagueService: LeagueService,
    private teamsService: TeamsService,
    private athletesService: AthletesService,
    private favoritesService: FavoritesService,
    private profileService: ProfileService
  ) {}

  ngOnInit(): void {
    this.loadLeagues();
    this.loadUserFavorites();
  }

  private loadLeagues(): void {
    this.isLoadingLeagues = true;
    this.leagueService.getLeagues().subscribe({
      next: leagues => {
        this.allLeagues = leagues.slice(0,5);
        this.isLoadingLeagues = false;
      },
      error: err => {
        console.error(err);
        this.errorMsg = 'Error cargando ligas';
        this.isLoadingLeagues = false;
      }
    });
  }

  private loadUserFavorites(): void {
    const userId = Number(localStorage.getItem('userId'));
    this.profileService.getUserProfile(userId).subscribe(user => {
      user.favoritePlayers?.forEach(fp => this.favoritePlayerIds.add(fp.playerId.toString()));
    });
  }

  // Liga seleccionada
  onLeagueSelect(slug: string): void {
    if (this.selectedLeagueSlug === slug) return;
    this.selectedLeagueSlug = slug;
    this.selectedTeamId = null;
    this.allAthletes = [];
    this.loadTeams(slug);
  }

  private loadTeams(slug: string): void {
    this.isLoadingTeams = true;
    this.allTeams = [];
    this.teamsService.getTeamsByLeague(slug).subscribe({
      next: teams => {
        this.allTeams = teams;
        this.isLoadingTeams = false;
      },
      error: err => {
        console.error(err);
        this.errorMsg = 'Error cargando equipos';
        this.isLoadingTeams = false;
      }
    });
  }

  // Equipo seleccionado
  onTeamSelect(teamId: string): void {
    if (this.selectedTeamId === teamId) return;
    this.selectedTeamId = teamId;
    this.allAthletes = [];
    this.loadAthletes(teamId);
  }

  private loadAthletes(teamId: string): void {
    this.isLoadingAthletes = true;
    const leagueId = this.allLeagues.find(l => l.slug === this.selectedLeagueSlug)?.slug || '';
    console.log('League ID:', leagueId);
    this.athletesService.getAthletesByTeam(teamId, leagueId).subscribe({
      next: athletes => {
        this.allAthletes = athletes;
        this.isLoadingAthletes = false;
      },
      error: err => {
        console.error(err);
        this.errorMsg = 'Error cargando jugadores';
        this.isLoadingAthletes = false;
      }
    });
  }

  isFavorite(athlete: AthleteDTO): boolean {
    return this.favoritePlayerIds.has(athlete.id);
  }

  toggleFavorite(a: AthleteDTO): void {
    if (this.isFavorite(a)) {
      this.favoritesService.removeFavoritePlayer(a.id)
        .subscribe(() => this.favoritePlayerIds.delete(a.id));
    } else {
      this.favoritesService.addFavoritePlayer(a.id)
        .subscribe(() => this.favoritePlayerIds.add(a.id));
    }
  }

  getSelectedLeagueDisplayName(): string {
    const league = this.allLeagues.find(l => l.slug === this.selectedLeagueSlug);
    return league ? league.displayName : 'Liga desconocida';
  }

  getSelectedTeamDisplayName(): string {
    console.log(this.allTeams);
    const team = this.allTeams.find(t => t.id === this.selectedTeamId);
    return team ? team.displayName : 'Equipo desconocido';
  }
}
