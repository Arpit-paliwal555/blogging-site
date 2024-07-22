import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { NgModel } from '@angular/forms';
import { BlogServiceService } from '../services/blog-service.service';

@Component({
  selector: 'app-createblog',
  standalone: true,
  imports: [HeaderComponent],
  templateUrl: './createblog.component.html',
  styleUrl: './createblog.component.css'
})


export class CreateblogComponent {
    title:String = '';
    content:String = '';

    constructor(private blogService: BlogServiceService){}
    
    blog:any = {
      title: this.title,
      content: this.content,
      userId: 1,
    }
    
    saveBlog(){
      this.blogService.saveBlog(this.blog).subscribe(response => {
        console.log('Blog saved successfully', response);
        // Handle post-login actions
      }, error => {
        console.error('Login failed', error);
      });
    }


}
