import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/core/components/app-component/app.component';
import { config } from './app/core/config/app.config.server';

/**
 * Bootstraps the Angular application.
 * 
 * This function initializes the Angular application by bootstrapping the `AppComponent` 
 * with the provided server-side configuration.
 * 
 * - `bootstrapApplication` is used to bootstrap the root `AppComponent` with the specified configuration.
 * - The configuration (`config`) is imported from the server-specific configuration file.
 */
const bootstrap = () => bootstrapApplication(AppComponent, config);

export default bootstrap;
