import { Component, OnInit } from '@angular/core';
import { Users } from '../models/users';
import { UsersService } from '../service/users-service';

@Component({
  selector: 'app-users-component',
  templateUrl: './users-component.component.html',
  styleUrl: './users-component.component.scss'
})
export class UsersComponentComponent implements OnInit{
  users?: Users[];

  constructor(private userService: UsersService) {}

  ngOnInit(): void {
    this.retrieveCustomers();
  }

  retrieveCustomers(): void {
    this.userService.getAll().subscribe({
      next: (data) => {
        this.users = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }
}
