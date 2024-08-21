import { Routes } from '@angular/router';
import { provideRouter } from '@angular/router';
import { AppComponent } from '../components/app-component/app.component';
import { HomepageComponent } from '../../features/homepage/homepage-component/homepage.component';

export const appRoutes: Routes = [
    { path: '', component: HomepageComponent }
];