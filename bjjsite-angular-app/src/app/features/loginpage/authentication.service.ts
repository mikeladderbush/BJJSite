import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { jwtDecode } from 'jwt-decode';
import { Memberships } from '../../shared/membership.enum';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private apiUrl = 'http://localhost:8080/api/v1/auth';

  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<any> {
    const body = { email, password };
    return this.http.post(`${this.apiUrl}/authenticate`, body, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  register(email: string, password: string, firstname: string, lastname: string, membership: Memberships): Observable<any> {
    const body = {
      email,
      password,
      firstname,
      lastname,
      membership
    };
    return this.http.post(`${this.apiUrl}/register`, body, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  isTokenValid(token: string): boolean {
    try {
      const decodedToken: any = jwtDecode(token);
      console.log(decodedToken);
      const expiry = decodedToken.expiry;
      const now = Math.floor(Date.now() / 1000);
      return expiry > now;
    } catch (error) {
      return false;
    }
  }

  getEmailFromToken(token: string): string | null {
    try {
      const decodedToken: any = jwtDecode(token);
      console.log(decodedToken);
      return decodedToken.sub;
    } catch (error) {
      return null;
    }
  }

  getUserData(email: string): Observable<any> {
    const token = localStorage.getItem('authToken');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    return this.http.get(`/api/users/${email}`, { headers });
  }
}
