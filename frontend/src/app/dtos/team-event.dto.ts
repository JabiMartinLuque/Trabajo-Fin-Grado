import { TeamDTO } from "./team.dto";

export interface TeamEventDTO {
    ref: string;
    id: string;
    date: string;
    name: string;
    shortName: string;
    timeValid: boolean;
    competitions: CompetitionDTO[];
    venues: Reference[];
    league: Reference;
}

export interface Reference {
    ref: string; 
}

export interface CompetitionDTO {
    ref: string;
    id: string;
    date: string;
    boxscoreAvailable: boolean;
    lineupAvailable: boolean;
    boxscoreSource: SourceDTO;
    playByPlaySource: SourceDTO;
    venue: VenueDTO;
    competitors: CompetitorDTO[];
    notes: Object[];
}

export interface SourceDTO {
    id: string;
    description: string;
    state: string;
}

export interface VenueDTO {
    ref: string;
    id: string;
    fullName: string;
    shortName: string;
    address: AddressDTO;
    images: Object[];
}

export interface AddressDTO {
    city: string;
    country: string;
}

export interface CompetitorDTO {
    ref: string;
    id: string;
    uid: string;
    type: string;
    order: number;
    homeAway: string;
    winner: boolean;
    team: TeamDTO;
    score: ScoreDTO;
    uniform: UniformDTO;
}

export interface UniformDTO {
    type: string;
    color: string;
    alternateColor: string;
}

export interface FormatDTO {
    regulation: RegulationDTO;
}

export interface RegulationDTO {
    periods: number;
    displayName: string;
    slug: string;
    clock: number;
}

export interface ScoreDTO {
    value: number;
}