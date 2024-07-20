import { Routes } from '@angular/router';
import { provideRouter } from '@angular/router';
import { AppComponent } from './app.component';
import { HomepageComponent } from './homepage/homepage.component';

export const routes: Routes = [
    { path: '', component: HomepageComponent },
];

export const appRoutes = provideRouter(routes);
