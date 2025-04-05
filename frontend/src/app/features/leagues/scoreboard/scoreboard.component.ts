import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ScoreboardDTO } from '../../../dtos/scoreboard';
import { ScoreboardService } from './scoreboard.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-scoreboard',
  imports: [CommonModule],
  templateUrl: './scoreboard.component.html',
  styleUrl: './scoreboard.component.css'
})
export class ScoreboardComponent implements OnInit {
  league: string = '';
  scoreboard: ScoreboardDTO | null = null;
  isLoading: boolean = false;
  errorMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private scoreboardService: ScoreboardService
  ) {}

  ngOnInit(): void {
    // Suponiendo que la liga se pasa como parámetro de ruta, por ejemplo: /league/scoreboard/esp.1
    this.route.paramMap.subscribe(params => {
      // Si usas query params en vez de ruta, usa: params.get('league')
      this.league = params.get('league') || 'esp.1';
      this.fetchScoreboard();
    });
  }

  fetchScoreboard(): void {
    this.isLoading = true;
    this.scoreboardService.getScoreboard(this.league).subscribe({
      next: data => {
        this.scoreboard = data;
        this.isLoading = false;
      },
      error: err => {
        console.error('Error fetching scoreboard', err);
        this.errorMessage = 'Error fetching scoreboard data';
        this.isLoading = false;
      }
    });
  }
}
