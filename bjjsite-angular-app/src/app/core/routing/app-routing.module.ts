import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from '../../features/homepage/homepage-component/homepage.component';
import { LoginpageComponent } from '../../features/loginpage/loginpage-component/loginpage.component';

const routes: Routes = [
  { path: '', component: HomepageComponent },  // Root path shows the homepage
  { path: 'login', component: LoginpageComponent }  // Login page
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
