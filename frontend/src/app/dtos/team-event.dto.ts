import { TeamDTO } from "./team.dto";

export interface TeamEventDTO {
    ref: string;
    id: string;
    uid: string;
    date: string;
    name: string;
    shortName: string;
    season: Reference;
    seasonType: Reference;
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
    guid: string;
    uid: string;
    date: string;
    attendance: number;
    necessary: boolean;
    timeValid: boolean;
    neutralSite: boolean;
    divisionCompetition: boolean;
    conferenceCompetition: boolean;
    previewAvailable: boolean;
    recapAvailable: boolean;
    boxscoreAvailable: boolean;
    lineupAvailable: boolean;
    gamecastAvailable: boolean;
    playByPlayAvailable: boolean;
    commentaryAvailable: boolean;
    pickcenterAvailable: boolean;
    summaryAvailable: boolean;
    liveAvailable: boolean;
    tickersAvailable: boolean;
    shortChartAvailable: boolean;
    timeoutAvailable: boolean;
    possessionAvailable: boolean;
    onWatchESPN: boolean;
    recent: boolean;
    bracketAvailable: boolean;
    wallclockAvailable: boolean;
    boxscoreSource: SourceDTO;
    playByPlaySource: SourceDTO;
    venue: VenueDTO;
    competitors: CompetitorDTO[];
    notes: Object[];
    situation: Reference;
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
    linescores: Reference;
    statistics: Reference;
    leaders: Reference;
    record: Reference;
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