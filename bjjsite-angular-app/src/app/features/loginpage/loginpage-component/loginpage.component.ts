import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SidebarComponent } from '../../../shared/components/sidebar/sidebar.component';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';

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

  registrationData = {
    email: '',
    password: '',
    firstname: '',
    lastname: '',
  }

  loggedIn: boolean = false;

  constructor(private authenticationService: AuthenticationService, private router: Router) { }

  ngOnInit(): void {
    const token = localStorage.getItem('authToken');
    if (token) {
      const email = this.authenticationService.getEmailFromToken(token);
      if (email) {
        const userData = this.authenticationService.getUserData(email).subscribe(
          (userData) => {
            if (userData) {
              this.router.navigate(['/user-account']);
            }
          },
          (error) => {
            console.error('Failed to retrieve user data', error);
          }
        );
      }
    }
  }

  onSubmit() {
    if (this.loginData.email && this.loginData.password) {
      this.authenticationService.login(this.loginData.email, this.loginData.password).subscribe(
        (response) => {
          console.log('Login successful', response);
          const token = response.token;
          localStorage.setItem('authToken', token);

          this.router.navigate(['/user-account']);
        },
        (error) => {
          console.error('Login failed', error);
        }
      );
    }
  }

  onRegister() {
    if (this.registrationData.email && this.registrationData.password) {
      this.authenticationService.register(
        this.registrationData.email,
        this.registrationData.password,
        this.registrationData.firstname,
        this.registrationData.lastname,
      ).subscribe((response) => {
        console.log('Registration successful', response);

        this.router.navigate(['/user-account']);

      },
        (error) => {
          console.error('Registration failed', error);
        }
      );
    }

  }
}
