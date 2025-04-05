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
}

export interface VenueDTO {
    displayName: string;
}

export interface CompetitorDTO {
    homeAway: string;
    winner: string;
    score: String;
    team: TeamDTO;
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
