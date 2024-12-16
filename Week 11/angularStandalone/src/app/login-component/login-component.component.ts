import { Component} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UsersService} from '../service/users.service';

@Component({
  selector: 'app-login-component',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './login-component.component.html',
  styleUrl: './login-component.component.scss'
})

export class LoginComponentComponent{
  email: string = '';
  password: string = '';

  constructor(private userService: UsersService) {}

  onSubmit() {
    if (!this.email.trim() || !this.password.trim()) {
      console.log('Email and/or password cannot be empty');
      return;
    }

    this.userService.checkCredentials(this.email, this.password).subscribe((isValid) => {
      if (isValid) {
        console.log('Login successful');
      } else {
        console.log('Invalid credentials');
      }
    });
  }
}
