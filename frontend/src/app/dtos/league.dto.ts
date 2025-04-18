import { SeasonDTO } from "./season.dto";

export interface LeagueDTO {
    id: number;
    guid: string;
    uid: string;
    alternateId: string;
    name: string;
    displayName: string;
    abbreviation: string;
    shortName: string;
    midsizeName: string;
    slug: string;
    isTournament: boolean;
    country: CountryDTO;
    season: SeasonDTO;
    logos: LogoDTO[];
    links: LinkDTO[];
    gender: string;    
}

export interface CountryDTO {
    id: number;
    slug: string;
    name: string;
    abbreviation: string;
    flag: FlagDTO;
}

export interface FlagDTO {
    href: string;
    alt: string;
    rel: string[];
}

export interface LogoDTO {
    href: string;
    width: number;
    height: number;
    alt: string;
    rel: string[];
    lastUpdated: string;

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