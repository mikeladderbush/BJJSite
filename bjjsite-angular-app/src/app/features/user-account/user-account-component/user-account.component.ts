import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../loginpage/authentication.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-user-account',
  templateUrl: './user-account.component.html',
  styleUrl: './user-account.component.css',
  imports: [CommonModule],
  standalone: true
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

  routeToCart(): void {
    this.router.navigate(['/shoppingcart']);
  }

  logout(): void {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}

