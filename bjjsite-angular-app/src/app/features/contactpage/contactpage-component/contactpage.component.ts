import { Component } from '@angular/core';

/**
 * ContactpageComponent
 * 
 * This component represents the "Contact" page of the application.
 * 
 * - The `templateUrl` points to the HTML file that defines the component's view.
 * - The `styleUrl` points to the CSS file that defines the component's styles.
 * - The `selector` allows this component to be used as `<app-contactpage>` in the application's HTML.
 */
@Component({
  selector: 'app-contactpage', // Custom HTML tag for the "Contact" page component
  standalone: true, // Indicates that this component is standalone
  imports: [], // No external modules are currently imported
  templateUrl: './contactpage.component.html', // Path to the HTML template
  styleUrl: './contactpage.component.css' // Path to the CSS file
})
export class ContactpageComponent {
  // The class is currently empty, but it can be expanded to include component logic
}
