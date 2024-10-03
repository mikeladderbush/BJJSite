import { Component, OnInit } from '@angular/core';
import { ViewportScroller } from '@angular/common';
import { Router, RouterOutlet, NavigationEnd } from '@angular/router';
import { SidebarComponent } from '../../../shared/components/sidebar/sidebar.component';
import { HomepageComponent } from '../../../features/homepage/homepage-component/homepage.component';

/**
 * The root component of the application.
 * Hosts the main layout and uses RouterOutlet for routing.
 */
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SidebarComponent, HomepageComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent{
  title = 'bjjsite-angular-app'; // Application title
  isLoginPage = false;

}

