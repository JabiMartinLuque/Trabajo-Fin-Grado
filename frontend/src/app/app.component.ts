import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatMenuPanel } from '@angular/material/menu';
import { User } from './entities/user';
import { CommonModule } from '@angular/common';
import { ProfileService } from './features/profile/profile.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterModule, MatToolbarModule, MatButtonModule, MatMenuModule, MatIconModule, MatSidenavModule, MatListModule, MatExpansionModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'frontend';

  user: User | null = null;
  
  constructor(private profileService: ProfileService) { }

    ngOnInit(): void {
        const storedUserId = localStorage.getItem('userId');
        const userId = storedUserId ? parseInt(storedUserId, 10) : null;
        if (userId) {
            this.profileService.getUserProfile(userId).subscribe({
                next: (data: User) => {
                    this.user = data;
                },
                error: () => {
                    console.error('Error fetching user profile');
                }
            });
        }
    }
}
