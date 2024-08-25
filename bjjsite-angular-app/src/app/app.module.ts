import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

/**
 * AppModule
 * 
 * This is the root module of the application. It provides a central point to configure 
 * and import other modules and services that the application depends on.
 * 
 * - The `CommonModule` is imported to provide Angular's common directives and pipes throughout the application.
 * - The `declarations` array is currently empty but will eventually hold components, directives, or pipes that are part of the main application.
 */
@NgModule({
  declarations: [], // Placeholder for components, directives, or pipes that belong to the root module
  imports: [
    CommonModule // Provides common Angular directives and pipes across the application
  ]
})
export class AppModule { }
