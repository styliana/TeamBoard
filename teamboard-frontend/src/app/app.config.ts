import { ApplicationConfig, provideZonelessChangeDetection } from '@angular/core'; // USUNIĘTO 'Experimental'
import { provideHttpClient } from '@angular/common/http';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [
    // Teraz używamy stabilnej wersji mechanizmu bez Zone.js
    provideZonelessChangeDetection(),
    provideRouter(routes),
    provideHttpClient() // To musi tu być, żeby Angular mógł pobrać ogłoszenia z Javy
  ]
};
