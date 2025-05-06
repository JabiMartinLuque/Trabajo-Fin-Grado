export interface TopPerformersDTO {
    id: string;
    name: string;
    abbreviation: string;
    categories: CategoryDTO[];
}

export interface CategoryDTO {
    name: string;
    displayName: string;
    shortDisplayName: string;
    abbreviation: string;
    leaders: LeaderDTO[];
}

export interface LeaderDTO {
    displayValue: string;
    shortDisplayValue: string;
    value: number;
    athleteRef: string;
    teamRef: string;
    statisticsRef: string;
}
