import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

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

  register(email: string, password: string, firstname: string, lastname: string): Observable<any> {
    const body = {
      email,
      password,
      firstname,
      lastname
    };
    return this.http.post(`${this.apiUrl}/register`, body, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }
}
