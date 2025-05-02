import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatchDetailService } from './match-details.service';
import { EventDTO, CompetitionDTO, EntryDTO } from '../../../dtos/scoreboard';
import { CommonModule } from '@angular/common';
import { AthletesService } from '../../leagues/athletes/athletes.service';
import { RouterModule } from '@angular/router';

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
  matchId: string = '';
  event!: EventDTO;
  isLoading: boolean = false;
  errorMessage: string = '';

  playerNames: Record<string,string> = {};

  sides: Array<'home' | 'away'> = ['home', 'away'];

  constructor(
    private route: ActivatedRoute,
    private matchDetailService: MatchDetailService,
    private athletesService: AthletesService
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


  getStartersByRow(
    comp: CompetitionDTO,
    rowIndex: number,
    side: 'home'|'away'
  ): EntryDTO[] {
    const rows = this.getFormationRows(comp, side);
    const start = rows.slice(0, rowIndex).reduce((a, b) => a + b, 0);
    const count = rows[rowIndex];
    return comp.competitors
      .find(c => c.homeAway === side)!
      .lineUp.entries
      .filter(e => {
        const pos = Number(e.formationPlace);
        return pos > start && pos <= start + count;
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
  getFormationClass(comp: CompetitionDTO, side: 'home' | 'away'): string {
    const c = comp.competitors.find(co => co.homeAway === side);
    return c?.lineUp.formation.formationClass
      ? 'formation-' + c.lineUp.formation.formationClass
      : '';
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