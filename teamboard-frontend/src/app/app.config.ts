import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';

import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [
    // Optymalizacja detekcji zmian
    provideZoneChangeDetection({ eventCoalescing: true }),

    // Konfiguracja ścieżek aplikacji
    provideRouter(routes),

    // Rejestracja klienta HTTP - bez tego AdService nie zadziała
    provideHttpClient()
  ]
};
