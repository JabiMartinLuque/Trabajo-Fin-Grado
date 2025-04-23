import { Component } from '@angular/core';
import { AthleteDTO } from '../../../dtos/athlete.dto';
import { TeamDetailsAthletesService } from './team-details.athletes.service';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTableModule } from '@angular/material/table';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-team-details-athletes',
  imports: [MatCardModule, MatProgressSpinnerModule, CommonModule, MatTableModule, RouterModule],
  templateUrl: './team-details-athletes.component.html',
  styleUrl: './team-details-athletes.component.css'
})
export class TeamDetailsAthletesComponent {
  athletes: AthleteDTO[] = []; // Array to hold athletes data
  isLoading: boolean = false; // Loading state
  errorMessage: string = ''; // Error message state
  displayedColumns = ['flag','fullName','position','age','heightWeight'];

  constructor(
    private teamDetailsAthletesService: TeamDetailsAthletesService,
    private commonModule: CommonModule, // CommonModule is imported but not used in this component
    private route: ActivatedRoute // ActivatedRoute is used to get route parameters
  ) {
  }
  ngOnInit(): void {
    this.route.parent?.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.loadAthletes(id);
      } else {
        this.errorMessage = 'ID de equipo no proporcionado'; 
      }
    }
    );
  }
  loadAthletes(teamId: string): void {
    this.isLoading = true; 
    this.teamDetailsAthletesService.getTeamAthletesByTeam(teamId).subscribe({
      next: (data: AthleteDTO[]) => {
        this.athletes = data; // Assign the fetched athletes data to the component's athletes property
        console.log(this.athletes); // Log the athletes data to the console
        this.isLoading = false; // Set loading state to false
      },
      error: (error) => {
        console.error('Error fetching athletes', error); // Log the error to the console
        this.errorMessage = 'Error fetching athletes'; // Set error message
        this.isLoading = false; // Set loading state to false
      }
    });

  }

  goToAthlete(athleteId: string): void {
    // Navigate to the athlete details page using the athleteId
    window.location.href = '/athletes/' + athleteId;
  }
}
