import { SeasonDTO } from './season.dto';

export interface TeamDTO {
    ref : string;
    id : string;
    guid: string;
    uid: string;
    alternateids: AlternateIds;
    slug: string;
    location: string;
    name: string;
    nickname: string;
    abbreviation: string;
    displayName: string;
    shortDisplayName: string;
    color: string;
    alternateColor: string;
    isActive: boolean;
    isAllStar: boolean;

    logo: string;

    recordRef: string;
    league: string;
    currentSeason: string;
    groups: GroupDTO;

    statistics: string;
    leadersRef: string;
    injuriesRef: string;
    notesRef: string;
    franchiseRef: string;
    eventsRef: string;
    coachesRef: string;
    seasonRef: string;
    summaryRef: string;
    isNational: boolean;
    form: string;

    defaultLeagueRef: string;

    venue: VenueDTO;

    links: LinkDTO[];

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
    images: object;
}

export interface LinkDTO {
    language: string;
    rel: string[];
    href: string;
    text: string;
    shortText: string;
    isExternal: boolean;
    isPremium: boolean;
}

export interface Address {
    city: string;
    country: string;
}
