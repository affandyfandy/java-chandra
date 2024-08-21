import { Component } from '@angular/core';
import { UsersService} from '../service/users-service'
import { Users } from '../models/users';

@Component({
  selector: 'app-signup-component',
  templateUrl: './signup-component.component.html',
  styleUrl: './signup-component.component.scss'
})
export class SignupComponentComponent {
  email: string = '';
  password: string = '';
  fullname: string = '';
  constructor(private userService: UsersService) {}

  onSubmit() {
    if (!this.email.trim() || !this.password.trim() || !this.fullname.trim()) {
      console.log('All fields are required');
      return;
    }

    const newUser: Users = {
      email: this.email,
      password: this.password,
      fullname: this.fullname
    };

    this.userService.create(newUser).subscribe({
      next: (createdUser) => {
        console.log('User created successfully:', createdUser);
      },
      error: (error) => {
        console.error('There was an error creating the user:', error);
      }
    });
  }
}
