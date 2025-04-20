export interface StatisticsDTO {
    teamRef: string;
    seasonRef: string;

    splits: SplitDTO;
}

export interface SplitDTO {
    id: string;
    name: string;

    categories: CategoryDTO[];
}

export interface CategoryDTO {
    name: string;
    displayName: string;
    shortDisplayName: string;
    abbreviation: string;
    stats: StatDTO[];
}

export interface StatDTO {
    name: string;
    displayName: string;
    shortDisplayName: string;
    description: string;
    abbreviation: string;
    value: number;
    displayValue: string;
}