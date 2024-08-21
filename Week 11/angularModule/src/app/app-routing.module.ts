import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponentComponent } from './login-component/login-component.component'
import { SignupComponentComponent } from './signup-component/signup-component.component'
import { UsersComponentComponent } from './users-component/users-component.component'

const routes: Routes = [
  { path: 'login', component: LoginComponentComponent },
  { path: 'signup', component: SignupComponentComponent},
  { path: 'view-users', component: UsersComponentComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
