import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { TeamDTO } from '../../../dtos/team.dto';
import { TeamsService } from '../../leagues/teams/teams.service';


@Component({
  selector: 'app-team-details-navbar',
  imports: [CommonModule, RouterModule, MatToolbarModule],
  templateUrl: './team-details-navbar.component.html',
  styleUrl: './team-details-navbar.component.scss'
})
export class TeamDetailsNavbarComponent {
  team?: TeamDTO;
  isLoading = false;
  errorMessage = ''; 

  constructor(    private teamService: TeamsService,
    private route: ActivatedRoute 
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.loadTeam(id);
      } else {
        this.errorMessage = 'ID de equipo no proporcionado';
      }
    });
  }

  private loadTeam(id: string): void {
    this.isLoading = true;
    this.teamService.getTeamByid(id).subscribe({
      next: team => {
        this.team = team;
        this.isLoading = false;},
      error: () => {
        this.errorMessage = 'No se pudo cargar los datos del equipo';
        this.isLoading = false;
      }
    });
  }
}
