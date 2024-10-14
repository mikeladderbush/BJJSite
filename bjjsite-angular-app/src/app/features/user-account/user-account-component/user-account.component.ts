import { AfterViewInit, Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../loginpage/authentication.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

declare let paypal: any;

@Component({
  selector: 'app-user-account',
  templateUrl: './user-account.component.html',
  styleUrl: './user-account.component.css',
  imports: [CommonModule],
  standalone: true
})
export class UserAccountComponent implements OnInit, AfterViewInit {
  userData: any;
  constructor(private authenticationService: AuthenticationService, private router: Router) { }

  ngOnInit(): void {
    this.loadUserData();
  }

  ngAfterViewInit(): void {
    this.renderPayPalButtons();
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

  renderPayPalButtons(): void {
    if (paypal) {
      paypal.Buttons({
        style: {
          color: 'black',
          size: 'large',
          height: 25,
        },
        createOrder: (data: any, actions: any) => {
          return actions.order.create({
            purchase_units: [{
              amount: {
                value: '10.00'
              }
            }]
          });
        },

        onApprove: (data: any, actions: any) => {
          return actions.order.capture().then((details: any) => {
            alert('Transaction completed by ' + details.payer.name.given_name);
          });
        },

        onError: (err: any) => {
          console.error('PayPal error: ', err);
        }
      }).render('#paypal-button-container');
    } else {
      console.error('PayPal SDK not loaded');
    }
  }
}

