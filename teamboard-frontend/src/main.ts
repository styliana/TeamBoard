import 'zone.js'; // KLUCZOWE: Ten import musi być na samej górze!

import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { App } from './app/app.component';

bootstrapApplication(App, appConfig)
  .catch((err) => console.error(err));
