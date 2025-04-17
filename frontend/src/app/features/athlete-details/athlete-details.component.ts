import { Component, OnInit } from '@angular/core';
import { AthletesService } from '../leagues/athletes/athletes.service';
import { ActivatedRoute } from '@angular/router';
import { AthleteDTO } from '../../dtos/athlete.dto';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-athlete-details',
  imports: [MatCardModule, MatDividerModule, MatProgressSpinnerModule, CommonModule, RouterModule, MatIconModule],
  templateUrl: './athlete-details.component.html',
  styleUrl: './athlete-details.component.css'
})
export class AthleteDetailsComponent implements OnInit {
  athlete?: AthleteDTO;
  isLoading = false;
  errorMessage = '';

  constructor(
    private route: ActivatedRoute,
    private athleteService: AthletesService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.loadAthlete(id);
      } else {
        this.errorMessage = 'ID de jugador no proporcionado';
      }
    });
  }

  private loadAthlete(id: string): void {
    this.isLoading = true;
    this.athleteService.getAthleteByid(id).subscribe({
      next: (data) => {
        this.athlete = data;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error cargando jugador', err);
        this.errorMessage = 'No se pudo cargar los datos del jugador';
        this.isLoading = false;
      }
    });
  }
}
