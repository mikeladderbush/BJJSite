import { Routes } from '@angular/router';
import { provideRouter } from '@angular/router';
import { AppComponent } from '../components/app-component/app.component';
import { HomepageComponent } from '../../features/homepage/homepage-component/homepage.component';

/**
 * appRoutes
 * 
 * Defines the routes for the application.
 * 
 * - The base path ('') maps to the `HomepageComponent`, which serves as the landing page of the application.
 */
export const appRoutes: Routes = [
    { path: '', component: HomepageComponent } // Maps the root URL to the homepage component
];
