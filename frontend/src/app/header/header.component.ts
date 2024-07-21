import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { routes } from '../app.routes';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
    constructor(private router: Router) { }
    goToCreateBlog() {
        this.router.navigate(['/createblog']);
    }
    goToHome() {
      this.router.navigate(['/home']);
  }
}
