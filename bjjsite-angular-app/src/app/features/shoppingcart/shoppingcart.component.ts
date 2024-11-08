import { CommonModule } from '@angular/common';
import { AfterViewInit, Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

declare let paypal: any;

@Component({
  selector: 'app-shoppingcart',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './shoppingcart.component.html',
  styleUrl: './shoppingcart.component.css'
})
export class ShoppingcartComponent implements AfterViewInit {

  selectedOption: number = 0;

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
        createOrder: (data: any, actions: any) => {
          return actions.order.create({
            purchase_units: [{
              amount: {
                value: this.selectedOption
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
