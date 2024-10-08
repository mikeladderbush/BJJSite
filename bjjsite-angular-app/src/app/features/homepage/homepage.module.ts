import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomepageComponent } from './homepage-component/homepage.component';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../shared/shared.module';

/**
 * HomepageModule
 * 
 * This module is dedicated to the homepage feature of the application.
 * 
 * - The `CommonModule` is imported to provide Angular's common directives and pipes within this module.
 * - The `declarations` array is currently empty but will hold components, directives, or pipes specific to the homepage.
 */
const routes: Routes = [
]
@NgModule({
  declarations: [HomepageComponent], // Placeholder for components, directives, or pipes related to the homepage
  imports: [
    RouterModule,
    SharedModule
  ]
})
export class HomepageModule { }
