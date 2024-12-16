import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ColDef } from 'ag-grid-community';
import { Products } from '../models/products';
import { CommonModule } from '@angular/common';
import { AgGridModule } from 'ag-grid-angular';
import { ProductsService } from '../service/products.service';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [
    CommonModule,
    AgGridModule
  ],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.scss'
})
export class ProductListComponent implements OnInit{

  @Output() productSelected = new EventEmitter<Products>();

  public paginationPageSize = 10;

  columnDefs: ColDef[] = [
    { field: 'id'},
    { field: 'name', filter: "agSetColumnFilter" },
    { field: 'price' },
    { field: 'status', sortable: true }
  ];

  rowData: Products[] = [];

  constructor(private productService: ProductsService) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getProducts().subscribe((data) => {
      this.rowData = data;
    });
  }

  onRowClicked(event: any): void {
    this.productSelected.emit(event.data);
  }
}
