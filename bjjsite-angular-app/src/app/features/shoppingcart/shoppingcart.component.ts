import { CommonModule } from '@angular/common';
import { AfterViewInit, Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Memberships } from '../../shared/membership.enum';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { jwtDecode } from 'jwt-decode';

declare let paypal: any;

@Component({
  selector: 'app-shoppingcart',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './shoppingcart.component.html',
  styleUrls: ['./shoppingcart.component.css']
})
export class ShoppingcartComponent implements AfterViewInit {

  Memberships = Memberships; // Make Memberships available to the template
  selectedOption: Memberships = Memberships.NONE;

  constructor(private http: HttpClient) { }

  ngAfterViewInit(): void {
    this.renderPayPalButtons();
  }

  renderPayPalButtons(): void {
    if (paypal) {
      paypal.Buttons({
        style: {
          color: 'black',
          size: 'large',
          height: 25,
        },
        createSubscription: (data: any, actions: any) => {
          const planId = this.getPlanIdForMembership(this.selectedOption);
          if (!planId) {
            alert('Please select a valid membership type.');
            return Promise.reject('Invalid membership selection');
          }
          return actions.subscription.create({ plan_id: planId });
        },
        onApprove: (data: any, actions: any) => {
          actions.order.capture().then(() => {
            this.updateUserMembership();
          });
        },
        onError: (err: any) => {
          console.error('PayPal error:', err);
          alert('Payment failed. Please try again.');
        }
      }).render('#paypal-button-container');
    } else {
      console.error('PayPal SDK not loaded');
    }
  }

  getPlanIdForMembership(membership: Memberships): string | null {
    switch (membership) {
      case Memberships.THREEDAYS:
        return 'P-5VS82014YS568633XM42UIRA'; // Three Days Membership Plan
      case Memberships.FULL:
        return 'P-2LB17374MY217825TM42UEEA'; // Full Membership Plan
      default:
        console.error('Invalid membership type selected');
        return null;
    }
  }

  updateUserMembership(): void {
    const token = localStorage.getItem('authToken');
    if (!token) {
      console.error('User is not authenticated');
      return;
    }

    const decodedToken: any = jwtDecode(token);
    const email = decodedToken.sub; // Assuming 'sub' contains the email

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    const userDto = {
      email: email,
      membership: this.selectedOption // Update with selected membership
    };

    this.http.put(`/api/users/${email}`, userDto, { headers })
      .subscribe({
        next: () => {
          alert('Membership updated successfully');
        },
        error: (err) => {
          console.error('Failed to update membership', err);
        }
      });
  }
}
