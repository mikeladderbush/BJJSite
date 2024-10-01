import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutpageComponent } from '../../features/aboutpage/aboutpage-component/aboutpage.component';
import { ContactpageComponent } from '../../features/contactpage/contactpage.module';
import { HomepageComponent } from '../../features/homepage/homepage-component/homepage.component';
import { LoginpageComponent } from '../../features/loginpage/loginpage-component/loginpage.component';


const routes: Routes = [
    { path: '', component: HomepageComponent }, // Default route
    { path: 'home', component: HomepageComponent }, // Home page
    { path: 'about', component: AboutpageComponent }, // About page
    { path: 'contact', component: ContactpageComponent }, // Contact page
    { path: 'login', component: LoginpageComponent }, // Login page
];

/**
 * AppRoutingModule
 * 
 * This module defines the root routing configuration for the application. 
 * It sets up the routes and handles the navigation between different parts of the app.
 * 
 * - Redirects the base path to the home page.
 * - Provides a wildcard route to handle undefined paths.
 */
@NgModule({
    imports: [RouterModule.forRoot(routes, {
        anchorScrolling: 'enabled',
        scrollPositionRestoration: 'enabled',
    })], // Configures the router at the application's root level
    exports: [RouterModule] // Makes RouterModule available throughout the app
})
export class AppRoutingModule {}
