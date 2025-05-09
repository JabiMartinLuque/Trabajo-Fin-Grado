import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { LeagueDTO } from '../../../dtos/league.dto';
import { LeagueService } from '../../leagues/league.service';
import { CommonModule } from '@angular/common';



@Component({
  selector: 'app-league-navbar',
  imports: [RouterModule, MatToolbarModule, CommonModule],
  templateUrl: './league-navbar.component.html',
  styleUrl: './league-navbar.component.scss'
})
export class LeagueNavbarComponent implements OnInit {
  leagueId: string = '';
  league?: LeagueDTO;
  isLoading = false;
  errorMessage = '';

  constructor(private route: ActivatedRoute, private legueService: LeagueService) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      console.log('ID de liga:', params.get('league')); // Debugging line
      const id = params.get('league');
      if (id) {
        this.leagueId = id;
        this.loadLeague(id);  // Carga la liga
      } else {
        this.errorMessage = 'ID de liga no proporcionada';
      }
    });
  }

  // Método para navegar a la página de detalles de la liga
  loadLeague(id: string): void {
    this.isLoading = true;
    this.legueService.getLeagueById(id).subscribe({
      next: league => {
        this.league = league;
        this.isLoading = false;
      },
      error: () => {
        this.errorMessage = 'No se pudo cargar los datos de la liga';
        this.isLoading = false;
      }
    });
  }
}
