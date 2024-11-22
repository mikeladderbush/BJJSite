import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export enum DayOfWeek {
  SUNDAY = 0,
  MONDAY = 1,
  TUESDAY = 2,
  WEDNESDAY = 3,
  THURSDAY = 4,
  FRIDAY = 5,
  SATURDAY = 6
}

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private apiUrl = 'http://localhost:8080/api/sessions';

  constructor(private http: HttpClient) { }

  addSession(day: DayOfWeek, startTime: string, endTime: string, type: string): Observable<any> {
    const session = { dayOfWeek: day, startTime, endTime, typeOfSession: type };
    const token = localStorage.getItem('authToken');

    console.log(session);

    return this.http.post(`${this.apiUrl}/addSession`, session, {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      })
    });
  }

  getSessionData(): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.get('http://localhost:8080/api/sessions/all', { headers });
  } 
}
