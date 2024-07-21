import { Component, Input, OnInit } from '@angular/core';
import { BlogServiceService } from '../services/blog-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-details',
  standalone: true,
  imports: [],
  templateUrl: './details.component.html',
  styleUrl: './details.component.css'
})
export class DetailsComponent implements OnInit {

    blog:any;
    user:any = {
        username: 'Anonymous',
        email: 'Anonymous'
      };
    name: String = "an";
    constructor(private route: ActivatedRoute,private blogService: BlogServiceService) { }

    blogId:Number=0;
    userId:Number=0;
    
    ngOnInit(): void {
      const blogId = this.route.snapshot.paramMap.get('blogId')
      this.blog = this.blogService.getBlogById(this.blogId);
      if (blogId) {
        this.blogService.getBlogById(+blogId).subscribe((data) => {
          this.blog = data;
          this.userId = this.blog.userId;
          if (this.userId !== 0 && this.userId !== undefined) {
            this.blogService.getUserById(this.userId).subscribe((userData) => {
              this.user = userData;
              this.name = this.user.username.substring(0, 2);
            });
          } 
          
        });
      }
    }
    
}
