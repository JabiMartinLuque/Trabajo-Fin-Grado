import { Component, OnInit } from '@angular/core';
import { ProfileService, UserWithFavorites} from './profile.service';
import { User } from '../../entities/user';
import { TeamDTO } from '../../dtos/team.dto';

import { CommonModule } from '@angular/common';
import { MatCard } from '@angular/material/card';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinner } from '@angular/material/progress-spinner';

@Component({
  selector: 'app-profile',
  imports: [CommonModule, MatCard, MatCardModule, MatDividerModule, MatListModule, MatIconModule, MatProgressSpinner],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit {
  user: User | null = null;
  isLoading: boolean = false;
  errorMessage: string = '';

  constructor(private profileService: ProfileService) { }

  ngOnInit(): void {
    this.isLoading = true;
    const storedUserId = localStorage.getItem('userId');
    const userId = storedUserId ? parseInt(storedUserId, 10) : null;
  
    if (userId) {
      this.profileService.getUserProfile(userId).subscribe({
        next: (data: UserWithFavorites) => {
          this.user = data;
          this.isLoading = false;
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
}