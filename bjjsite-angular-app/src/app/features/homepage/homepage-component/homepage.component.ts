import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { SidebarComponent } from '../../../shared/components/sidebar/sidebar.component';
import { AdminAccountComponent } from '../../../admin/admin_features/admin-account/admin-account.component';
import { SessionService } from '../../../admin/session.service';

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
export class HomepageComponent {

  sessionData: { dayOfWeek: string; startTime: string; endTime: string; typeOfSession: string }[] = [];
  loggedIn: boolean = false;
  constructor(private http: HttpClient, private cdr: ChangeDetectorRef, private sessionService: SessionService) { }

  ngOnInit(): void {
    const token = localStorage.getItem('authToken');
    if (token) {
      this.loggedIn = true;
    }

    this.loadSessionData();
  }

  loadSessionData(): void {
    this.sessionService.getSessionData().subscribe(
      (response) => {
        this.sessionData = Array.isArray(response) ? response : [];
        console.log(this.sessionData);
      },
      (error) => {
        console.error('Failed to load session data', error);
      }
    );
  }

  if(loggedIn = true) {
    //add user hello at top right later
  }


}
