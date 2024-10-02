import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from '../../features/homepage/homepage-component/homepage.component';
import { AboutpageComponent } from '../../features/aboutpage/aboutpage-component/aboutpage.component';
import { ContactpageComponent } from '../../features/contactpage/contactpage.module';
import { LoginpageComponent } from '../../features/loginpage/loginpage-component/loginpage.component';

const routes: Routes = [
  { path: '', component: HomepageComponent },  // Root path shows the homepage
  { path: 'about', component: AboutpageComponent },  // About page
  { path: 'contact', component: ContactpageComponent },  // Contact page
  { path: 'login', component: LoginpageComponent }  // Login page
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
