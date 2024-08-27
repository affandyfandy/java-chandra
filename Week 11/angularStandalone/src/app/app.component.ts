import { Component } from '@angular/core';
import { RouterOutlet, RouterLink, Router } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from "./header/header.component";
import { FooterComponent } from './footer/footer.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    RouterLink,
    HttpClientModule,
    HeaderComponent,
    FooterComponent
],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})

export class AppComponent {}
