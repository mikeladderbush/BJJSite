import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' }, // Redirects empty path to the login page
    { path: 'login', loadChildren: () => import('../../features/loginpage/loginpage.module').then(m => m.LoginpageModule) } // Lazy loads the login page module
];

/**
 * AppRoutingModule
 * 
 * This module defines the root routing configuration for the application. 
 * It sets up the routes and handles the navigation between different parts of the app.
 * 
 * - Redirects the base path to the login page.
 * - Lazily loads the login page module to optimize initial load time.
 */
@NgModule({
    imports: [RouterModule.forRoot(routes)], // Configures the router at the application's root level
    exports: [RouterModule] // Makes RouterModule available throughout the app
})
export class AppRoutingModule {}
