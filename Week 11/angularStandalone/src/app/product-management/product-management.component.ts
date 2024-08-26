import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { ProductListComponent } from '../product-list/product-list.component';
import { Products } from '../models/products';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProductsService } from '../service/products.service';

@Component({
  selector: 'app-product-management',
  standalone: true,
  imports: [
    CommonModule,
    ProductListComponent,
    ReactiveFormsModule
  ],
  templateUrl: './product-management.component.html',
  styleUrl: './product-management.component.scss'
})
export class ProductManagementComponent {
  productForm: FormGroup;
  editingProduct: Products | null = null;

  @Output() refreshList = new EventEmitter<void>();

  @ViewChild(ProductListComponent) productListComponent!: ProductListComponent; // Use ViewChild

  constructor(private fb: FormBuilder, private productService: ProductsService) {
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      price: [0, Validators.required],
      status: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.productForm.valid) {
      const productData: Products = this.productForm.value;

      if (this.editingProduct) {
        // Update existing product
        const updatedProduct: Products = { ...this.editingProduct, ...productData };
        this.productService.updateProduct(updatedProduct).subscribe(() => {
          this.editingProduct = null;
          this.productForm.reset();
          this.refreshList.emit(); // Emit event to refresh product list
          this.productListComponent.loadProducts(); // Refresh product list
        });
      } else {
        // Add new product
        this.productService.addProduct(productData).subscribe(() => {
          this.productForm.reset();
          this.refreshList.emit(); // Emit event to refresh product list
          this.productListComponent.loadProducts(); // Refresh product list
        });
      }
    }
  }

  onEditProduct(product: Products) {
    this.editingProduct = product;
    this.productForm.patchValue(product);
  }

  refreshProductList() {
    this.productListComponent.loadProducts();
  }
}
