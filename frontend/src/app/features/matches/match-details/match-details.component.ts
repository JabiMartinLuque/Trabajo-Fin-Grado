import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatchDetailService } from './match-details.service';
import { EventDTO, CompetitionDTO, EntryDTO } from '../../../dtos/scoreboard';
import { CommonModule } from '@angular/common';
import { AthletesService } from '../../leagues/athletes/athletes.service';
import { RouterModule } from '@angular/router';
import { interval, Subject, switchMap, takeUntil, startWith, tap } from 'rxjs';

import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatDivider } from '@angular/material/divider';
import { MatChipsModule } from '@angular/material/chips';
import { MatTabsModule } from '@angular/material/tabs';
import { MatListModule } from '@angular/material/list';

import { FlexLayoutModule } from '@angular/flex-layout';

@Component({
  selector: 'app-match-details',
  imports: [MatCardModule, MatTableModule, MatProgressSpinnerModule, MatToolbarModule, MatIconModule, MatChipsModule ,
     CommonModule, FlexLayoutModule, MatTabsModule, MatListModule, RouterModule],
  templateUrl: './match-details.component.html',
  styleUrl: './match-details.component.scss'
})
export class MatchDetailComponent implements OnInit {
  private destroy$ = new Subject<void>();
  matchId: string = '';
  event!: EventDTO;
  isLoading: boolean = false;
  errorMessage: string = '';

  playerNames: Record<string,string> = {};

  sides: Array<'home' | 'away'> = ['home', 'away'];

  /** Mapas de “formationClass” → (formationPlace → columna) */
  private formationPositionMap: Record<string, Array<Record<number,number>>> = {
    '4-2-3-1': [
    { 3: 0, 6: 1, 5: 2, 2: 3 },
    { 4: 0, 8: 1 },
    { 11: 0, 10: 1, 7: 2 },
    { 9: 0 }
  ],

    '4-1-4-1': [
      { 3: 0, 6: 1, 5: 2, 2: 3 },
      { 4: 0 },
      { 11: 0, 10: 1, 8: 2, 7: 3 },
      { 9: 0 }
    ],

    '4-3-3': [
      { 3: 0, 6: 1, 5: 2, 2: 3 },
      { 8: 0, 4: 1, 7: 2 },
      { 11: 0, 9: 1, 10: 2 }
    ],

    '4-4-2': [
      { 3: 0, 6: 1, 5: 2, 2: 3 },
      { 11: 0, 8: 1, 4: 2, 7: 3 },
      { 9: 0, 10: 1 }
    ],

    '5-3-2': [
      { 3: 0, 4: 1, 5: 2, 6: 3, 2: 4 },
      { 11: 0, 8: 1, 7: 3 },
      { 9: 0, 10: 1 }
    ],

    '5-4-1': [
      { 3: 0, 4: 1, 5: 2, 6: 3, 2: 4 },
      { 11: 0, 10: 1, 8: 2, 7: 3 },
      { 9: 0 }
    ],

    '3-4-3': [
      { 3: 0, 5: 1, 6: 2 },
      { 4: 0, 8: 1, 10: 2, 2: 3 },
      { 11: 0, 9: 1, 7: 2 }
    ],

    '3-5-2': [
      { 3: 0, 5: 1, 6: 2 },
      { 4: 0, 8: 1, 10: 2, 2: 3, 7: 4 },
      { 11: 0, 9: 1 }
    ],

    '3-4-2-1': [
      { 4: 0, 5: 1, 6: 2 },
      { 3: 0, 8: 1, 7: 2, 2: 3 },
      { 11: 0, 10: 1 },
      { 9: 0 }
    ],
  };

  constructor(
    private route: ActivatedRoute,
    private matchDetailService: MatchDetailService,
    private athletesService: AthletesService
  ) {}

  ngOnInit(): void {
    // Cada vez que cambie el param “id” en la ruta,
    // arrancamos un polling que ejecute loadMatchDetail()
    this.route.paramMap.pipe(
      tap(params => {
        this.matchId = params.get('id') || '';
      }),
      switchMap(() =>
        // startWith(0) lanza inmediatamente la primera carga,
        // luego cada 30 s repite
        interval(30000).pipe(
          startWith(0)
        )
      ),
      takeUntil(this.destroy$)
    ).subscribe(() => {
      this.loadMatchDetail();
    });
  }

  ngOnDestroy(): void {
    // Cancelamos el polling al salir del componente
    this.destroy$.next();
    this.destroy$.complete();
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
        console.log('Detalles del partido:', this.event);

        this.event.competitions.forEach(comp => {
          comp.competitors.forEach(c => {
            c.lineUp.entries.forEach(entry => {
              const id = entry.playerId;
              if (id && !this.playerNames[id]) {
                this.athletesService.getAthleteByid(id).subscribe(ath => {
                  this.playerNames[id] = ath.fullName;
                });
              }
            });
          });
        });
      },
      error: (error) => {
        console.error('Error al cargar el detalle del partido', error);
        this.errorMessage = 'Error al cargar el detalle del partido';
        this.isLoading = false;
      }
    });
  }

  getHomeTeamLogo(comp: CompetitionDTO): string | undefined {
    const homeCompetitor = comp.competitors.find(c => c.homeAway === 'home');
    return homeCompetitor?.team?.logo;
  }

  getAwayTeamLogo(comp: CompetitionDTO): string | undefined {
    const awayCompetitor = comp.competitors.find(c => c.homeAway === 'away');
    return awayCompetitor?.team?.logo;
  }

  getHomeTeamName(comp: CompetitionDTO): string | undefined {
    const homeCompetitor = comp.competitors.find(c => c.homeAway === 'home');
    return homeCompetitor?.team?.displayName;
  }

  getAwayTeamName(comp: CompetitionDTO): string | undefined {
    const awayCompetitor = comp.competitors.find(c => c.homeAway === 'away');
    return awayCompetitor?.team?.displayName;
  }

  getFilteredDetails(comp: any, homeAway: string): any[] {
    const teamId = comp.competitors.find((c: { homeAway: string; }) => c.homeAway === homeAway)?.team.id;
    return comp.details.filter((d: { team: { id: any; }; }) => d.team.id === teamId);
  }

  /** Devuelve [1, ...summary] y luego lo invertimos en la vista */
  getFormationRows(comp: CompetitionDTO, side: 'home'|'away'): number[] {
    const competitor = comp.competitors.find(c => c.homeAway === side);
    if (!competitor?.lineUp?.formation?.summary) return [];
    const outfield = competitor.lineUp.formation.summary
      .split('-')
      .map(s => Number(s));
    return [1, ...outfield];  // portero + resto
  }

  private getRowPositionMap(formationClass: string, rowIndex: number): Record<number,number> {
    const maps = this.formationPositionMap[formationClass];
    return (maps && maps[rowIndex]) ? maps[rowIndex] : {};
  }

  getStartersByRow(
    comp: CompetitionDTO,
    rowIndex: number,
    side: 'home' | 'away'
  ): EntryDTO[] {
    const competitor = comp.competitors.find(c => c.homeAway === side)!;
    const entries    = competitor.lineUp.entries;
    const rows       = this.getFormationRows(comp, side); // [1,4,2,3,1] por ejemplo
  
    // PORTERO: filaIndex = 0 → sólo formationPlace === 1
    if (rowIndex === 0) {
      return entries
        .filter(e => Number(e.formationPlace) === 1)
        .sort(() => 0);
    }
  
    // Para filas de campo usamos tu plantilla estática
    const formationClass = competitor.lineUp.formation.formationClass;
    const maps           = this.formationPositionMap[formationClass] || [];
    const posMap         = maps[rowIndex - 1] || {};
  
    // Extraemos sólo los códigos que tú has definido en posMap
    const placesInThisRow = Object.keys(posMap).map(k => Number(k));
  
    return entries
      .filter(e => placesInThisRow.includes(Number(e.formationPlace)))
      .sort((a, b) => {
        const pa = Number(a.formationPlace);
        const pb = Number(b.formationPlace);
        const xa = posMap[pa] ?? 0;
        const xb = posMap[pb] ?? 0;
        return xa - xb;
      });
  }
  
  
  /**(3) Suplentes – los con formationPlace===0 */
  getSubstitutes(comp: CompetitionDTO, side: 'home' | 'away'): EntryDTO[] {
    return comp.competitors
      .find(c => c.homeAway === side)!
      .lineUp.entries
      .filter(e => parseInt(e.formationPlace, 10) === 0);
  }

  getPlayerName(id: string): string {
    return this.playerNames[id] || id;
  }

  /** Devuelve la clase “formation-X-Y-Z” según el homeAway */
  getFormationClass(comp: CompetitionDTO, side: 'home'|'away'): string {
    const cls = comp.competitors
      .find(c => c.homeAway === side)!
      .lineUp
      .formation
      .formationClass;    // p.e. "4-2-3-1"
    return `formation-${cls}`;   // ahora → "formation-4-2-3-1"
  }

  getComparativeStats(comp: CompetitionDTO) {
    // Asumimos que comp.competitors[0] es home y [1] es away
    const homeStats = comp.competitors.find(c => c.homeAway==='home')?.statistics || [];
    const awayStats = comp.competitors.find(c => c.homeAway==='away')?.statistics || [];
  
    // Mapeamos por estadística, suponiendo que ambos arrays tienen mismo nombre de stats en igual orden
    return homeStats.map((hs, idx) => {
      const as = awayStats[idx];
      // Convertimos displayValue a número para comparar (quita % si hace falta)
      const parseNum = (v: string) => parseFloat(v.replace('%','')) || 0;
      const h = parseNum(hs.displayValue);
      const a = parseNum(as?.displayValue || '0');
      const highest = h === a ? null : (h > a ? 'home' : 'away');
      return {
        name: hs.name,
        home: hs.displayValue,
        away: as?.displayValue ?? '-',
        highest
      };
    });
  }

  /** Devuelve el competidor local */
  getHome(comp: CompetitionDTO) {
    return comp.competitors.find(c => c.homeAway === 'home')!;
  }

  /** Devuelve el competidor visitante */
  getAway(comp: CompetitionDTO) {
    return comp.competitors.find(c => c.homeAway === 'away')!;
  }
  

}