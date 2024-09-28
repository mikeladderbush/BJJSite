import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AboutpageComponent } from './aboutpage-component/aboutpage.component';
import { SharedModule } from '../../shared/shared.module';

/**
 * AboutpageModule
 * 
 * This module is dedicated to the "About" page of the application.
 * 
 * - The `CommonModule` is imported to provide Angular's common directives and pipes within this module.
 * - The `declarations` array includes the `AboutpageComponent`, which is the main component for this module.
 */
const routes: Routes = [
];

@NgModule({
  declarations: [AboutpageComponent], // Correct component declaration
  imports: [
    CommonModule,
    SharedModule,
    RouterModule.forChild(routes) // Routing for the "About" page
  ]
})
export class AboutpageModule { }
