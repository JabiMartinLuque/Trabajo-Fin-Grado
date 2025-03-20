import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { LoginComponent } from './features/user_auth/login/login.component';
import { RegisterComponent } from './features/user_auth/register/register.component';
import { MatchesComponent } from './features/matches/matches.component';
import { ProfileComponent } from './features/profile/profile.component';
import { AuthGuard } from './auth.guard';

export const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    {path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
    {path: 'login', component: LoginComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'matches', component: MatchesComponent, canActivate: [AuthGuard]},
    {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]},
    { path: '**', redirectTo: '/home' }

];
