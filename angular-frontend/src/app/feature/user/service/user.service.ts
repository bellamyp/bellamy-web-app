import { Injectable } from '@angular/core';
import { BackendConfig } from '../../../core/config/backend-config';
import { HttpClient } from '@angular/common/http';
import { UserRegistration } from '../model/user-registration';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = BackendConfig.springApiUrl; // Base URL for the API
  private userUrl = `${this.baseUrl}/api/users`; // URL for users endpoint

  constructor(private http: HttpClient) {}

  registerUser(user: UserRegistration): Observable<UserRegistration> {
    return this.http.post<UserRegistration>(`${this.userUrl}`, user);
  }

}
