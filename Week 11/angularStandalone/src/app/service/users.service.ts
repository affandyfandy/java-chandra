import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Users } from '../models/users';

const baseUrl = 'http://localhost:3000/users';
@Injectable({
  providedIn: 'root'
})

export class UsersService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<Users[]> {
    return this.http.get<Users[]>(baseUrl);
  }

  create(user: Users): Observable<Users> {
    return this.http.post<Users>(baseUrl, user);
  }

  checkCredentials(email: string, password: string): Observable<boolean> {
    return this.http.get<Users[]>(`${baseUrl}?email=${email}&password=${password}`)
      .pipe(
        map(users => users.length > 0)
      );
  }
}
