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
  sessionData = { day: 0, startTime: '', endTime: '', type: '' }

  constructor(private authenticationService: AuthenticationService, private sessionService: SessionService, private router: Router) { }

  addSession(): void {
    if (this.sessionData.day === null || this.sessionData.day === undefined) {
      alert('Please select a day.');
      return;
    }

    if (!this.sessionData.startTime) {
      alert('Please fill in the start time');
      return;
    }

    if (!this.sessionData.endTime) {
      alert('Please fill in the end time');
      return;
    }

    if (!this.sessionData.type) {
      alert('Please fill in the type of session');
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
