import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from '../../features/homepage/homepage-component/homepage.component';
import { LoginpageComponent } from '../../features/loginpage/loginpage-component/loginpage.component';
import { UserAccountComponent } from '../../features/user-account/user-account-component/user-account.component';
import { authGuard } from '../../auth.guard';
import { AdminAccountComponent } from '../../admin/admin_features/admin-account/admin-account.component';

const routes: Routes = [
  { path: 'home', component: HomepageComponent },
  { path: 'login', component: LoginpageComponent },  // Login page
  { path: 'user-account', component: UserAccountComponent, canActivate: [authGuard]},
  { path: 'admin-account', component: AdminAccountComponent, canActivate: [authGuard]},
  { path: '', redirectTo: '/home', pathMatch: 'full' },  // Root path shows the homepage
  { path: '**', redirectTo: '/home' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
