import { Component, OnInit, inject } from '@angular/core';
import { ProfileService } from '../../profile.service';
import { LeagueService } from '../../../leagues/league.service';
import { User } from '../../../../entities/user';
import { LeagueDTO } from '../../../../dtos/league.dto';;
import { CommonModule } from '@angular/common';
import { forkJoin } from 'rxjs';
import { FavoritesService } from '../favorites.service';

import { MatCardModule } from '@angular/material/card';
import { MatSelectionList } from '@angular/material/list';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatCheckboxChange, MatCheckboxModule } from '@angular/material/checkbox';

@Component({
  selector: 'app-favorites-leagues',
  imports: [CommonModule, MatCardModule, MatListModule, MatIconModule, MatProgressSpinnerModule, MatCheckboxModule],
  templateUrl: './favorites-leagues.component.html',
  styleUrl: './favorites-leagues.component.scss'
})
export class FavoritesLeaguesComponent implements OnInit {
  private profileSvc = inject(ProfileService);
  private leagueSvc  = inject(LeagueService);
  private favoriteLeaguesSvc = inject(FavoritesService);

  user: User | null = null;
  allLeagues: LeagueDTO[] = [];
  favoriteLeagueIds = new Set<string>(); // Contiene los IDs de ligas favoritas

  isLoading = false;
  errorMsg = '';

  ngOnInit(): void {
    this.isLoading = true;
    const storedUserId = localStorage.getItem('userId');
    const userId = storedUserId ? parseInt(storedUserId, 10) : null;

    if (!userId) {
      this.errorMsg = 'No se encontró el ID del usuario.';
      this.isLoading = false;
      return;
    }

    // Cargamos en paralelo todas las ligas y el perfil del usuario
    forkJoin([
      this.leagueSvc.getLeagues(),           // Devuelve un array con todas las ligas
      this.profileSvc.getUserProfile(userId) // Devuelve el usuario con sus ligas favoritas
    ]).subscribe({
      next: ([leagues, userData]) => {
        this.allLeagues = leagues;
        this.user = userData;

        // Agregamos a la lista los IDs de ligas favoritas del usuario
        userData.favoriteLeagues?.forEach(fav => {
          this.favoriteLeagueIds.add(fav.leagueId);
        });
      },
      error: err => {
        console.error('Error al cargar ligas o perfil', err);
        this.errorMsg = 'No se pudo cargar la información.';
      },
      complete: () => (this.isLoading = false)
    });
  }

  toggleFavorite(league: LeagueDTO) {
    if (!this.user) return;

    const leagueId = league.slug; // Ajusta según tu propiedad: id, leagueId, slug, etc.

    // Si ya es favorita, la quitamos
    if (this.favoriteLeagueIds.has(leagueId)) {
      // Llamada al backend: removeFavoriteLeague(...)
      this.favoriteLeaguesSvc.removeFavoriteLeague(leagueId).subscribe({
        next: () => {
          this.favoriteLeagueIds.delete(leagueId);
        },
        error: err => console.error('Error al quitar liga', err)
      });
    } else {
      // Si no lo es, la añadimos
      // Llamada al backend: addFavoriteLeague(...)
      this.favoriteLeaguesSvc.addFavoriteLeague(leagueId).subscribe({
        next: () => {
          this.favoriteLeagueIds.add(leagueId);
        },
        error: err => console.error('Error al agregar liga', err)
      });
    }
  }

  onCheckboxChange(lg: LeagueDTO): void {
  // clona el Set para que Angular detecte el cambio
  const favs = new Set(this.favoriteLeagueIds);
  favs.has(lg.slug) ? favs.delete(lg.slug) : favs.add(lg.slug);
  this.favoriteLeagueIds = favs;

  this.toggleFavorite(lg);          // tu llamada existente al servicio
}

  
}
