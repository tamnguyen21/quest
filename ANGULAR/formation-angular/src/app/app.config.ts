import { ApplicationConfig, provideBrowserGlobalErrorListeners, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { apiUrlInterceptor } from './api-url-interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    // Services liés au navigateur
    provideBrowserGlobalErrorListeners(),

    // Services liés au refresh avec zoneJS
    provideZoneChangeDetection({ eventCoalescing: true }),

    // Services liés aux routes
    provideRouter(routes),

    // Services liés au client HTTP HttpClient
    provideHttpClient(
      withInterceptors([ apiUrlInterceptor ])
    )
  ]
};
