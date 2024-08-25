import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from '../../features/homepage/homepage-component/homepage.component';

const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' }, // Redirects empty path to the home page
    { path: 'home', component: HomepageComponent },       // Route to the homepage
    { path: '**', redirectTo: '/home'}                    // Wildcard route, must be last
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
    imports: [RouterModule.forRoot(routes)], // Configures the router at the application's root level
    exports: [RouterModule] // Makes RouterModule available throughout the app
})
export class AppRoutingModule {}
