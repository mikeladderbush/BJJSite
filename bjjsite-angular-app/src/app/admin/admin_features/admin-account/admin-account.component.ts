import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Event, Router } from '@angular/router';
import { AuthenticationService } from '../../../features/loginpage/authentication.service';
import { FormsModule } from '@angular/forms';
import { SessionService } from '../../session.service';

@Component({
  selector: 'app-admin-account',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-account.component.html',
  styleUrl: './admin-account.component.css'
})
export class AdminAccountComponent {
  sessionData = { day: '', startTime: '', endTime: '', type: '' }

  constructor(private authenticationService: AuthenticationService, private sessionService: SessionService, private router: Router) { }

  addClassToCalendar(event: Event): void {
    if (!this.sessionData.day || !this.sessionData.startTime || !this.sessionData.endTime) {
      alert('Please fill all required fields');
      return;
    }

    this.sessionService.addSession(
      this.sessionData.day,
      this.sessionData.startTime,
      this.sessionData.endTime,
      this.sessionData.type
    ).subscribe(
      (response) => {
        console.log('Session added successfully', response);
      },
      (error) => {
        console.error('Session could not be added', error);
      }
    );

    alert('The class has been added');
  }

  logout(): void {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
