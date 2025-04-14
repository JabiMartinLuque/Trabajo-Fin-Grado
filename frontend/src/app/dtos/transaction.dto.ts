import { AthleteDTO } from "./athlete.dto";
import { TeamDTO } from "./team.dto";

export interface TransactionDTO {
    id: string;
    date: string;
    athlete: AthleteDTO;
    from: TeamDTO;
    to: TeamDTO;
    type: string;
    amount: number;
    displayAmount: string;
}