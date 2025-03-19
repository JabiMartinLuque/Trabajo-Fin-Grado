import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { LoginComponent } from './features/user_auth/login/login.component';
import { RegisterComponent } from './features/user_auth/register/register.component';
import { MatchesComponent } from './features/matches/matches.component';
import { ProfileComponent } from './features/profile/profile.component';

export const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    {path: 'home', component: HomeComponent},
    {path: 'login', component: LoginComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'matches', component: MatchesComponent},
    {path: 'profile', component: ProfileComponent},
    { path: '**', redirectTo: '/home' }

];
