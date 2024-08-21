import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginpageComponent } from './loginpage-component/loginpage.component';
import { LoginpageRoutingModule } from './loginpage-routing.module';

@NgModule({
  declarations: [LoginpageComponent],
  imports: [
    CommonModule,
    LoginpageRoutingModule
  ]
})
export class LoginpageModule { }
