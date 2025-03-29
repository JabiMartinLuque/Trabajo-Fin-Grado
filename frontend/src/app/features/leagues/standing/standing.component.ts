import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StandingDTO } from '../../../dtos/standing.dto';
import { StandingService } from './standing.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-standing',
  imports: [CommonModule],
  templateUrl: './standing.component.html',
  styleUrl: './standing.component.css'
})
export class StandingComponent {
  leagueId: string = '';
  standings: StandingDTO[] = [];
  isLoading: boolean = false;
  errorMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private standingService: StandingService
  ) {}

  ngOnInit(): void {
    // Extraemos el parámetro "id" de la ruta (por ejemplo, "esp.1")
    this.route.queryParamMap.subscribe(params => {
      this.leagueId = params.get('league') || '';
      this.loadStandings();
    });
  }

  loadStandings(): void {
    this.isLoading = true;
    this.standingService.getStandingByLeague(this.leagueId).subscribe({
      next: (data: StandingDTO[]) => {
        console.log("Standings data received: ", data);
        this.standings = data;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error fetching standings', error);
        this.errorMessage = 'Error fetching standings';
        this.isLoading = false;
      }
    });
  }
}
