import { Injectable } from '@angular/core';
import { BackendConfig } from '../../../core/config/backend-config';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BankService {

  private baseUrl = BackendConfig.springApiUrl; 
  private bankUrl = `${this.baseUrl}/api/banks`; 
  private bankTypeUrl = `${this.bankUrl}/types`;

  constructor(
    private httpClient: HttpClient
  ) { }

  getBankTypes(): Observable<string[]> {
    return this.httpClient.get<string[]>(`${this.bankTypeUrl}`);
  }
}
