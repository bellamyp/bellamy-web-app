import { Injectable } from '@angular/core';
import { BackendConfig } from '../../../core/config/backend-config';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TransactionCreate } from '../model/transaction-create';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private baseUrl = BackendConfig.springApiUrl;
  private transactionsUrl = `${this.baseUrl}/api/transactions`;
  private transactionsTypesUrl = `${this.transactionsUrl}/types`;

  constructor(
    private httpClient: HttpClient
  ) { }

  getTransactionTypes(): Observable<string[]> {
    return this.httpClient.get<string[]>(this.transactionsTypesUrl);
  }

  createTransaction(transaction: TransactionCreate): Observable<TransactionCreate> {
    return this.httpClient.post<TransactionCreate>(this.transactionsUrl, transaction);
  }
}
