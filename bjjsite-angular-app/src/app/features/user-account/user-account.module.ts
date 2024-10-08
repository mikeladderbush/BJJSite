import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SharedModule } from '../../shared/shared.module';
import { UserAccountComponent } from './user-account-component/user-account.component';
import { UserAccountRoutingModule } from './user-account-routing.module';


@NgModule({
    declarations: [UserAccountComponent],
    imports: [
        FormsModule,
        UserAccountRoutingModule,
        SharedModule
    ],
    exports: [UserAccountComponent]
})
export class UserAccountPageModule { }
