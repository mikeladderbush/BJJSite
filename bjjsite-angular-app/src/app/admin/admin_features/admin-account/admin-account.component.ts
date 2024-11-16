import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../../features/loginpage/authentication.service';

@Component({
  selector: 'app-admin-account',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './admin-account.component.html',
  styleUrl: './admin-account.component.css'
})
export class AdminAccountComponent implements OnInit {
  userData: any;
  selectedDay: string = '';
  timeRange: string = '';
  calendar: Record<string, string> = {
    Sunday: '',
    Monday: '',
    Tuesday: '',
    Wednesday: '',
    Thursday: '',
    Friday: '',
    Saturday: '',
  };

  constructor(private authenticationService: AuthenticationService, private router: Router) { }

  ngOnInit(): void {
    this.loadUserData();
  }

  loadUserData(): void {
    const token = localStorage.getItem('authToken');
    if (token) {
      const email = this.authenticationService.getEmailFromToken(token);
      if (email) {
        this.authenticationService.getUserData(email).subscribe(
          (response) => {
            this.userData = typeof response === 'string' ? JSON.parse(response) : response;
            console.log(this.userData);
          },
          (error) => {
            console.error('Failed to load user data', error);
          }
        );
      } else {
        this.router.navigate(['/login']);
      }
    } else {
      this.router.navigate(['/login']);
    }
  }

  addClassToCalendar(): void {
    //TODO, ADD CLASSES TO DATABASE AND THEN GET FROM THERE
    if (!this.selectedDay || !this.timeRange) {
      alert('Please select a day AND a time range');
      return;
    }

    if (this.calendar[this.selectedDay]) {
      const overwrite = confirm(
        'There is already a class scheduled for this time, do you want to overwrite it?'
      );
      if (!overwrite) {
        return;
      }
    }
    this.calendar[this.selectedDay] = this.timeRange;
    alert('The class has been added');
  }

  logout(): void {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
