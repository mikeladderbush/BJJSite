import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SidebarComponent } from '../../../shared/components/sidebar/sidebar.component';
import { AuthenticationService } from '../authentication.service';

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
    lastname: ''
  }

  constructor(private authenticationService: AuthenticationService) { }

  onSubmit() {
    if (this.loginData.email && this.loginData.password) {
      this.authenticationService.login(this.loginData.email, this.loginData.password).subscribe(
        (response) => {
          console.log('Login successful', response);
          const token = response.token;
          localStorage.setItem('authToken', token);
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
        this.registrationData.lastname
      ).subscribe((response) => {
        console.log('Registration successful', response);
      },
        (error) => {
          console.error('Registration failed', error);
        }
      );
    }

  }
}
