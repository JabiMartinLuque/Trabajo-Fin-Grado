import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatchDetailService } from './match-details.service';
import { EventDTO } from '../../../dtos/scoreboard';
import { CommonModule } from '@angular/common';

import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatDivider } from '@angular/material/divider';
import { MatChipsModule } from '@angular/material/chips';

import { FlexLayoutModule } from '@angular/flex-layout';

@Component({
  selector: 'app-match-details',
  imports: [MatCardModule, MatTableModule, MatProgressSpinnerModule, MatToolbarModule, MatIconModule, MatChipsModule ,MatDivider, CommonModule, FlexLayoutModule],
  templateUrl: './match-details.component.html',
  styleUrl: './match-details.component.css'
})
export class MatchDetailComponent implements OnInit {
  matchId: string = '';
  event!: EventDTO;
  isLoading: boolean = false;
  errorMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private matchDetailService: MatchDetailService
  ) {}

  ngOnInit(): void {
    // Asumimos que la ruta tiene un parámetro llamado "id"
    this.route.paramMap.subscribe(params => {
      this.matchId = params.get('id') || '';
      this.loadMatchDetail();
    });
  }

  loadMatchDetail(): void {
    if (!this.matchId) {
      this.errorMessage = 'No se proporcionó el ID del partido';
      return;
    }
    this.isLoading = true;
    this.matchDetailService.getMatchDetail(this.matchId).subscribe({
      next: (data: EventDTO) => {
        this.event = data;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error al cargar el detalle del partido', error);
        this.errorMessage = 'Error al cargar el detalle del partido';
        this.isLoading = false;
      }
    });
  }
}