import { Routes } from '@angular/router';
import { LoginComponentComponent } from './login-component/login-component.component'
import { SignupComponentComponent } from './signup-component/signup-component.component'
import { UsersComponent } from './users/users.component'
import { ProductManagementComponent } from './product-management/product-management.component';

export const routes: Routes = [
  { path: '', redirectTo: '/product-management', pathMatch: 'full'},
  { path: 'product-management', component: ProductManagementComponent},
  { path: 'login', component: LoginComponentComponent },
  { path: 'signup', component: SignupComponentComponent},
  { path: 'view-users', component: UsersComponent},
];
