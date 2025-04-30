import { Component, OnInit} from '@angular/core';
import { User } from '../../entities/user';
import { AthleteDTO } from '../../dtos/athlete.dto';
import { TeamDTO } from '../../dtos/team.dto';
import { LeagueDTO } from '../../dtos/league.dto';
import { ProfileService } from '../profile/profile.service';
import { AthletesService } from '../leagues/athletes/athletes.service';
import { LeagueService } from '../leagues/league.service';
import { TeamsService } from '../leagues/teams/teams.service';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';

import { CommonModule } from '@angular/common';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatDividerModule } from '@angular/material/divider';
import { HomeService } from './home.service';
import { TeamEventDTO } from '../../dtos/team-event.dto';
import { ScoreboardDTO, EventDTO } from '../../dtos/scoreboard';

import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatCardModule } from '@angular/material/card';
import { MatTabsModule, MatTabChangeEvent } from '@angular/material/tabs';


@Component({
  selector: 'app-home',
  imports: [CommonModule, MatExpansionModule, MatDividerModule, MatListModule, MatIconModule, MatProgressSpinnerModule, RouterModule, MatCardModule, MatTabsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  user: User | null = null;
  isLoading: boolean = false;
  errorMessage: string = '';
  favoritePlayers: AthleteDTO[] = []; // Lista de jugadores favoritos
  favoriteTeams: TeamDTO[] = []; // Lista de equipos favoritos
  favoriteLeagues: LeagueDTO[] = []; // Lista de ligas favoritas

  athleteMatches: TeamEventDTO[] = []; // Lista de partidos de jugadores
  teamMatches: TeamEventDTO[] = []; // Lista de partidos de equipos
  leagueMatches: ScoreboardDTO[] = []; // Lista de partidos de ligas
  flattenedLeagueEvents: EventDTO[] = []; // Lista de eventos de ligas aplanada

  selectedLeagueSlug: string | null = null;

  constructor(
      private profileService: ProfileService, 
      private athletesService: AthletesService,
      private leaguesService: LeagueService, 
      private teamsService: TeamsService,
      private homeService: HomeService,
      private router: Router) { }

  ngOnInit(): void {
    this.isLoading = true;
    const storedUserId = localStorage.getItem('userId');
    const userId = storedUserId ? parseInt(storedUserId, 10) : null;
  
    if (userId) {
      this.profileService.getUserProfile(userId).subscribe({
        next: (data: User) => {
          this.user = data;
          this.isLoading = false;
          this.loadFavorites();

        },
        error: (error) => {
          console.error('Error fetching profile', error);
          this.errorMessage = 'Error fetching profile data';
          this.isLoading = false;
        }
      });
    } else {
      this.errorMessage = 'No se encontró el ID del usuario en la sesión.';
      this.isLoading = false;
    }
  }

  loadFavorites(): void {
    this.favoritePlayers = [];
    this.favoriteTeams = [];
    this.favoriteLeagues = [];
  
    this.user?.favoritePlayers?.forEach((fav) => {
      this.athletesService.getAthleteByid(fav.playerId).subscribe((athlete: any) => {
        this.favoritePlayers.push(athlete);
        this.fetchAthleteMatches(athlete.id); // Fetch matches for the athlete
      });
    });
  
    this.user?.favoriteTeams?.forEach((fav) => {
      this.teamsService.getTeamByid(fav.teamId).subscribe((team: any) => {
        this.favoriteTeams.push(team);
        this.fetchTeamMatches(team.id); // Fetch matches for the team
      });
    });

    this.user?.favoriteLeagues?.forEach((fav) => {
      this.leaguesService.getLeagueById(fav.leagueId).subscribe((league: any) => {
        this.favoriteLeagues.push(league);
        //this.fetchLeagueMatches(league.slug); // Fetch matches for the league
      });
    });

    setTimeout(() => {
      if (this.favoriteLeagues.length > 0) {
        this.selectedLeagueSlug = this.favoriteLeagues[0].slug;
        this.fetchLeagueMatches(this.selectedLeagueSlug);
      }
    }, 0);
  }

  fetchAthleteMatches(athleteId: string): void {
    this.homeService.getMatchesByAthlete(athleteId)
      .subscribe((matches: TeamEventDTO) => {
        // Une viejos y nuevos
        const all = [...this.athleteMatches, matches];
        // Filtra por id único
        this.athleteMatches = all.filter((m, idx, arr) =>
          arr.findIndex(x => x.id === m.id) === idx
        );
      });
  }
  
  fetchTeamMatches(teamId: string): void {
    if(this.teamMatches.length <= 0) {
      this.homeService.getMatchesByTeam(teamId).subscribe((matches: any) => {
        console.log(matches);
        this.teamMatches.push(matches);
      });
    }
  }
  fetchLeagueMatches(leagueId: string): void {
    this.leagueMatches = [];
    this.flattenedLeagueEvents = [];

    this.homeService.getMatchesByLeague(leagueId)
      .subscribe({
        next: (block: ScoreboardDTO) => {
          this.leagueMatches.push(block);
          this.flattenedLeagueEvents = this.leagueMatches.flatMap(league => league.events);
        },
        error: err => console.error(err)
      });
  }

  goToMatch(matchId: string) {
    this.router.navigate(['/match', matchId]);
  }
}