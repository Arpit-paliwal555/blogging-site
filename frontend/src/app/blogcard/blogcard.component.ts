import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Date } from 'mongoose';

@Component({
  selector: 'app-blogcard',
  standalone: true,
  imports: [],
  templateUrl: './blogcard.component.html',
  styleUrl: './blogcard.component.css'
})

export class BlogcardComponent {

  @Input()
  blogId: String="";

  @Input()
  authorName: String="";

  @Input()
  title: String="";

  @Input()
  content: String="";

  @Input()
  publishedDate:any=new Date();

  constructor(private router: Router) { }

  date:any = new Date();
  //publishedDate = new Date().toLocaleString();
  minutes:Number = 0;
  ngOnInit(): void {
    this.minutes = Math.ceil(this.content.length/100);
  }
  

  goToDetails(){
    this.router.navigate(['/details',this.blogId]);
  }
  
  
  

}
