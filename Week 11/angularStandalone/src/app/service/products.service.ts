import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Products } from '../models/products';

const baseUrl = 'http://localhost:3000/products';
@Injectable({
  providedIn: 'root'
})

export class ProductsService {
  constructor(private http: HttpClient) {}

  getProducts(): Observable<Products[]> {
    return this.http.get<Products[]>(baseUrl);
  }

  addProduct(product: Products): Observable<Products> {
    return this.http.post<Products>(baseUrl, product);
  }

  updateProduct(product: Products): Observable<Products> {
    return this.http.put<Products>(`${baseUrl}/${product.id}`, product);
  }
}
