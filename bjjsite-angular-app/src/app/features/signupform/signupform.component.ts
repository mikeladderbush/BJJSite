import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SidebarComponent } from '../../shared/components/sidebar/sidebar.component';
import { Router } from '@angular/router';
import { AuthenticationService } from '../loginpage/authentication.service';
import { Memberships } from '../../shared/membership.enum';

@Component({
  selector: 'signupform',
  standalone: true,
  imports: [FormsModule, CommonModule, SidebarComponent],
  templateUrl: './signupform.component.html',
  styleUrls: ['./signupform.component.css']
})
export class SignupformComponent {

  Memberships = Memberships;

  registrationData = {
    email: '',
    password: '',
    firstname: '',
    lastname: '',
    membership: Memberships.NONE as Memberships // Default to NONE
  }

  constructor(private authenticationService: AuthenticationService, private router: Router) { }

  onRegister() {
    if (this.registrationData.email && this.registrationData.password) {
      this.registerUser();
    }
  }

  registerUser() {
    this.authenticationService.register(
      this.registrationData.email,
      this.registrationData.password,
      this.registrationData.firstname,
      this.registrationData.lastname,
      this.registrationData.membership
    ).subscribe(
      (response) => {
        console.log('Registration successful', response);
        this.router.navigate(['/user-account']); // Redirect to user account after registration
      },
      (error) => {
        console.error('Registration failed', error);
      }
    );
  }
}
