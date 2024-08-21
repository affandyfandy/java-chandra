import { Routes } from '@angular/router';
import { LoginComponentComponent } from './login-component/login-component.component'
import { SignupComponentComponent } from './signup-component/signup-component.component'
import { UsersComponent } from './users/users.component'


export const routes: Routes = [
  { path: 'login', component: LoginComponentComponent },
  { path: 'signup', component: SignupComponentComponent},
  { path: 'view-users', component: UsersComponent}
];
