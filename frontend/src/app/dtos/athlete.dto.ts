export interface AthleteDTO {
    id: string; 
    uid: string; 
    type: string; 
    guid: string;
    firstName: string;
    middleName: string;
    lastName: string;
    fullName: string;
    displayName: string;
    shortName: string;
    weight: number;
    displayWeight: string;
    height: number;
    displayHeight: string;
    age: number;
    dateOfBirth: string;
    gender: string;
    citizenship: string;
    slug: string;
    jersey: string;
    active: boolean;
    profiled: boolean;

    citizenshipCountry: CitizenshipCountryDTO;
    flag: FlagDTO;
    position: PositionDTO;
    links: LinkDTO[];

    teamRef: string;

}


export interface CitizenshipCountryDTO {
    alternateId: string;
    abbreviation: string;
}    

export interface FlagDTO {
    href: string;
    alt: string;
    rel: string[];
}

export interface PositionDTO {
    ref: string;
    id: string;
    name: string;
    displayName: string;
    abbreviation: string;
    shortName: string;
    leaf: boolean;
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