import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/core/components/app-component/app.component';
import { appRoutes } from './app/core/routing/app.routes';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

/**
 * Bootstraps the Angular application.
 * 
 * This function initializes the Angular application by bootstrapping the `AppComponent` 
 * and configuring the application routes.
 * 
 * - `bootstrapApplication` is used to bootstrap the root `AppComponent`.
 * - `appRoutes` are provided to handle routing within the application.
 * - `provideHttpClient` with `withInterceptorsFromDi` can be added to configure HTTP clients 
 *   with dependency injection-based interceptors if needed.
 */
bootstrapApplication(AppComponent, {
  providers: [appRoutes] // Configures the routing for the application
}).catch((err) => console.error(err)); // Catches and logs any errors during the bootstrapping process
