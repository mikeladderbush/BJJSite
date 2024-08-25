import { Component } from '@angular/core';

/**
 * AboutpageComponent
 * 
 * This component represents the "About" page of the application.
 * 
 * - The `templateUrl` points to the HTML file defining the component's view.
 * - The `styleUrl` points to the CSS file defining the component's styles.
 * - The `selector` allows this component to be used as `<app-aboutpage>` in the application's HTML.
 */
@Component({
  selector: 'app-aboutpage', // Custom HTML tag for the "About" page component
  standalone: true, // Indicates that this component is standalone
  imports: [], // No external modules are currently imported
  templateUrl: './aboutpage.component.html', // Path to the HTML template
  styleUrl: './aboutpage.component.css' // Path to the CSS file
})
export class AboutpageComponent {
}
