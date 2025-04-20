import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TeamDTO } from '../../dtos/team.dto';
import { TeamsService } from '../leagues/teams/teams.service';
import { ActivatedRoute } from '@angular/router';
import { RouterModule } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatToolbarModule } from '@angular/material/toolbar';

@Component({
  selector: 'app-team-details',
  imports: [CommonModule, MatCardModule, MatDividerModule, MatProgressSpinnerModule, RouterModule, MatToolbarModule],
  templateUrl: './team-details.component.html',
  styleUrl: './team-details.component.css'
})
export class TeamDetailsComponent {
  team?: TeamDTO;
  isLoading = false;
  errorMessage = '';

  constructor(
    private route: ActivatedRoute,
    private teamService: TeamsService
  ) {}

  ngOnInit(): void {
    this.route.parent?.paramMap.subscribe(params => {
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
      next: (data) => {
        this.team = data;
        this.isLoading = false;
        console.log('Equipo:', this.team);
      },
      error: (err) => {
        console.error('Error cargando equipo', err);
        this.errorMessage = 'No se pudo cargar los datos del equipo';
        this.isLoading = false;
      }
    });
  }

}
