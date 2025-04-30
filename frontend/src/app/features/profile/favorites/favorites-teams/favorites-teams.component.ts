import { Component } from '@angular/core';
import { LeagueService } from '../../../leagues/league.service';
import { TeamsService } from '../../../leagues/teams/teams.service';
import { FavoritesService } from '../favorites.service';
import { ProfileService } from '../../profile.service';
import { TeamDTO } from '../../../../dtos/team.dto';
import { LeagueDTO } from '../../../../dtos/league.dto';

import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatCheckboxChange, MatCheckboxModule } from '@angular/material/checkbox';

@Component({
  selector: 'app-favorites-teams',
  imports: [CommonModule, MatCardModule, MatListModule, MatIconModule, MatProgressSpinnerModule, MatCheckboxModule],
  templateUrl: './favorites-teams.component.html',
  styleUrl: './favorites-teams.component.css'
})
export class FavoritesTeamsComponent {
  isLoadingLeagues = false;
  isLoadingTeams = false;
  errorMsg = '';

  allLeagues: LeagueDTO[] = [];
  selectedLeagueSlug: string | null = null;

  allTeams: TeamDTO[] = [];
  favoriteTeamIds = new Set<string>();

  constructor(
    private leagueService: LeagueService,
    private teamsService: TeamsService,
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
      next: (leagues) => {
        this.allLeagues = leagues.slice(0, 5); // Las “Big 5”
        this.isLoadingLeagues = false;
      },
      error: (err) => {
        this.errorMsg = 'Error cargando ligas';
        console.error(err);
        this.isLoadingLeagues = false;
      }
    });
  }

  private loadUserFavorites(): void {
    // Para precargar qué equipos están marcados
    const userId = Number(localStorage.getItem('userId'));
    this.profileService.getUserProfile(userId).subscribe(user => {
      user.favoriteTeams?.forEach(fav => this.favoriteTeamIds.add(fav.teamId.toString()));
    });
  }

  onLeagueSelect(slug: string): void {
    if (this.selectedLeagueSlug === slug) return;
    this.selectedLeagueSlug = slug;
    this.loadTeamsForLeague(slug);
  }

  private loadTeamsForLeague(slug: string): void {
    this.isLoadingTeams = true;
    this.allTeams = [];
    this.teamsService.getTeamsByLeague(slug).subscribe({
      next: teams => {
        this.allTeams = teams;
        this.isLoadingTeams = false;
      },
      error: err => {
        this.errorMsg = 'Error cargando equipos';
        console.error(err);
        this.isLoadingTeams = false;
      }
    });
  }

  isFavorite(team: TeamDTO): boolean {
    return this.favoriteTeamIds.has(team.id);
  }

  toggleFavorite(team: TeamDTO): void {
    const teamId = team.id;
    if (this.isFavorite(team)) {
      this.favoritesService.removeFavoriteTeam(teamId)
        .subscribe(() => this.favoriteTeamIds.delete(teamId));
    } else {
      this.favoritesService.addFavoriteTeam(teamId)
        .subscribe(() => this.favoriteTeamIds.add(teamId));
    }
  }

  getSelectedLeagueDisplayName(): string {
    const league = this.allLeagues?.find(l => l.slug === this.selectedLeagueSlug);
    return league ? league.displayName : 'Liga desconocida';
  }
}
