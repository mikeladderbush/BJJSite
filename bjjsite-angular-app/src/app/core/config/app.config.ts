import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { appRoutes } from '../routing/app.routes';
import { provideClientHydration } from '@angular/platform-browser';

/**
 * Application-wide configuration for Angular.
 * 
 * This configuration sets up essential providers, including:
 * - Zone-based change detection with event coalescing enabled for performance optimization.
 * - Routing configuration using `appRoutes`.
 * - Client-side hydration support for improving the performance of server-side rendered applications.
 */
export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }), // Optimizes change detection performance
    provideRouter(appRoutes), // Configures the router with application routes
    provideClientHydration() // Enables client-side hydration for SSR
  ]
};
