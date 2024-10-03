import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomepageComponent } from '../features/homepage/homepage-component/homepage.component';

/**
 * CoreModule
 * 
 * This module is intended to provide core services and singleton components 
 * that are shared across the entire application.
 * 
 * - The `CommonModule` is imported to make common directives and pipes available.
 * - Declarations are empty for now, but this is where core components and services would be declared.
 */
@NgModule({
  declarations: [], // Placeholder for core components, directives, and pipes
  imports: [
    CommonModule, // Provides Angular's common directives and pipes
  ]
})
export class CoreModule { }
