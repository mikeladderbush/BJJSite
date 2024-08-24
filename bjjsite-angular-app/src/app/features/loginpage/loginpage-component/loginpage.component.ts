import { Component, OnInit } from '@angular/core';

/**
 * LoginpageComponent
 * 
 * This component represents the login page of the application.
 * 
 * - The component manages the user's login credentials, including `username` and `password`.
 * - The `onLogin` method is triggered when the user attempts to log in, currently logging the username to the console.
 */
@Component({
  selector: 'app-loginpage', // Custom HTML tag for the login page component
  templateUrl: './loginpage.component.html', // Path to the HTML template
  styleUrls: ['./loginpage.component.css'] // Path to the CSS file
})
export class LoginpageComponent {
  username: string = ''; // Holds the entered username
  password: string = ''; // Holds the entered password

  constructor() {}

  /**
   * onLogin
   * 
   * Method to handle the login action.
   * Currently logs the entered username to the console.
   */
  onLogin(): void {
    console.log(`Logging in with ${this.username}`);
  }
}
