import { BankInput } from "../../bank/model/bank-input";

export class TransactionCreate {

    date!: Date;
    amount!: number;
    type!: string;
    notes?: string;
    bankId?: number;
}
