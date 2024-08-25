import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/core/components/app-component/app.component';
import { provideRouter } from '@angular/router';  // Import provideRouter
import { appRoutes } from './app/core/routing/app.routes';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

/**
 * Bootstraps the Angular application.
 * 
 * This function initializes the Angular application by bootstrapping the `AppComponent` 
 * and configuring the application routes.
 * 
 * - `bootstrapApplication` is used to bootstrap the root `AppComponent`.
 * - `provideRouter` is used to provide the application routes.
 * - `provideHttpClient` with `withInterceptorsFromDi` can be added to configure HTTP clients 
 *   with dependency injection-based interceptors if needed.
 */
bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(appRoutes),  // Correct way to provide routing
    provideHttpClient(withInterceptorsFromDi())  // Configures HTTP client with interceptors
  ]
}).catch((err) => console.error(err)); // Catches and logs any errors during the bootstrapping process
