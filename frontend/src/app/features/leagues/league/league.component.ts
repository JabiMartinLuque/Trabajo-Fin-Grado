import { Component } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { LeagueNavbarComponent } from '../league-navbar/league-navbar.component';

@Component({
  selector: 'app-league',
  imports: [MatCardModule, MatTableModule, MatToolbarModule ,RouterModule, LeagueNavbarComponent],
  templateUrl: './league.component.html',
  styleUrl: './league.component.css'
})
export class LeagueComponent {
  leagueId: string = '';

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    // Extraemos el parámetro "id" de la ruta, por ejemplo "esp.1"
    this.leagueId = this.route.snapshot.paramMap.get('id') || '';
  }
}