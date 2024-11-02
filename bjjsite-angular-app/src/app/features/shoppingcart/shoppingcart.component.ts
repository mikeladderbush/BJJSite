import { AfterViewInit, Component } from '@angular/core';

declare let paypal: any;

@Component({
  selector: 'app-shoppingcart',
  standalone: true,
  imports: [],
  templateUrl: './shoppingcart.component.html',
  styleUrl: './shoppingcart.component.css'
})
export class ShoppingcartComponent implements AfterViewInit {

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
