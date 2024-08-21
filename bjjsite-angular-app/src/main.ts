import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/core/components/app-component/app.component';
import { appRoutes } from './app/core/routing/app.routes';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

bootstrapApplication(AppComponent, {
  providers: [appRoutes]
}).catch((err) => console.error(err));
