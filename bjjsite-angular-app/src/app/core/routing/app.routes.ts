import { Routes } from '@angular/router';
import { provideRouter } from '@angular/router';
import { AppComponent } from '../components/app-component/app.component';
import { HomepageComponent } from '../../features/homepage/homepage-component/homepage.component';
import { LoginpageComponent } from '../../features/loginpage/loginpage-component/loginpage.component';
import { UserAccountComponent } from '../../features/user-account/user-account-component/user-account.component';
import { ShoppingcartComponent } from '../../features/shoppingcart/shoppingcart.component';
import { SignupformComponent } from '../../features/signupform/signupform.component';
import { AdminAccountComponent } from '../../admin/admin_features/admin-account/admin-account.component';

/**
 * appRoutes
 * 
 * Defines the routes for the application.
 * 
 * - The base path ('') maps to the `HomepageComponent`, which serves as the landing page of the application.
 */
export const appRoutes: Routes = [
    { path: 'home', component: HomepageComponent },
    { path: 'login', component: LoginpageComponent },
    { path: 'user-account', component: UserAccountComponent },
    { path: 'admin-account', component: AdminAccountComponent},
    { path: 'signupform', component: SignupformComponent},
    { path: 'shoppingcart', component: ShoppingcartComponent },
    { path: '', redirectTo: '/home', pathMatch: 'full' }  // Root path shows the homepage
];
