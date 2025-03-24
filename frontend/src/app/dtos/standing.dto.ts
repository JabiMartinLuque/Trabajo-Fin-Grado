// src/app/dtos/standing.dto.ts

import { TeamDTO } from './team.dto'; // Asegúrate de tener definido TeamDTO

export interface StandingDTO {
  team: TeamDTO;
  records: RecordDTO[];
}

export interface RecordDTO {
  id: string;
  name: string;
  abbreviation: string;
  displayName: string;
  shortDisplayName: string;
  summary: string;
  displayValue: string;
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
