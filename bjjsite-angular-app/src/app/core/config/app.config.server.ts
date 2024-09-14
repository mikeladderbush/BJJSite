import { mergeApplicationConfig, ApplicationConfig } from '@angular/core';
import { provideServerRendering } from '@angular/platform-server';
import { appConfig } from './app.config';

/**
 * Server-specific Angular configuration.
 * 
 * This configuration merges the general application configuration (`appConfig`)
 * with server-specific providers to enable server-side rendering.
 */
const serverConfig: ApplicationConfig = {
  providers: [
    provideServerRendering() // Enables server-side rendering capabilities
  ]
};

export const config = mergeApplicationConfig(appConfig, serverConfig);
