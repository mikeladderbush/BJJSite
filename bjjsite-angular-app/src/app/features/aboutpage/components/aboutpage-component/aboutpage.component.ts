import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';

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
  selector: 'about', // Custom HTML tag for the "About" page component
  standalone: true, // Indicates that this component is standalone
  imports: [CommonModule, RouterModule], // No external modules are currently imported
  templateUrl: './aboutpage.component.html', // Path to the HTML template
  styleUrl: './aboutpage.component.css' // Path to the CSS file
})
export class AboutpageComponent implements OnInit {
  user: any;

  constructor(private http: HttpClient, private cdr: ChangeDetectorRef) { }
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
