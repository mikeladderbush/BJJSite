import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppComponent } from './core/components/app-component/app.component';
import { HomepageComponent } from './features/homepage/homepage-component/homepage.component';
import { LoginpageComponent } from './features/loginpage/loginpage-component/loginpage.component';
import { AboutpageComponent } from './features/aboutpage/components/aboutpage-component/aboutpage.component';
import { ContactpageComponent } from './features/contactpage/contactpage-component/contactpage.component';
import { AppRoutingModule } from './core/routing/app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { SharedModule } from './shared/shared.module';
import { SidebarComponent } from './shared/components/sidebar/sidebar.component';
import { RouterModule } from '@angular/router';
import { appRoutes } from './core/routing/app.routes';
import { HttpClient, provideHttpClient, withFetch } from '@angular/common/http';

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
  declarations: [
    AppComponent,
    HomepageComponent,
    LoginpageComponent,
    AboutpageComponent,
    ContactpageComponent
  ], // Placeholder for components, directives, or pipes that belong to the root module
  imports: [
    HttpClient,
    CommonModule,
    BrowserModule,
    AppRoutingModule, // Provides common Angular directives and pipes across the application
    FormsModule,
    SharedModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
    provideHttpClient(withFetch())
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
