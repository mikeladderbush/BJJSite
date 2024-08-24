import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginpageComponent } from './loginpage-component/loginpage.component';

/**
 * LoginpageRoutingModule
 * 
 * This module defines the routing configuration for the login page feature.
 * 
 * - The `routes` array contains a single route that maps the root path ('') to the `LoginpageComponent`.
 * - The module imports `RouterModule.forChild(routes)` to configure the router at the feature level and exports the `RouterModule` for use in the `LoginpageModule`.
 */
const routes: Routes = [
    { path: '', component: LoginpageComponent } // Maps the root URL to the LoginpageComponent
];

@NgModule({
    imports: [RouterModule.forChild(routes)], // Configures the router for this feature module
    exports: [RouterModule] // Exports RouterModule for use in the LoginpageModule
})
export class LoginpageRoutingModule {}
