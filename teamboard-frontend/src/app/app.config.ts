import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideHttpClient } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    // Zmieniamy na standardowy mechanizm odświeżania Angulara
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideHttpClient()
  ]
};
