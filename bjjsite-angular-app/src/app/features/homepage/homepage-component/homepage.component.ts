import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { SidebarComponent } from '../../../shared/components/sidebar/sidebar.component';

/**
 * HomepageComponent
 * 
 * This component represents the homepage of the application. It is responsible for
 * displaying user-related data fetched from a backend API.
 * 
 * - The component imports `HttpClientModule` for making HTTP requests and `CommonModule` 
 *   for common Angular directives and pipes.
 * - The `ChangeDetectorRef` is used to manually trigger change detection after updating 
 *   the component's state based on the HTTP response.
 */
@Component({
  selector: 'home', // Custom HTML tag for the homepage component
  standalone: true, // Indicates that this component is standalone
  imports: [HttpClientModule, CommonModule, RouterModule, SidebarComponent], // Imports necessary modules for HTTP and common Angular functionality
  templateUrl: './homepage.component.html', // Path to the HTML template
  styleUrls: ['./homepage.component.css'] // Path to the CSS file
})
export class HomepageComponent implements OnInit {
  user: any; // Holds the user data fetched from the API

  /**
   * Constructor
   * 
   * Injects the `HttpClient` service for making HTTP requests and the `ChangeDetectorRef`
   * service for manually triggering change detection.
   */
  constructor(private http: HttpClient, private cdr: ChangeDetectorRef) { }

  /**
   * ngOnInit
   * 
   * Lifecycle hook that is called after the component's view has been fully initialized.
   * It triggers the `getUser` method to fetch user data when the component is initialized.
   */
  ngOnInit(): void {
    this.getUser(1);
  }

  /**
   * getUser
   * 
   * Fetches user data from the backend API based on the provided user ID.
   * 
   * @param id - The ID of the user to fetch
   */
  getUser(id: number): void {
    this.http.get(`http://localhost:8080/api/users/${id}`)
      .subscribe({
        next: data => {
          this.user = data;
          console.log(this.user);
          this.cdr.detectChanges(); // Manually triggers change detection
        },
        error: error => {
          console.error('There was an issue getting a user', error);
        },
        complete: () => {
          console.log('Request complete');
        }
      });
  }
}
