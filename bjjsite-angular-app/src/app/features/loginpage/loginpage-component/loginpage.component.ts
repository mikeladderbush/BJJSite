import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SidebarComponent } from '../../../shared/components/sidebar/sidebar.component';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-login',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css'],
  imports: [FormsModule, CommonModule, SidebarComponent],
  standalone: true
})
export class LoginpageComponent {
  loginData = {
    email: '',
    password: ''
  };

  loggedIn: boolean = false;

  constructor(private authenticationService: AuthenticationService, private router: Router) { }

  ngOnInit(): void {
    const token = localStorage.getItem('authToken');
    if (token) {
      if (this.authenticationService.isTokenValid(token)) {
        this.redirectBasedOnRole(token);
      }
    }
  }

  onSubmit(): void {
    if (this.loginData.email && this.loginData.password) {
      this.authenticationService.login(this.loginData.email, this.loginData.password).subscribe(
        (response) => {
          const token = response.token;
          localStorage.setItem('authToken', token);
          this.redirectBasedOnRole(token);
        },
        (error) => {
          console.error('Login failed', error);
        }
      );
    }
  }

  private redirectBasedOnRole(token: string): void {
    try {
      const decodedToken: any = jwtDecode(token);
      const roles: string[] = decodedToken.roles || [];
      if (roles.includes('ROLE_ADMIN')) {
        this.router.navigate(['/admin-account']);
      } else {
        this.router.navigate(['/user-account']);
      }
    } catch (error) {
      console.error('Failed to retrieve roles', error);
    }
  }
}



