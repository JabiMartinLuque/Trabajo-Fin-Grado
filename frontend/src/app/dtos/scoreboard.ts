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
