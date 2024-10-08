import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../loginpage/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-account',
  templateUrl: './user-account.component.html',
  styleUrl: './user-account.component.css'
})
export class UserAccountComponent implements OnInit {
  userData: any;

  constructor(private authenticationService: AuthenticationService, private router: Router) { }

  ngOnInit(): void {
    this.loadUserData();
  }

  loadUserData(): void {
    const token = localStorage.getItem('authToken');
    if (token) {
      const email = this.authenticationService.getEmailFromToken(token);
      console.log(email);

      if (email) {
        this.authenticationService.getUserData(email).subscribe(
          (response) => {
            this.userData = response;
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
}
