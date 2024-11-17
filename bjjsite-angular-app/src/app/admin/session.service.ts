import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private apiUrl = 'http://localhost:8080/api/sessions';

  constructor(private http: HttpClient) { }

  addSession(day: string, startTime: string, endTime: string, type: string): Observable<any> {
    const session = { day, startTime, endTime, type };
    const token = localStorage.getItem('authToken');

    return this.http.post(`${this.apiUrl}/addSession`, session, {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      })
    });
  }
}
