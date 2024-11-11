import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SidebarComponent } from '../../shared/components/sidebar/sidebar.component';
import { Router } from '@angular/router';
import { AuthenticationService } from '../loginpage/authentication.service';
import { Memberships } from '../../shared/membership.enum';

declare let paypal: any;

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
    membership: Memberships.NONE as Memberships
  }

  constructor(private authenticationService: AuthenticationService, private router: Router) { }

  onRegister() {
    if (this.registrationData.email && this.registrationData.password) {
      if (this.registrationData.membership === Memberships.THREEDAYS || this.registrationData.membership === Memberships.FULL) {
        this.initiatePayPalPayment();
      } else {
        this.registerUser();
      }
    }
  }

  initiatePayPalPayment() {
    paypal.Buttons({
      createSubscription: (data: any, actions: any) => {
        return actions.subscription.create({
          plan_id: this.getPlanIdForMembership(this.registrationData.membership)
        });
      },
      onApprove: (data: any, actions: any) => {
        this.registerUser();
      },
      onError: (err: any) => {
        console.error('PayPal payment failed', err);
        alert('Payment failed. Please try again.');
      }
    }).render(`#paypal-button-container`);
  }

  getPlanIdForMembership(membership: Memberships): string {
    switch (membership) {
      case Memberships.THREEDAYS:
        return 'YOUR_THREEDAYS_PLAN_ID';
      case Memberships.FULL:
        return 'YOUR_FULL_PLAN_ID';
      default:
        throw new Error('Invalid subscription type');
    }
  }

  registerUser() {
    this.authenticationService.register(
      this.registrationData.email,
      this.registrationData.password,
      this.registrationData.firstname,
      this.registrationData.lastname,
      this.registrationData.membership
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
