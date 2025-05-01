import { Form } from '@angular/forms';
import { TeamDTO } from './team.dto';

export interface ScoreboardDTO {
    events: EventDTO[];
}

export interface EventDTO {
    id: string;
    date: string;
    name: string;
    shortName: string;
    competitions: CompetitionDTO[];
}

export interface CompetitionDTO {
    startDate: string;
    status: StatusDTO;
    venue: VenueDTO;
    competitors: CompetitorDTO[];
    details: DetailDTO[];
}

export interface VenueDTO {
    fullName: string;
}

export interface CompetitorDTO {
    homeAway: string;
    winner: string;
    score: String;
    team: TeamDTO;
    lineUp: LineupDTO;
    statistics: StatisticDTO[];
}

export interface StatusDTO {
    clock: string;
    displayClock: string;
    type: TypeDTO;
}

export interface TypeDTO {
    name: string;
    state: string;
    completed: boolean;
}

export interface LineupDTO {
    ref: String
    formation: FormationDTO;
    entries: EntryDTO[];
}

export interface FormationDTO {
    id: string;
    summary: string;
    formationClass: string;
    name: string;
    numRows: number;
}
export interface EntryDTO {
    playerId: string;
    starter: boolean;
    jersey: string;
    formationPlace: string;
}

export interface DetailDTO {
    type: TypeDeailDTO;
    clock: ClockDTO;
    team: TeamIDDTO;
    scoreValue: string;
    scoringPlay: boolean;
    redCard: boolean;
    yellowCard: boolean;
    penaltyKick: boolean;
    ownGoal: boolean;
    shootout: boolean;
    athletesInvolved: AthleteInvolvedDTO[];
}

export interface TypeDeailDTO {
    id: string;
    text: string;
}

export interface ClockDTO {
    value: string;
    displayValue: string;
}

export interface TeamIDDTO {
    id: string;
}

export interface AthleteInvolvedDTO {
    id: string;
    displayName: string;
    shortName: string;
    fullName: string;
    jersey: string;
    team: TeamIDDTO;
}

export interface StatisticDTO {
    name: string;
    abbreviation: string;
    displayValue: string;
}
