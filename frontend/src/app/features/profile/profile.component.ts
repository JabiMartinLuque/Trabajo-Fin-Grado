import { Component, OnInit } from '@angular/core';
import { ProfileService} from './profile.service';
import { UserStateService } from './userState.service';
import { User } from '../../entities/user';
import { TeamDTO } from '../../dtos/team.dto';
import { AthleteDTO } from '../../dtos/athlete.dto';

import { CommonModule } from '@angular/common';
import { MatCard } from '@angular/material/card';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinner } from '@angular/material/progress-spinner';
import { MatDialog } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';


import { RouterModule, Router } from '@angular/router';

import { ProfileImageDialogComponent } from './profile-image-dialog/profile-image-dialog.component';
import { AthletesService } from '../leagues/athletes/athletes.service';
import { TeamsService } from '../leagues/teams/teams.service';
import { LeagueService } from '../leagues/league.service';
import { LeagueDTO } from '../../dtos/league.dto';
import { MatChipsModule } from '@angular/material/chips';

@Component({
  selector: 'app-profile',
  imports: [CommonModule, MatCard, MatCardModule, MatDividerModule, MatListModule, MatIconModule
    , MatProgressSpinner, RouterModule, MatChipsModule, MatFormFieldModule, FormsModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent implements OnInit {
  user: User | null = null;
  isLoading: boolean = false;
  errorMessage: string = '';
  favoritePlayers: AthleteDTO[] = []; // Lista de jugadores favoritos
  favoriteTeams: TeamDTO[] = []; // Lista de equipos favoritos
  favoriteLeagues: LeagueDTO[] = []; // Lista de ligas favoritas

  editing = { username: false, email: false };
  private backup = { username: '', email: '' };

  constructor(
    private profileService: ProfileService,
    private userStateService: UserStateService,
    private athletesService: AthletesService,
    private leaguesService: LeagueService, 
    private teamsService: TeamsService,
    private router: Router,
    private dialog: MatDialog) { }

  ngOnInit(): void {
    this.isLoading = true;
    const storedUserId = localStorage.getItem('userId');
    const userId = storedUserId ? parseInt(storedUserId, 10) : null;
  
    if (userId) {
      this.profileService.getUserProfile(userId).subscribe({
        next: (data: User) => {
          this.user = data;
          this.userStateService.setUser(data); // Actualiza el estado del usuario en el servicio
          this.isLoading = false;
          this.loadFavorites(); // Llamamos al método que carga jugadores y equipos

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
        console.log(athlete);
        this.favoritePlayers.push(athlete);
      });
    });
  
    this.user?.favoriteTeams?.forEach((fav) => {
      this.teamsService.getTeamByid(fav.teamId).subscribe((team: any) => {
        this.favoriteTeams.push(team);
      });
    });

    this.user?.favoriteLeagues?.forEach((fav) => {
      this.leaguesService.getLeagueById(fav.leagueId).subscribe((league: any) => {
        this.favoriteLeagues.push(league);
      });
    });
    
  }

  openProfileImageDialog(): void {
    const dialogRef = this.dialog.open(ProfileImageDialogComponent, {
      width: '400px',
      data: { imageUrl: this.user?.profileImageUrl, username: this.user?.username }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        // Actualiza la imagen de perfil del usuario
        if(this.user) {
          this.user.profileImageUrl = result;
        }
      }
    });
  }

  toggleEdit(field: 'username'|'email') {
    if (this.editing[field]) return;
    if (this.user) {
      this.backup[field] = this.user[field];
      this.editing[field] = true;
    }
  }

  cancelEdit() {
    if (this.user) {
      if (this.editing.username) this.user.username = this.backup.username;
      if (this.editing.email)    this.user.email    = this.backup.email;
    }
    this.editing = { username: false, email: false };
  }

  saveEdit() {
  if (this.user) {
    if (!this.validateEmail(this.user.email)) {
      alert('El formato del correo no es válido');
      return;
    }
    this.profileService.updateProfile(this.user).subscribe({
      next: (updatedUser) => {
        this.user = updatedUser;
        this.editing = { username: false, email: false };
      },
      error: () => {
        this.cancelEdit();
      }
    });
  }
}

  logout(): void {
    localStorage.removeItem('jwt');
    localStorage.removeItem('userId');
    
    this.router.navigate(['/login']);
  }

  validateEmail(email: string): boolean {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
  }

}