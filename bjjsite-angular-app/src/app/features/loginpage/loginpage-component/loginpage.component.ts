import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SidebarComponent } from '../../../shared/components/sidebar/sidebar.component';

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

  onSubmit() {
    if (this.loginData.email && this.loginData.password) {
      // Here you would typically handle the login logic, e.g., calling a service.
      console.log('Login successful', this.loginData);
    }
  }
}
