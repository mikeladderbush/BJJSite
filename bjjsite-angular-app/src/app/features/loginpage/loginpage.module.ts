import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginpageComponent } from './loginpage-component/loginpage.component';
import { LoginpageRoutingModule } from './loginpage-routing.module';
import { FormsModule } from '@angular/forms';
import { SharedModule } from '../../shared/shared.module';

/**
 * LoginpageModule
 * 
 * This module encapsulates the functionality related to the login page feature.
 * 
 * - The `LoginpageComponent` is declared here to be part of this module.
 * - The `CommonModule` is imported to provide Angular's common directives and pipes.
 * - The `LoginpageRoutingModule` is imported to handle routing specific to the login page.
 */
@NgModule({
  declarations: [LoginpageComponent], // Declares the LoginpageComponent as part of this module
  imports: [
    CommonModule, // Provides common Angular directives and pipes
    LoginpageRoutingModule, // Handles routing for the login page feature
    FormsModule,
    SharedModule
  ]
})
export class LoginpageModule { }
