import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

/**
 * The root component of the application.
 * Hosts the main layout and uses RouterOutlet for routing.
 */
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'bjjsite-angular-app'; // Application title
}
