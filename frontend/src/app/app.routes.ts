import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { LoginComponent } from './features/user_auth/login/login.component';
import { RegisterComponent } from './features/user_auth/register/register.component';
import { MatchesComponent } from './features/matches/matches.component';
import { ProfileComponent } from './features/profile/profile.component';
import { StandingComponent } from './features/leagues/standing/standing.component';
import { TeamsComponent } from './features/leagues/teams/teams.component';
import { AuthGuard } from './auth.guard';
import { AthletesComponent } from './features/leagues/athletes/athletes.component';
import { LeagueComponent } from './features/leagues/league/league.component';
import { ScoreboardComponent } from './features/leagues/scoreboard/scoreboard.component';
import { MatchDetailComponent } from './features/matches/match-details/match-details.component';
import { TransactionsComponent } from './features/transactions/transactions.component';
import { AthleteDetailsComponent } from './features/athlete-details/athlete-details.component';
import { FavoritesTeamsComponent } from './features/profile/favorites/favorites-teams/favorites-teams.component';
import { FavoritesAthletesComponent } from './features/profile/favorites/favorites-athletes/favorites-athletes.component';
import { FavoritesLeaguesComponent } from './features/profile/favorites/favorites-leagues/favorites-leagues.component';
import { TeamDetailsComponent } from './features/team-details/team-details.component';
import { TeamDetailsAthletesComponent } from './features/team-details/team-details-athletes/team-details-athletes.component';
import { TeamDetailsMatchesComponent } from './features/team-details/team-details-matches/team-details-matches.component';
import { TeamDetailsStatsComponent } from './features/team-details/team-details-stats/team-details-stats.component';
import { TeamDetailsNavbarComponent } from './features/team-details/team-details-navbar/team-details-navbar.component';
import { NewsComponent } from './features/leagues/news/news.component';
import { TeamDetailsTopPerformersComponent } from './features/team-details/team-details-top-performers/team-details-top-performers.component';


export const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' }, //http://localhost:4200/home
    { path: 'home', component: HomeComponent, canActivate: [AuthGuard]}, //http://localhost:4200/home
    { path: 'login', component: LoginComponent}, //http://localhost:4200/login
    { path: 'register', component: RegisterComponent}, //http://localhost:4200/register
    { path: 'matches', component: MatchesComponent, canActivate: [AuthGuard]}, //http://localhost:4200/matches
    { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },

  // Ahora son rutas independientes
  { path: 'favorites/teams',   component: FavoritesTeamsComponent,    canActivate: [AuthGuard] },
  { path: 'favorites/players', component: FavoritesAthletesComponent,  canActivate: [AuthGuard] },
  { path: 'favorites/leagues', component: FavoritesLeaguesComponent,  canActivate: [AuthGuard] }, //http://localhost:4200/profile

    { path: 'match/:id', component: MatchDetailComponent, canActivate: [AuthGuard]}, //http://localhost:4200/match/12345
    { path: 'transactions/:league', component: TransactionsComponent, canActivate: [AuthGuard] }, //http://localhost:4200/transactions
    { path: 'player/:id', component: AthleteDetailsComponent, canActivate: [AuthGuard] }, //http://localhost:4200/athlete/12345
    { path: 'team/:id', 
      component: TeamDetailsNavbarComponent,
      canActivate: [AuthGuard], 
      children: [
        { path: 'details', component: TeamDetailsComponent, canActivate: [AuthGuard] },
        { path: 'athletes', component: TeamDetailsAthletesComponent, canActivate: [AuthGuard] },
        { path: 'matches', component: TeamDetailsMatchesComponent, canActivate: [AuthGuard] },
        { path: 'stats', component: TeamDetailsStatsComponent, canActivate: [AuthGuard]},
        { path: 'top-performers', component: TeamDetailsTopPerformersComponent, canActivate: [AuthGuard] },
      ] 
    }, //http://localhost:4200/team/12345
    {
    path: 'league/:league', //http://localhost:4200/league/esp.1
    component: LeagueComponent,
    canActivate: [AuthGuard],
    children: [
      { path: 'standing', component: StandingComponent },
      { path: 'news', component: NewsComponent },
      { path: 'teams', component: TeamsComponent },
      { path: 'scoreboard', component: ScoreboardComponent },
      { path: '', redirectTo: 'standing', pathMatch: 'full' }
    ]
  },
    { path: '**', redirectTo: '/home' }

];
