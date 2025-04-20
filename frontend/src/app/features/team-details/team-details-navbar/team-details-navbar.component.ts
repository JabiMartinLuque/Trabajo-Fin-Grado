import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';

@Component({
  selector: 'app-team-details-navbar',
  imports: [CommonModule, RouterModule, MatToolbarModule],
  templateUrl: './team-details-navbar.component.html',
  styleUrl: './team-details-navbar.component.css'
})
export class TeamDetailsNavbarComponent {
  
}
