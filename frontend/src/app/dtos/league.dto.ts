import { SeasonDTO } from "./season.dto";

export interface LeagueDTO {
    id: number;
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