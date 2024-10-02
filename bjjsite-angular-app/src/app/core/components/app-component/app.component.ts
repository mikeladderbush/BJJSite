import { Component } from '@angular/core';
import { Router, RouterOutlet, NavigationEnd } from '@angular/router';
import { SidebarComponent } from '../../../shared/components/sidebar/sidebar.component';
import { HomepageComponent } from '../../../features/homepage/homepage-component/homepage.component';
import { ContactpageComponent } from '../../../features/contactpage/contactpage-component/contactpage.component';
import { AboutpageComponent } from '../../../features/aboutpage/aboutpage-component/aboutpage.component';

/**
 * The root component of the application.
 * Hosts the main layout and uses RouterOutlet for routing.
 */
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SidebarComponent, HomepageComponent, AboutpageComponent, ContactpageComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'bjjsite-angular-app'; // Application title
  isLoginPage = false;

  constructor(private router: Router) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.isLoginPage = this.router.url === '/login';
      }
    });
  }
}
