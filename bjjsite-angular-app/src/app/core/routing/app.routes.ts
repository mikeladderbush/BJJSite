import { Routes } from '@angular/router';
import { provideRouter } from '@angular/router';
import { AppComponent } from '../components/app-component/app.component';
import { HomepageComponent } from '../../features/homepage/homepage-component/homepage.component';
import { AboutpageComponent } from '../../features/aboutpage/aboutpage-component/aboutpage.component';
import { ContactpageComponent } from '../../features/contactpage/contactpage-component/contactpage.component';
import { LoginpageComponent } from '../../features/loginpage/loginpage-component/loginpage.component';

/**
 * appRoutes
 * 
 * Defines the routes for the application.
 * 
 * - The base path ('') maps to the `HomepageComponent`, which serves as the landing page of the application.
 */
export const appRoutes: Routes = [
    { path: '', component: HomepageComponent }, // Maps the root URL to the homepage component
    { path: 'home', component: HomepageComponent },
    { path: 'about', component: AboutpageComponent },
    { path: 'contact', component: ContactpageComponent },
    { path: 'login', component: LoginpageComponent}
];
