import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../profile.service';
import { User } from '../../../entities/user';

import { CommonModule } from '@angular/common';
import { MatCard } from '@angular/material/card';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatDividerModule } from '@angular/material/divider';

import { MatDialog } from '@angular/material/dialog';
import { ProfileImageDialogComponent } from '../profile-image-dialog/profile-image-dialog.component';

@Component({
  selector: 'app-profile-details',
  imports: [CommonModule, MatCard, MatCardModule, MatProgressSpinnerModule, MatDividerModule],
  templateUrl: './profile-details.component.html',
  styleUrl: './profile-details.component.scss'
})
export class ProfileDetailsComponent {
  user: User | null = null;
  isLoading: boolean = true;

  constructor(private profileService: ProfileService, private dialog: MatDialog) {}

  ngOnInit(): void {
    const userId = Number(localStorage.getItem('userId'));
    // Supongamos que el servicio getUserProfile devuelve más datos (incluyendo biography, por ejemplo)
    this.profileService.getUserProfile(userId).subscribe({
      next: (data) => {
        this.user = data;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error al cargar el perfil', err);
        this.isLoading = false;
      }
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
}
