import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css']
})

export class LoginpageComponent {
  username: string = '';
  password: string = '';

  constructor() {}

  onLogin(): void {
    console.log(`Logging in with ${this.username}`);
  }
}
