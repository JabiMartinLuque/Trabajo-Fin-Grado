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

export const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    {path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
    {path: 'login', component: LoginComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'matches', component: MatchesComponent, canActivate: [AuthGuard]},
    {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]},
    {
    path: ':league', 
    children: [
      { path: 'standing', component: StandingComponent },
      { path: 'players', component: AthletesComponent },
      { path: 'teams', component: TeamsComponent },
      { path: '', redirectTo: 'standing', pathMatch: 'full' }
    ]
  },
    { path: '**', redirectTo: '/home' }

];
