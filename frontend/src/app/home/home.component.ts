import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { BlogcardComponent } from '../blogcard/blogcard.component';
import { BlogServiceService } from '../services/blog-service.service';
import { NgFor } from '@angular/common';
import { date } from 'zod';
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [HeaderComponent, BlogcardComponent, NgFor],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  blogs: any[] = [];

  constructor(private blogService: BlogServiceService) { }

  ngOnInit(): void {
    this.blogService.getBlogs().subscribe((data) => {
      this.blogs = data;
    });
}
}
