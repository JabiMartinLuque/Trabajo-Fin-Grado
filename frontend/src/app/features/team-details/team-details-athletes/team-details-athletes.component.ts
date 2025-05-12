import { Component } from '@angular/core';
import { AthleteDTO } from '../../../dtos/athlete.dto';
import { TeamDetailsAthletesService } from './team-details.athletes.service';
import { TeamsService } from '../../leagues/teams/teams.service';
import { TeamDTO } from '../../../dtos/team.dto';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule, Router } from '@angular/router';

@Component({
  selector: 'app-team-details-athletes',
  imports: [MatCardModule, MatProgressSpinnerModule, CommonModule, MatTableModule, MatIconModule, RouterModule],
  templateUrl: './team-details-athletes.component.html',
  styleUrl: './team-details-athletes.component.scss'
})
export class TeamDetailsAthletesComponent {
  team?: TeamDTO; // Team data
  athletes: AthleteDTO[] = []; // Array to hold athletes data
  isLoading: boolean = false; // Loading state
  errorMessage: string = ''; // Error message state
  displayedColumns = ['flag','fullName','position','age','heightWeight'];

  constructor(
    private teamDetailsAthletesService: TeamDetailsAthletesService,
    private commonModule: CommonModule, // CommonModule is imported but not used in this component
    private route: ActivatedRoute, // ActivatedRoute is used to get route parameters
    private teamService: TeamsService, // Service to fetch team data
    private router: Router // RouterModule is used for navigation
  ) {
  }
  ngOnInit(): void {
    this.route.parent?.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.loadTeam(id);
      } else {
        this.errorMessage = 'ID de equipo no proporcionado'; 
      }
    }
    );
  }

  private loadTeam(id: string): void {
    this.isLoading = true;
    this.teamService.getTeamByid(id).subscribe({
      next: team => {
        this.team = team;
        this.loadAthletes(id);
      },
      error: () => {
        this.errorMessage = 'No se pudo cargar los datos del equipo';
        this.isLoading = false;
      }
    });
  }

  loadAthletes(teamId: string): void {
    this.isLoading = true; 
    this.teamDetailsAthletesService.getTeamAthletesByTeam(teamId, this.team?.league || 'esp.1').subscribe({
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
    this.router.navigate(['player', athleteId]);
  }


    hasAthletesWithPosition(position: string): boolean {
      return this.athletes?.some(a => a.position?.displayName === position);
    }
}
