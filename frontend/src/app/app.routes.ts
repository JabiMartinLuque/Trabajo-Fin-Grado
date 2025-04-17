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

export const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' }, //http://localhost:4200/home
    { path: 'home', component: HomeComponent, canActivate: [AuthGuard]}, //http://localhost:4200/home
    { path: 'login', component: LoginComponent}, //http://localhost:4200/login
    { path: 'register', component: RegisterComponent}, //http://localhost:4200/register
    { path: 'matches', component: MatchesComponent, canActivate: [AuthGuard]}, //http://localhost:4200/matches
    { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]}, //http://localhost:4200/profile
    { path: 'match/:id', component: MatchDetailComponent, canActivate: [AuthGuard]}, //http://localhost:4200/match/12345
    { path: 'transactions/:league', component: TransactionsComponent, canActivate: [AuthGuard] }, //http://localhost:4200/transactions
    { path: 'player/:id', component: AthleteDetailsComponent, canActivate: [AuthGuard] }, //http://localhost:4200/athlete/12345
    {
    path: ':league/:league', //http://localhost:4200/league/esp.1
    component: LeagueComponent,
    canActivate: [AuthGuard],
    children: [
      { path: 'standing', component: StandingComponent },
      { path: 'players', component: AthletesComponent },
      { path: 'teams', component: TeamsComponent },
      { path: 'scoreboard', component: ScoreboardComponent },
      { path: '', redirectTo: 'standing', pathMatch: 'full' }
    ]
  },
    { path: '**', redirectTo: '/home' }

];
