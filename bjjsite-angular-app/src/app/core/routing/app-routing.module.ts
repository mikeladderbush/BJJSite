import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from '../../features/homepage/homepage-component/homepage.component';
import { LoginpageComponent } from '../../features/loginpage/loginpage-component/loginpage.component';
import { UserAccountComponent } from '../../features/user-account/user-account-component/user-account.component';
import { authGuard } from '../../auth.guard';

const routes: Routes = [
  { path: '', component: HomepageComponent },  // Root path shows the homepage
  { path: 'login', component: LoginpageComponent },  // Login page
  { path: 'user-account', component: UserAccountComponent, canActivate: [authGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
