import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';


@Component({
  selector: 'app-league-navbar',
  imports: [RouterModule, MatToolbarModule],
  templateUrl: './league-navbar.component.html',
  styleUrl: './league-navbar.component.css'
})
export class LeagueNavbarComponent implements OnInit {
  leagueId: string = '';

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    // Si la liga se pasa como parámetro en la ruta (por ejemplo, 'league/:id')
    this.route.parent?.paramMap.subscribe(params => {
      this.leagueId = params.get('id') || '';
    });
  }
}
