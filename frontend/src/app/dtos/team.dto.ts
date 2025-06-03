import { SeasonDTO } from './season.dto';

export interface TeamDTO {
    ref : string;
    id : string;
    slug: string;
    location: string;
    name: string;
    nickname: string;
    abbreviation: string;
    displayName: string;
    shortDisplayName: string;
    color: string;
    alternateColor: string;

    logo: string;

    recordRef: string;
    league: string;
    currentSeason: string;
    groups: GroupDTO;

    statistics: string;
    eventsRef: string;
    form: string;

    defaultLeagueRef: string;

    venue: VenueDTO;

    competitions: string[];

}

export interface AlternateIds {
    sdr: string;
}

export interface GroupDTO {
    name: string;
    season: SeasonDTO;
    standingRef: string;
}

export interface VenueDTO {
    ref: string;
    id: string;
    fullName: string;
    shortname: string;
    address: Address;
}

export interface Address {
    city: string;
    country: string;
}
