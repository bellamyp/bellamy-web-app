import { computed, Injectable, signal } from '@angular/core';
import { BackendConfig } from '../config/backend-config';
import { jwtDecode } from 'jwt-decode';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { JwtPayload } from '../models/jwt-payload';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private baseUrl = BackendConfig.springApiUrl + '/api/auth/login';
  private _username = signal<string | null>(null);
  readonly username = computed(() => this._username());

  constructor(private http: HttpClient) {
    this.loadUserFromToken();
  }

  getToken(): string | null {
    return localStorage.getItem('jwt_token');
  }

  storeToken(token: string): void {
    localStorage.setItem('jwt_token', token);
    this.loadUserFromToken();
  }

  login(username: string, password: string): Observable<{ token: string }> {
    return this.http.post<{ token: string }>(
      this.baseUrl,
      { username, password },
      { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) }
    );
  }

  // Clear token and reset signal
  logout(): void {
    localStorage.removeItem('jwt_token');
    this._username.set(null);
  }

  // Decode token and update username signal
  private loadUserFromToken(): void {
    const token = this.getToken();
    if (token) {
      try {
        const decodedToken: JwtPayload = jwtDecode<JwtPayload>(token);
        const isExpired = decodedToken.exp * 1000 < Date.now();
        if (isExpired) {
          this.logout();
        } else {
          this._username.set(decodedToken.sub ?? null);
        }
      } catch (err) {
        console.error('Invalid token:', err);
        this._username.set(null);
      }
    } else {
      this._username.set(null);
    }
  }
}
